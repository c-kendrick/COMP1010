import java.util.ArrayList;

class Character {
    int health;
    int damage;
    int intelligence;
    int initiative;
    String race;
    Boolean isWounded;
    Boolean isPoisoned;

    //ArrayList<String> inventory = new ArrayList<>();


    void takeDamage(int amount) {
        health -= amount;
    }

    void weaken(int amount) {
        damage -= amount;
    }

    void attack(Character target, int amount) {
        target.takeDamage(amount);
    }


}   
    
class statusEffect {
    int turnsLeft;
    Character target;
    String effect;
    // info used for damage amount or other numbers needed
    int info;

    public statusEffect(int turnsLeft, Character target, String effect, int info) {
        this.turnsLeft = turnsLeft;
        this.target = target;
        this.effect = effect;
        this.info = info;
    }
}



public class assignment {

    public void turnCounter() {
        ArrayList<statusEffect> effects = new ArrayList<>();
    }


    public static void main(String[] args) {
        barbarian john = new barbarian("John");
        rogue alex = new rogue("Alex");
        System.out.println(alex.health);
        john.attack(alex);
        System.out.println(alex.health);
        
    }
}