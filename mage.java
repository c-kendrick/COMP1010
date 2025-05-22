public class mage extends Character {
    

    public mage(String r) {
		health = 150;
		damage = 30;
		race = r;
        intelligence = 5;
        initiative = 5;
	}

    void attack(Character target) {
        // attacking engineer
        if (target instanceof engineer) {

        }

        if (target instanceof rogue) {

        }

        if (target instanceof barbarian) {

        }
    }

    void spellAttack(Character target) {
        
    }

    void spellWeaken(Character target) {

    }

    void spellHeal() {
        if (health < 180) {
            health += 20;
        } else {
            health = 200;
        }
    }

}
