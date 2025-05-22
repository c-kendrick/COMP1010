public class Mage extends Character {
    boolean spellUsed;

    public Mage(String r) {
		health = 200;
		damage = 15;
		race = r;
        intelligence = 5;
        initiative = 4;
	}

    void spellAttack(Character target) {
        if (target instanceof Engineer) {
            Engineer eng = (Engineer) target;

            if (eng.deployedDevice) {

            } else {
                target.takeDamage(damage);
            }


        }

        if (target instanceof Rogue) {
            target.takeDamage(damage);
        }

        if (target instanceof Barbarian) {
            spellUsed = true;
            target.takeDamage(damage);
        }
    }

    void spellWeaken(Character target) {
        target.weaken(damage);
        StatusEffect weakenSpell = new StatusEffect(5, target, "Weakness", damage);
        

    }


    void spellHeal() {

    }

}
