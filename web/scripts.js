const actionSelect = document.getElementById("actionSelect");
const formContainer = document.getElementById("formContainer");
const output = document.getElementById("output");

actionSelect.addEventListener("change", () => {
  const action = actionSelect.value;

  switch (action) {

    case "addEquipment":
      formContainer.innerHTML = `
        <input id="equipmentId" placeholder="Equipment ID">
        <input id="categoryId" placeholder="Category ID">
        <input id="name" placeholder="Name">
        <input id="description" placeholder="Description">
        <input id="dailyCost" type="number" placeholder="Daily Cost">
        <input id="status" placeholder="Status (AVAILABLE, RENTED, REMOVED)">
      `;
      break;

    case "deleteEquipment":
      formContainer.innerHTML = `
        <input id="equipmentId" placeholder="Equipment ID">
      `;
      break;

    case "addCustomer":
      formContainer.innerHTML = `
        <input id="firstName" placeholder="First Name">
        <input id="lastName" placeholder="Last Name">
        <input id="phone" type="tel" placeholder="Phone">
        <input id="email" type="email" placeholder="Email">
        <input id="discountRate" type="number" placeholder="Discount Rate">
       `;
       <!-- any new customer is supposedly not banned so ignore -->
      break;

    case "getAllEquipment":
    case "getAllCustomers":
      formContainer.innerHTML = ""; // no fields needed
      break;

    case "getRentalsForCustomer":
      formContainer.innerHTML = `
        <input type="number" id="rentalId" placeholder="Rental ID">
        <label for="currentDate">Current Date</label>
        <input type="date" id="currentDate" placeholder="Current Date">
        <input type="number" id="customerId" placeholder="Customer ID">
        <input type="text" id="customerLastName" placeholder="Customer Last Name">
        <label for="rentalDate">Rental Date</label>
        <input type="date" id="rentalDate" placeholder="Rental Date">
        <label for="returnDate">Return Date</label>
        <input type="date" id="returnDate" placeholder="Return Date">
      `;
      break;

    default:
      formContainer.innerHTML = "";
  }
});

document.getElementById("submitBtn").addEventListener("click", () => {
  const action = actionSelect.value;
  let data = "";

  switch (action) {

    case "addEquipment":
      data = [
        "addEquipment",
        document.getElementById("categoryId").value,
        document.getElementById("name").value,
        document.getElementById("description").value,
        document.getElementById("dailyCost").value,
        document.getElementById("status").value
      ].join(",");
      break;

    case "deleteEquipment":
      data = "deleteEquipment," + document.getElementById("equipmentId").value;
      break;

    case "addCustomer":
      data = [
        document.getElementById("firstName").value,
        document.getElementById("lastName").value,
        document.getElementById("phone").value,
        document.getElementById("email").value,
        document.getElementById("discountRate").value,
        document.getElementById("isBanned").value
      ].join(",");
      break;

    case "getRentalsForCustomer":
      data = "getRentalsForCustomer." + document.getElementById("customerId").value;
      break;

    case "getAllEquipment":
      data = "getAllEquipment"
    case "getAllCustomers":
      data = "getAllCustomers"; // no fields
      break;
  }

   connect(data)
});

async function connect(data) {
  await fetch("http://localhost:8080/api/handle", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: data
  })
    .then(res => res.text())
    .then(result => {
      output.textContent = result;
    })
    .catch(err => {
      output.textContent = "Error: " + err;
    });
  }