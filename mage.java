public class mage extends Character {
    boolean spellUsed;

    public mage(String r) {
		health = 200;
		damage = 15;
		race = r;
        intelligence = 5;
        initiative = 4;
	}

    void spellAttack(Character target) {
        if (target instanceof engineer) {
            engineer eng = (engineer) target;

            if (eng.deployedDevice) {

            } else {
                target.takeDamage(damage);
            }


        }

        if (target instanceof rogue) {
            target.takeDamage(damage);
        }

        if (target instanceof barbarian) {
            spellUsed = true;
            target.takeDamage(damage);
        }
    }

    void spellWeaken(Character target) {
        target.weaken(damage);
        statusEffect weakenSpell = new statusEffect(5, target, "Weakness", damage);
        

    }


    void spellHeal() {

    }

}
