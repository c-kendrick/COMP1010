import java.util.Random;

public class Enemy extends Character {
    boolean isBoss;
    
    public Enemy(String race, int base, boolean isBoss) {
        this.race = race;
        this.isBoss = isBoss;

        Random r= new Random();
        health = ((int)(Math.random() * 5) + 1) * base;
        damage = ((int)(Math.random() * 5) + 1) * base;
        intelligence = base;
        initiative = base;
    }

    @Override
    void attack(Character target) {
        if (isBoss) {
            
        }
        target.takeDamage(damage);
    }

    
}
