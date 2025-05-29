
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Equipment{

    int ID;
    String name;
    int health;
    int strength;
    int defence;
    int initiative;
    int type;
    boolean unlocked;

    private static ArrayList<Equipment> equipmentList = new ArrayList<>();
    private static ArrayList<Equipment> equippedItems = new ArrayList<>();

    public Equipment(int ID, String name, int health, int strength, int defence, int initiative, int type, boolean unlocked) {
        if (type < 1 || type > 4) {
            System.err.println("Type must be between 1 and 4 inclusive.");
        }
        this.ID = ID;
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.defence = defence;
        this.initiative = initiative;
        this.type = type;
        equipmentList.add(this);
        this.unlocked = unlocked;
    }

    //convert type number to name ie helmet armour etc
    public static String typeName(int type) {
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
        return "Equipment{" + "ID = " + ID + " Name = " + name + " , Health = " + health + " , Strength = " + strength + ", Defence = " + defence + ", Initiative = " + initiative + ", Type = " + typeName(type) + '}';
    }

    public static void allEquipment() {
        System.out.println("All created Equipment:");
        for (Equipment Equipment : equipmentList) {
            if (Equipment.unlocked == true) {
                System.out.println(Equipment);
            }
        }
    }

    public static void equiped() {
        System.out.println("All active Equipment:");
        for (Equipment Equipment : equippedItems) {
            System.out.println(Equipment);

        }
    }

    public static int[] addedstats() {
        int[] stats = new int[4];
        for (Equipment eq : equippedItems) {
            stats[0] += eq.health;
            stats[1] += eq.strength;
            stats[2] += eq.defence;
            stats[3] += eq.initiative;
        }
        return stats;
    }

    public static void equip() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> usedTypes = new ArrayList<>();

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

        equiped();
    }

    public static void main(String[] args) {
        Equipment sword = new Equipment(1, "Sword", 0, 10, 0, 5, 4, true);
        Equipment shield = new Equipment(2, "Shield", 5, 0, 10, 0, 3, true);
        Equipment helmet = new Equipment(3, "helmet", 0, 5, 0, 0, 1, true);
        Equipment armor = new Equipment(4, "Armor", 20, 0, 15, 0, 2, true);
        Equipment locked = new Equipment(5, "Armor", 200, 100, 15, 0, 2, true);
        for (int i : addedstats()) {
            System.out.print(i + " ");

        }
        System.err.println(" ");
        equip();
        for (int i : addedstats()) {
            System.out.print(i + " ");

        }
        System.err.println(" ");
        equip();
        for (int i : addedstats()) {
            System.out.print(i + " ");

        }
    }

}
