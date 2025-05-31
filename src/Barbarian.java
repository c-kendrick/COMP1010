
public class Barbarian extends Character {
    boolean isRaging;
    boolean isBlindedRampage;


	public Barbarian(String race, String name) {
		this.race = race;
        this.name = name;

        health = 200;
        maxHealth = health;
		damage = 30;
        maxDamage = damage;
        intelligence = 5 ;
        initiative = 5 ;
        isRaging = false;
        isBlindedRampage = false;
        killcount = 0;
        specialAbLeft = 3;
        specialAbMax = specialAbLeft;
	}

    public Barbarian(String race, int health, int damage, int intelligence, int initiative, String name) {
        this.race = race;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.maxDamage = damage;
        this.intelligence = intelligence;
        this.initiative = initiative;
        this.name = name;

        specialAbLeft = 3;
        specialAbMax = specialAbLeft;
        killcount = 0;
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

    // to do delegate each attackEngineer, attackMage etc to make it readable
    @Override
    void attack(Character target) {
        // attacking engineer
        if (target instanceof Engineer) {
            Engineer eng = (Engineer) target;
            
            if (eng.deployedDevice && eng.builtDevice == "WALL" && eng.wallHealth > 0) {
                    if (isRaging) {
                        eng.wallHealth = 0;
                        eng.deployedDevice = false;
                    }
                    eng.wallHealth -= 25;
                    System.out.println("Wall health: 25"); // to do: make it so other clans can attack the wall too
                    if (eng.wallHealth <= 0) {
                        eng.deployedDevice = false;
                        System.out.println("Wall destroyed");
                    }
            } else {        
                System.out.println(name + " attacked " + eng.name + " for " + damage + " points");        
                eng.takeDamage(damage);
            }
        }

        // attacking rogue
        if (target instanceof Rogue) {
            Rogue rog = (Rogue) target;

            if (!rog.isInvisible) {
                System.out.println(name + " attacked " + rog.name + " for " + damage + " points");        
                rog.takeDamage(damage);
            } else {
                System.out.println("Cannot attack invisible rogue!");
            }
        }

        // attacking mage
        if (target instanceof Mage) {
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
        
        if (target instanceof Barbarian) {
            System.out.println(name + " attacked " + target.name + " for " + damage + " points.");
            target.takeDamage(damage);
        }
    }

    void activateRage() {
        isRaging = true;
        damage += 30;
        specialAbLeft--;
    }
    
    void deactivateRage() {
        isRaging = false;
        damage -= 30;
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
        if (specialAbLeft < 1) {
            System.out.println("You are too tired to rage. Attacking normally instead");
            attack(target);
        } else {
            System.out.println("BARBARIAN RAGING");

            activateRage();
            attack(target);
            deactivateRage();
        }


    }
	
    
}

