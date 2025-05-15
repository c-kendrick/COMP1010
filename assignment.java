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

    void attack(Character target, int amount) {
        target.takeDamage(amount);
    }


}   
    




public class assignment {


    public static void main(String[] args) {
        barbarian john = new barbarian("John");
        rogue alex = new rogue("Alex");
        System.out.println(alex.health);
        john.attack(alex);
        System.out.println(alex.health);
        
    }
}