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

    boolean flee(Character target) {
        int num;
        if (initiative < target.initiative) {
            num = (int)(Math.random() * 10) + 1;
            if (num > 7) {
                return true;
            }
            return false;
        } 
        
        if (initiative == target.initiative) {
            num = (int)(Math.random() * 10) + 1;
            if (num > 5) {
                return true;
            } else {
                return false;
            }
        }
        
        if (initiative > target.initiative) {
            num = (int)(Math.random() * 10) + 1;
            if (num > 3) {
                return true;
            } else {
                return false;
            }
        }
        
        return true;
    }
}
