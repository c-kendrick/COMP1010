
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Equipment{

    int ID;
    String name;
    int health;
    int strength;
    int initiative;
    int type;
    boolean unlocked;

    public static ArrayList<Equipment> equipmentList = new ArrayList<>();
    private static ArrayList<Equipment> equippedItems = new ArrayList<>();
    public static int[] stats = {0, 0, 0};

    public Equipment(int ID, String name, int health, int strength, int initiative, int type, boolean unlocked) {
        if (type < 1 || type > 4) {
            System.err.println("Type must be between 1 and 4 inclusive.");
        }
        this.ID = ID;
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.initiative = initiative;
        this.type = type;
        equipmentList.add(this);
        this.unlocked = unlocked;

        try {
        appendToCSV("allEquipments.csv"); 
    } catch (IOException e) {
        System.err.println("Error writing to CSV: " + e.getMessage());
    }


    }


    //Converts Equipment to csv
    public String EqtoCSV() {
        return ID + "," + name + "," + health + "," + strength + "," + initiative + "," + type + "," + unlocked;
    }

    //Adds this Equipment to csv
    public void appendToCSV(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(this.EqtoCSV());
            writer.newLine();
        }
    }


    //convert type number to name ie helmet armour etc
    public static String typeName(int type) {
        String typeName = "";
        if (type == 1) {
            typeName = "Helmet";
        }
        if (type == 2) {
            typeName = "Armour";
        }
        if (type == 3) {
            typeName = "Item";
        }
        if (type == 4) {
            typeName = "Weapon";
        }
        return typeName;
    }

    public String toString() {
        return "Equipment[" + "ID:" + ID + " - Name:" + name + " | Health:" + health + " | Strength:" + strength + " | Initiative:" + initiative + " | Type:" + typeName(type) + ']';
    }
    //Prints all unlocked equipment
    public static void allEquipment() {
        System.out.println("All created Equipment:");
        for (Equipment Equipment : equipmentList) {
            if (Equipment.unlocked == true) {
                System.out.println(Equipment);
            }
        }
    }
//prints out equiped equipments
    public static void equiped() {
        System.out.println("All active Equipment:");
        for (Equipment Equipment : equippedItems) {
            System.out.println(Equipment);

        }
    }
    //calculates added stats of equiped equipments
    public static int[] addedstats() {
        
        Arrays.fill(stats, 0);
        for (Equipment eq : equippedItems) {
            stats[0] += eq.health;
            stats[1] += eq.strength;
            stats[2] += eq.initiative;
        }
        return stats;
    }
    public static void generateEquipments(){
        Equipment sword = new Equipment(1, "Short Sword", 0, 10,  5, 4, true);
        Equipment shield = new Equipment(2, "Wooden Shield", 10, 0,  0, 3, true);
        Equipment helmet = new Equipment(3, "Ordinary Cap", 5, 5, 5, 1, true);
        Equipment armour = new Equipment(4, "Rusty Armour", 10, 0, -5,  2, true);
        Equipment locked = new Equipment(5, "Armour of strength", 20, 10,  0, 2, false);
        Equipment armo = new Equipment(6, "Knights Helmet", 10, 0, 5,  1, false);
        Equipment a = new Equipment(7, "Chain-mail Armour", 15, 0, 0,  2, false);
        Equipment b = new Equipment(8, "Life4damage gloves", -75, 80, -15,  3, false);
        Equipment shiel = new Equipment(9, "Big Wall Shield", 50, 0,  -20, 3, false);
        Equipment sld = new Equipment(10, "Flash Katana", 0, 20,  10, 4, false);
        Equipment slds = new Equipment(11, "Long Sword", 0, 20,  -10, 4, false);
    }
    
    //responsible for equiping 
    public static void equip() {
        //if(equipmentList.isEmpty() == true){
        //    generateEquipments();
        //}
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> usedTypes = new ArrayList<>();
        equippedItems.clear();

        allEquipment();

        int equippedCount = 0;
        while (equippedCount < 4) {
            System.out.print("Enter the ID of Equipment to equip (" + (equippedCount + 1) + "/4): ");
            int choice = 0;
            try {
                choice = scanner.nextInt();

            } catch (InputMismatchException ex) {
                System.err.println("enter a number");
                scanner.nextLine();
            }

            Equipment selected = null;
            for (Equipment eq : equipmentList) {
                if (eq.ID == choice && eq.unlocked == true) {
                    selected = eq;
                    break;
                }
            }

            if (selected == null) {
                System.out.println("No Equipment found with that ID.");
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
        //scanner.close();
        addedstats();
        equiped();
    }
    //function that unlocks 2 random equipments
    public static void unlockTwoRandomEquipments() {
        List<Equipment> lockedItems = new ArrayList<>();

        for (Equipment eq : equipmentList) {
            if (!eq.unlocked) {
                lockedItems.add(eq);
            }
        }

        if (lockedItems.size() < 2) {
            System.out.println("No equipments left to unlock.");
            return;
        }

        Random random = new Random();
        int firstIndex = random.nextInt(lockedItems.size());
        Equipment first = lockedItems.remove(firstIndex); // remove to avoid duplicate
        first.unlocked = true;

        int secondIndex = random.nextInt(lockedItems.size());
        Equipment second = lockedItems.get(secondIndex);
        second.unlocked = true;

        System.out.println("Equipments found: " + first.name + " and " + second.name);
    }
        



    public static void main(String[] args) {
        generateEquipments();
        unlockTwoRandomEquipments();
        for (int i : addedstats()) {
            System.out.print(i + " ");

        }
        System.err.println(" ");
        equip();
        for (int i : addedstats()) {
            System.out.print(i + " ");

        }
        System.err.println(" ");
        //equip();
        unlockTwoRandomEquipments();
        for (int i : addedstats()) {
            System.out.print(i + " ");

        }
        System.err.println(" ");
 
            

    }

}
