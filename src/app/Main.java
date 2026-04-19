package app;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/handle", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                System.out.println(exchange);
                // Handle CORS preflight
                if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                    exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
                    exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
                    exchange.sendResponseHeaders(204, -1);
                    return;
                }

//
//                if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
//                    byte[] msg = "Only POST allowed".getBytes("UTF-8");
//                    exchange.sendResponseHeaders(405, msg.length);
//                    exchange.getResponseBody().write(msg);
//                    exchange.close();
//                    return;
//                }

                // -----------------------------
                // Read body (Java 8 compatible)
                // -----------------------------
                InputStream is = exchange.getRequestBody();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;

                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }

                String body = baos.toString("UTF-8").trim();
                // Example body: "addEquipment,1,Chainsaw,Electric,25.0,AVAILABLE"

                // -----------------------------
                // Parse action + arguments
                // -----------------------------
                String[] parts = body.split(",");
                String action = parts[0];
                System.out.println(Arrays.toString(parts));
                // Copy remaining parts into args[]
                String[] args = new String[parts.length - 1];
                for (int i = 1; i < parts.length; i++) {
                    args[i - 1] = parts[i];
                }

                // -----------------------------
                // Handle actions directly here
                // -----------------------------
                String response;

                switch (action) {

                    case "addEquipment":
                        response = "Added equipment: " + String.join(", ", args);
                        break;

                    case "deleteEquipment":
                        response = "Deleted equipment ID: " + args[0];
                        break;

                    case "addCustomer":
                        response = "Added customer: " + String.join(", ", args);
                        break;

                    case "getAllEquipment":
                        response = "Equipment list here";
                        break;

                    case "getAllCustomers":
                        response = "Customer list here";
                        break;

                    case "getRentalsForCustomer":
                        response = "Rentals for customer ID: " + args[0];
                        break;

                    default:
                        response = "Unknown action: " + action;
                }
System.out.println(response);
                // -----------------------------
                // Send response
                // -----------------------------
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

                byte[] out = response.getBytes("UTF-8");
                exchange.sendResponseHeaders(200, out.length);
                exchange.getResponseBody().write(out);
                exchange.close();
            }
        });

        server.setExecutor(null);
        System.out.println("Server running at http://localhost:8080/");
        server.start();

        File html = new File("./web/index.html");
        Desktop.getDesktop().browse(html.toURI());
    }
}
