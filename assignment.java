<<<<<<< Updated upstream
class Character {
    int health;
    int damage;
    int intelligence;
    int initiative;
    String race;
    Boolean isWounded;
    Boolean isPoisoned;
=======
import java.util.ArrayList;
import java.util.Scanner;
    
class StatusEffect {
    int turnsLeft;
    Character target;
    String effect;
    // info used for damage amount or other numbers needed
    int info;
>>>>>>> Stashed changes

    //ArrayList<String> inventory = new ArrayList<>();


    void takeDamage(int amount) {
        health -= amount;
    }

    void attack(Character target, int amount) {
        target.takeDamage(amount);
    }


}   
    




public class assignment {


    public static void main(String[] args) {
<<<<<<< Updated upstream
        barbarian john = new barbarian("John");
        rogue alex = new rogue("Alex");
        System.out.println(alex.health);
        john.attack(alex);
        System.out.println(alex.health);
=======

        Scanner scanner = new Scanner(System.in);

>>>>>>> Stashed changes
        
        System.out.println("You! You're finally awake.");
        System.out.println("What is your name?");
        String name = scanner.nextLine();

        System.out.println("Okay " + name + is your class?");
    }
}