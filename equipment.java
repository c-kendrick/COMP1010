import java.util.ArrayList;
import java.util.Scanner;

public class equipment {
    int ID;
    String name;
    int health;
    int strength;
    int defence;
    int initiative;
    int type;
    equipment left , right; 
     private static ArrayList<equipment> equipmentList = new ArrayList<>();


    public equipment(int ID, String name, int health, int strength, int defence, int initiative, int type, equipment left, equipment right) {
        if (type < 1 || type > 4) {
            throw new IllegalArgumentException("Type must be between 1 and 4 inclusive.");
        }
        this.ID = ID;
        this.name = name;
        this.health= health;
        this.strength = strength;
        this.defence = defence;
        this.initiative = initiative;
        this.type = type;
        this.left = left;
        if(left!=null){
            left.right=this;
        }
        this.right = right;
        if(right!=null){
            right.left=this;
        }
        equipmentList.add(this);
        
    }
    //convert type number to name ie helmet armour etc
    public String typeName(int type){
        String typeName="";
        if (type == 1 ){
            typeName = "helmet";
        }
        if (type == 2 ){
            typeName = "Armour";
        }
        if (type == 3 ){
            typeName = "Item";
        }
        if (type == 4 ){
            typeName = "wepon";
        }
        return typeName;
    }
    
    
    
    public String toString(){   
        return "Equipment{" +"ID=" + ID +"Name= " + name + ", Health= " + health + ", Strength= " + strength +", Defence= " + defence +", Initiative= " + initiative + ", Type= " + typeName(type) +'}';
    }

    public static void allEquipment(){
        System.out.println("All created equipment:");
        for (equipment equipment : equipmentList) {
            System.out.println(equipment);  // Prints the equipment object (automatically calls toString())
        }
    }
    public static void allEquipment(int type){
        System.out.println("All created equipment:");
        for (equipment equipment : equipmentList) {
            if(equipment.type==type){
                System.out.println(equipment);
              }
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
            }

    }



public static void man(String[] args) {
    equipment sword = new equipment(01,"Sword", 0, 10, 0, 5, 4);
    equipment shield = new equipment(02,"Shield", 5, 0, 10, 0, 3);
    equipment helmet = new equipment(03,"helmet", 0, 5, 0, 0, 1);
    equipment armor = new equipment(04,"Armor", 20, 0, 15, 0, 2);
    allEquipment();


    }
}