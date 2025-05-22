
public class Barbarian extends Character {
    boolean isRaging;

	public Barbarian(String r) {
		health = 150;
		damage = 30;
		race = r;
        intelligence = 5;
        initiative = 5;
        isRaging = false;
	}

    void attack(Character target) {
        // attacking engineer
        if (target instanceof Engineer) {
            Engineer eng = (Engineer) target;
            
            if (eng.deployedDevice) {
                if (isRaging)
                    eng.deployedDevice = false;
            } else {                
                target.takeDamage(damage);
            }
        }

        // attacking rogue
        if (target instanceof Rogue) {
            Rogue rog = (Rogue) target;

            if (!rog.isInvisible) {
                // cannot rage against rogue
                if (isRaging)
                    damage -=20;

                target.takeDamage(damage);
            }
        }

        // attacking mage
        if (target instanceof Mage) {
            if (isRaging) {
                // if mage is using spell
                // chance for mage spell to double barbarian damage
            }

            target.takeDamage(damage);
        }
    }

    void activateRage() {
        isRaging = true;
        damage += 20;
    }
    
    void deactivateRage() {
        isRaging = false;
        damage -= 20;
    }

    void rageAttack(Character target) {
        activateRage();
        attack(target);
        deactivateRage();
    }
	
    
}

