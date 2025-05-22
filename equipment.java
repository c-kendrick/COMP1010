
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class equipment {

    int ID;
    String name;
    int health;

@@ -13,7 +15,7 @@ public class equipment {
     private static ArrayList<equipment> equipmentList = new ArrayList<>();


    public equipment(int ID, String name, int health, int strength, int defence, int initiative, int type, equipment left, equipment right) {
    public equipment(int ID, String name, int health, int strength, int defence, int initiative, int type) {
        if (type < 1 || type > 4) {
            throw new IllegalArgumentException("Type must be between 1 and 4 inclusive.");
        }


@@ -56,7 +58,7 @@ public class equipment {
    
    
    public String toString(){   
        return "Equipment{" +"ID=" + ID +"Name= " + name + ", Health= " + health + ", Strength= " + strength +", Defence= " + defence +", Initiative= " + initiative + ", Type= " + typeName(type) +'}';
        return "Equipment{" +"ID = " + ID +" Name = " + name + " , Health = " + health + " , Strength = " + strength +", Defence = " + defence +", Initiative = " + initiative + ", Type = " + typeName(type) +'}' + "\n";
    }

    public static void allEquipment(){

@@ -74,31 +76,96 @@ public class equipment {
        }
    }

    public void equip(){
        Scanner scanner = new Scanner(System.in);
        allEquipment();
        //System.out.println("choose 4 different types of equipments, type in their IDs ");
        equipment prev = null;
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter the ID of equipment to equip :");
            int choice = scanner.nextInt();

            if (choice < 01 || choice > 10) {
                System.out.println("Invalid choice, please choose again.");
                i--;
                continue;


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

        // All checks passed — equip it
        equippedItems.add(selected);
        usedTypes.add(selected.type);
        System.out.println("Equipped: " + selected.name + " (" + selected.typeName(selected.type) + ")");
        equippedCount++;
    }

    return equippedItems;
}


public static void man(String[] args) {
public static void main(String[] args) {
    equipment sword = new equipment(01,"Sword", 0, 10, 0, 5, 4);
    equipment shield = new equipment(02,"Shield", 5, 0, 10, 0, 3);
    equipment helmet = new equipment(03,"helmet", 0, 5, 0, 0, 1);
    equipment armor = new equipment(04,"Armor", 20, 0, 15, 0, 2);
    allEquipment();
    
    //allEquipment();
    System.out.println(equip());
    