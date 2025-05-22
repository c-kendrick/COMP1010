public class Character {
    int health;
    int damage;
    int intelligence;
    int initiative;
    String race;
    Boolean isWounded;
    Boolean isPoisoned;

    public Character() {

    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    
    void attack(Character target) {
    }

    void specialAbility(Character target) {

    }
}
