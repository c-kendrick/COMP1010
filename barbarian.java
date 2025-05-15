
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

    void attack(Character target) {
        // attacking engineer
        if (target instanceof engineer) {
            engineer eng = (engineer) target;
            
            if (eng.deployedDevice) {
                if (isRaging)
                    eng.deployedDevice = false;
            } else {                
                target.takeDamage(damage);
            }

            
            
        }

        // attacking rogue
        if (target instanceof rogue) {
            rogue rog = (rogue) target;
            if (!rog.isInvisible) {
                target.takeDamage(damage);
            }
        }

        // attacking mage
        if (target instanceof mage) {
            
        }
    }

    void rageAttack(Character target) {
        isRaging = true;
        damage += 20;
        attack(target);
        damage -= 20;
        isRaging = false;
    }
	
    
}

