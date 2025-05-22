import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class equipment{

    int ID;
    String name;
    int health;
    int strength;
    int defence;
    int initiative;
    int type;

    private static ArrayList<equipment> equipmentList = new ArrayList<>();

    public equipment(int ID, String name, int health, int strength, int defence, int initiative, int type) {
        if (type < 1 || type > 4) {
            throw new IllegalArgumentException("Type must be between 1 and 4 inclusive.");
        }
        this.ID=ID;
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.defence = defence;
        this.initiative = initiative;
        this.type = type;
        equipmentList.add(this);
    }

    //convert type number to name ie helmet armour etc
    public String typeName(int type) {
        String typeName = "";
        if (type == 1) {
            typeName = "helmet";
        }
        if (type == 2) {
            typeName = "Armour";
        }
        if (type == 3) {
            typeName = "Item";
        }
        if (type == 4) {
            typeName = "wepon";
        }
        return typeName;
    }

    public String toString() {
        return "Equipment{" + "ID = " + ID + " Name = " + name + " , Health = " + health + " , Strength = " + strength + ", Defence = " + defence + ", Initiative = " + initiative + ", Type = " + typeName(type) + '}' + "\n";
    }

    public static void allEquipment() {
        System.out.println("All created equipment:");
        for (equipment equipment : equipmentList) {
            System.out.println(equipment);

        }
    }



    public static List<equipment> equip() {
    Scanner scanner = new Scanner(System.in);
    List<equipment> equippedItems = new ArrayList<>();
    List<Integer> usedTypes = new ArrayList<>();

    allEquipment();

    int equippedCount = 0;
    while (equippedCount < 4) {
        System.out.print("Enter the ID of equipment to equip (" + (equippedCount + 1) + "/4): ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > 10) {
            System.out.println("Invalid ID. Please choose an ID between 1 and 10.");
            continue;
        }

        equipment selected = null;
        for (equipment eq : equipmentList) {
            if (eq.ID == choice) {
                selected = eq;
                break;
            }
        }

        if (selected == null) {
            System.out.println("No equipment found with that ID.");
            continue;
        }

        if (usedTypes.contains(selected.type)) {
            System.out.println("You have already equipped an item of type: " + selected.typeName(selected.type));
            continue;
        }

        
        equippedItems.add(selected);
        usedTypes.add(selected.type);
        System.out.println("Equipped: " + selected.name + " (" + selected.typeName(selected.type) + ")");
        equippedCount++;
    }
    scanner.close();
    return equippedItems;
}

    public static void main(String[] args) {
        equipment sword = new equipment(1, "Sword", 0, 10, 0, 5, 4);
        equipment shield = new equipment(2, "Shield", 5, 0, 10, 0, 3);
        equipment helmet = new equipment(3, "helmet", 0, 5, 0, 0, 1);
        equipment armor = new equipment(4, "Armor", 20, 0, 15, 0, 2);
        allEquipment();
        System.out.println(equip());
    }

    
}