package manager;

import domain.Equipment;
import domain.EquipmentStatus; 
import java.util.ArrayList;
import java.util.List;

public class EquipmentManager {

    private List<Equipment> equipmentList = new ArrayList<>();

    public void addEquipment(Equipment e) {
        equipmentList.add(e);
    }

    public void removeEquipment(int id) {
        for (Equipment e : equipmentList) {
            if (e.getEquipmentId() == id) {
                e.setStatus(EquipmentStatus.REMOVED); 
                return;
            }
        }
        System.out.println("Equipment not found!");
    }

    public Equipment findEquipmentById(int id) {
        for (Equipment e : equipmentList) {
            if (e.getEquipmentId() == id) return e;
        }
        return null;
    }

    public List<Equipment> listEquipment() {
        return equipmentList;
    }
}
