public class Engineer extends Character {
    boolean deployedDevice;

    public Engineer(String r) {
        health = 200;
        damage = 15;
        race = r;
        intelligence = 10;
        initiative = 2;
        deployedDevice = false;
    }

    void build() {
        
    }


    void attack(Character target) {
        // attacking barbarian
        if (target instanceof Barbarian) {
            Barbarian bar = (Barbarian) target;

        if (bar.isRaging) {
            
        }
            
        }
    }
}
