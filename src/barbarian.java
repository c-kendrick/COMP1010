
public class barbarian extends Character {
    boolean isRaging;
    boolean isBlindedRampage;
    boolean hasRaged;

	public barbarian(String r) {
		health = 150;
		damage = 30;
		race = r;
        intelligence = 5;
        initiative = 5;
        isRaging = false;
        isBlindedRampage = false;
        hasRaged = false;
	}

    public barbarian(String race, int health, int damage, int intelligence, int initiative) {
        this.race = race;
        this.health = health;
        this.damage = damage;
        this.intelligence = intelligence;
        this.initiative = initiative;

        isRaging = false;
        isBlindedRampage = false;
    }

    @Override
    void genChoices(Character target) {
        int choice = (int)(Math.random() * 2) + 1;

        if (choice == 1)
            attack(target);

        if (choice == 2) 
            specialAbility(target);

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
            if (isBlindedRampage) {
                // if mage is using spell
                // chance for mage spell to double barbarian damage
                System.out.println("Barbarian is in Blinded Rampage! 50% chance to hit Mage.");
                if (Math.random() < 0.5) {
                    System.out.println("Blinded Rampage hit the Mage for" + damage + " points!");
                    target.takeDamage(damage);
                } else {
                    System.out.println("Blinded Rampage missed the Mage!");
                } 
            } else {
                System.out.println("Barbarian attacked Mage for " + damage + " points.");
                target.takeDamage(damage);
            }
        }
    }

    void activateRage() {
        isRaging = true;
        damage += 20;
        hasRaged = true;
    }
    
    void deactivateRage() {
        isRaging = false;
        damage -= 20;
    }

    void enterBlindedRampage() {
        isRaging = true;
        isBlindedRampage = true;
        damage += 10;
        System.out.println("Barbarian has entered Blinded Rampage!");
    }

    void exitBlindedRampage() {
        if (isBlindedRampage) {
            damage -= 10;
            isBlindedRampage = false;
            System.out.println("Barbarian exits Blinded Rampage");
        }
    }

    //rage attack special ability 
    @Override
    void specialAbility(Character target) {
        if (hasRaged) {
            System.out.println("You are too tired to rage. Attacking normally instead");
            attack(target);
        } else {
            System.out.println("BARBARIAN RAGING");

            activateRage();
            attack(target);
            deactivateRage();
            exitBlindedRampage();   
            // to do: turn hasRaged to false at the end of dungeon.
        }


    }
	
    
}

