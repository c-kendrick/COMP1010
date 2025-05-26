
import java.util.ArrayList;
import java.util.InputMismatchException;
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
    private static ArrayList<equipment> equippedItems = new ArrayList<>();

    public equipment(int ID, String name, int health, int strength, int defence, int initiative, int type) {
        if (type < 1 || type > 4) {
            System.err.println("Type must be between 1 and 4 inclusive.");
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
        return "Equipment{" + "ID = " + ID + " Name = " + name + " , Health = " + health + " , Strength = " + strength + ", Defence = " + defence + ", Initiative = " + initiative + ", Type = " + typeName(type) + '}' ;
    }

    public static void allEquipment() {
        System.out.println("All created equipment:");
        for (equipment equipment : equipmentList) {
            System.out.println(equipment);

        }
    }
    public static void equiped() {
        System.out.println("All active equipment:");
        for (equipment equipment : equippedItems){
            System.out.println(equipment);

        }
    }
    public static int[] addedstats(){
        int[] stats = new int[4];
        for(equipment eq : equippedItems){
            stats[0] += eq.health;
            stats[1] += eq.strength;
            stats[2] += eq.defence;
            stats[3] += eq.initiative;
        } return stats;
    }


    public static void equip() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> usedTypes = new ArrayList<>();

        allEquipment();

        int equippedCount = 0;
        while (equippedCount < 4) {
            System.out.print("Enter the ID of equipment to equip (" + (equippedCount + 1) + "/4): ");
            int choice = 0;
            try{
                choice = scanner.nextInt();
                
            }catch (InputMismatchException ex){
                System.err.println("enter a number");
                scanner.nextLine();
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

        equiped();
    }

    public static void main(String[] args) {
        equipment sword = new equipment(1, "Sword", 0, 10, 0, 5, 4);
        equipment shield = new equipment(2, "Shield", 5, 0, 10, 0, 3);
        equipment helmet = new equipment(3, "helmet", 0, 5, 0, 0, 1);
        equipment armor = new equipment(4, "Armor", 20, 0, 15, 0, 2);
        for (int i : addedstats()){
            System.out.print(i + " ");

        }
        System.err.println(" ");
        equip();
        for (int i : addedstats()){
            System.out.print(i + " ");

        }
        System.err.println(armor.ID);
    }

    
}