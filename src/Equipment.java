
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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
    boolean writeToCSV;//to avoid duplication in csv

    public static ArrayList<Equipment> equipmentList = new ArrayList<>();
    private static ArrayList<Equipment> equippedItems = new ArrayList<>();
    public static int[] stats = {0, 0, 0};

    public Equipment(int ID, String name, int health, int strength, int initiative, int type, boolean unlocked, boolean writeToCSV) {
        if (type < 1 || type > 4) {
            throw new IllegalArgumentException("Type must be between 1 and 4 inclusive.");
        }
        this.ID = ID;
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.initiative = initiative;
        this.type = type;
        this.unlocked = unlocked;
        
        equipmentList.add(this);

        //checks if writeTOCSV is true, later on in generateEquipment its set to true, but in readFromCSV its set to false to avoid duplication.
        if (writeToCSV) {
            try {
                appendToCSV("allEquipments.csv");
            } catch (IOException e) {
                System.err.println("Error writing to CSV: " + e.getMessage());
            }
        }
    }

    //Converts Equipment to csv
    public String eqToCSV() {
        return ID + "," + name + "," + health + "," + strength + "," + initiative + "," + type + "," + unlocked;
    }

    //Adds this Equipment to csv
    public void appendToCSV(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(this.eqToCSV());
            writer.newLine();
        }
    }
    //reads from csv
     public static void readFromCSV(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Stops if it finds a null line
            while ((line = reader.readLine()) != null) {
                // skips lines that are just spaces
                if (line.trim().isEmpty()) continue;

                String[] values = line.split(",");
                //Equipments need 7 parameters to contruct
                if (values.length != 7) {
                    System.err.println("Invalid line: " + line);
                    continue;
                }

                int ID = Integer.parseInt(values[0].trim());
                String name = values[1].trim();
                int health = Integer.parseInt(values[2].trim());
                int strength = Integer.parseInt(values[3].trim());
                int initiative = Integer.parseInt(values[4].trim());
                int type = Integer.parseInt(values[5].trim());
                boolean unlocked = Boolean.parseBoolean(values[6].trim());

               System.err.println(new Equipment(ID, name, health, strength, initiative, type, unlocked, false));//sets writeToCSV false so it doesnt write to CSV again 
            }
            System.err.println("----------------------------------------------------------------------------------------  \n");
            System.out.println("Those were all the available equipments. Data successfully loaded from " + fileName + "\n");
            System.err.println("----------------------------------------------------------------------------------------  \n");
        } catch (IOException e) {
            System.err.println("Error reading file: " + fileName);
            e.printStackTrace();
        }
    }

    //convert type number to name ie helmet armour etc
    public static String typeName(int type) {
        if (type == 1) {
            return "Helmet";
        }
        if (type == 2) {
            return "Armour";
        }
        if (type == 3) {
            return "Item";
        }
        if (type == 4) {
            return "Weapon";
        }
        else{
            return "Invalid"; // never actually happens, handled in constructor
        }
    }

    public String toString() {
        return "Equipment[" + "ID:" + ID + " - Name:" + name + " | Health:" + health + " | Strength:" + strength + " | Initiative:" + initiative + " | Type:" + typeName(type) + ']';
    }

    //Prints all unlocked equipment
    public static void allEquipment() {
        System.out.println("All unlocked Equipment:");
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
    

    //creates equipments - since equipments are added to allEquipment.csv at creation running this twice add these entries twice
    public static void generateEquipments(){
        //feel free to add equipments here, ID needs to be distict.
        Equipment sword = new Equipment(1, "Short Sword", 0, 10,  5, 4, true,true);
        Equipment shield = new Equipment(2, "Wooden Shield", 10, 0,  0, 3, true,true);
        Equipment helmet = new Equipment(3, "Ordinary Cap", 5, 5, 5, 1, true,true);
        Equipment armour = new Equipment(4, "Rusty Armour", 10, 0, -5,  2, true,true);
        Equipment locked = new Equipment(5, "Armour of strength", 20, 10,  0, 2, false,true);
        Equipment armo = new Equipment(6, "Knights Helmet", 10, 0, 5,  1, false,true);
        Equipment a = new Equipment(7, "Chain-mail Armour", 15, 0, 0,  2, false,true);
        Equipment b = new Equipment(8, "Life4damage gloves", -75, 80, -15,  3, false,true);
        Equipment shiel = new Equipment(9, "Big Wall Shield", 50, 0,  -20, 3, false,true);
        Equipment sld = new Equipment(10, "Flash Katana", 0, 20,  10, 4, false,true);
        Equipment slds = new Equipment(11, "Long Sword", 0, 20,  -10, 4, false,true);
    }

    
    //responsible for equiping 
    public static void equip() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> usedTypes = new ArrayList<>();
        equippedItems.clear();

        allEquipment();

        int equippedCount = 0;
        while (equippedCount < 4) {
            System.out.print("Enter the ID of Equipment to equip (" + (equippedCount + 1) + "/4): \n");
            int choice = 0;
            try {
                choice = scanner.nextInt();

            } catch (InputMismatchException ex) {
                System.err.println("enter a number ");
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
}
