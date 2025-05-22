public class Character {
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
