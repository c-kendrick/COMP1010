public class Character {
    int health;
    int damage;
    int intelligence;
    int initiative;
    String race;
    Boolean isWounded;
    Boolean isPoisoned;

    // create constructor

    public void takeDamage(int damage) {
        health -= damage;
    }

    
    void attack(Character target, int amount) {
        target.takeDamage(amount);
    }
}
