public class Rogue extends Character {
    int invisibilityStatus;
    boolean isInvisible;
    boolean hasStolenSpellBook;

	public Rogue(String race, String name) {
		this.race = race;
        this.name = name;
        
        health = 100;
		damage = 15;
        intelligence = 5;
        initiative = 20;

        maxHealth = health;
        maxDamage = damage;

        isInvisible = false; 
        hasStolenSpellBook = false; 
        killcount = 0;
        specialAbLeft = 5;
        invisibilityStatus = 0;
        isFleeing = false;
	}
	
    public Rogue(String race, int health, int damage, int intelligence, int initiative, String name) {
        this.race = race;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.intelligence = intelligence;
        this.initiative = 20; 
        maxHealth = health;
        maxDamage = damage;
        killcount = 0;
        specialAbLeft = 5;
        specialAbMax = specialAbLeft;
        invisibilityStatus = 0;
        isFleeing = false;
    }


    @Override
	void attack (Character target){

        if (!isEnemyInvisible(target)) {
            checkInvisStatus();
            target.takeDamage(damage);
            System.out.println(name + " attacked " + target.name + " for " + damage + " points.");
        } else {
            System.out.println(name + " is invisible and cannot be attacked");
        }
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
    void specialAbility(Character target){ 
        if (!isInvisible) {
            activateInvisibilitiy();
            return;
        }

        if (target instanceof Mage) {
            checkInvisStatus();
            Mage m = (Mage) target;
            if (m.hasSpellBook);
            m.loseSpellBook();
            hasStolenSpellBook = true; 
            System.out.println("Rogue has stolen the Mage's Spell Book");
        } else {
            attack(target);
        }
    }

    void checkInvisStatus() {
        if (invisibilityStatus > 0) {                    
            invisibilityStatus--;
            System.out.println("Rogue is still invisible");
        } else {
            deactivateInvisibility();
        }
    }

    void activateInvisibilitiy() {
        invisibilityStatus = 1;
        System.out.println(name + " has turned invisible");
        isInvisible = true;
    }

    void deactivateInvisibility() {
        System.out.println("Rogue is now VISIBLE");
        isInvisible = false;
    }

    boolean isEnemyInvisible(Character target) {
        if (target instanceof Rogue) {
            Rogue rog = (Rogue) target;
            return rog.isInvisible;
        } 
        return false;
    }
}

