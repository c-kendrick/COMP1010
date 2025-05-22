
public class barbarian extends Character {
    boolean isRaging;

	public barbarian(String r) {
		health = 150;
		damage = 30;
		race = r;
        intelligence = 5;
        initiative = 5;
        isRaging = false;
	}

    @Override
    void attack(Character target) {
        // attacking engineer
        if (target instanceof engineer) {
            engineer eng = (engineer) target;
            
            if (eng.deployedDevice) {
                if (isRaging)
                    eng.deployedDevice = false;
            } else {        
                System.out.println("Barbarian attacked for " + damage + " points");        
                target.takeDamage(damage);
            }
        }

        // attacking rogue
        if (target instanceof rogue) {
            rogue rog = (rogue) target;

            if (!rog.isInvisible) {
                // cannot rage against rogue
                if (isRaging)
                    damage -=20;

                System.out.println("Barbarian attacked for " + damage + " points");        
                target.takeDamage(damage);
            }
        }

        // attacking mage
        if (target instanceof mage) {
            if (isRaging) {
                // if mage is using spell
                // chance for mage spell to double barbarian damage
            }

            System.out.println("Barbarian attacked for " + damage + " points");
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

    //rage attack special ability 
    @Override
    void specialAbility(Character target) {
        System.out.println("BARBARIAN RAGING");

        activateRage();
        attack(target);
        deactivateRage();
    }
	
    
}

