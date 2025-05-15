public class engineer extends Character {
    boolean deployedDevice;

    public engineer(String r) {
        health = 200;
        damage = 15;
        race = r;
        intelligence = 10;
        initiative = 2;
        deployedDevice = false;
    }

    void build()


    void attack(Character target) {
        // attacking barbarian
        if (target instanceof barbarian) {
            barbarian bar = (barbarian) target;

        if (bar.isRaging) {
            
        }
            
        }
    }
}
