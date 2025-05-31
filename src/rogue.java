public class Rogue extends Character {
    int invisibilityStatus;
    boolean isInvisible;
    boolean hasStolenSpellBook;

	public Rogue(String race, String name) {
		this.race = race;
        this.name = name;
        
        health = 100;
        maxHealth = health;
		damage = 15;
        maxDamage = damage;
        intelligence = 5;
        initiative = 10;
        isInvisible = false; 
        hasStolenSpellBook = false; 
        killcount = 0;
        specialAbLeft = 5;
        invisibilityStatus = 0;
        
	}
	
    public Rogue(String race, int health, int damage, int intelligence, int initiative, String name) {
        this.race = race;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.intelligence = intelligence;
        this.initiative = initiative; 
        maxHealth = health;
        maxDamage = damage;
        killcount = 0;
        specialAbLeft = 5;
        specialAbMax = specialAbLeft;
        invisibilityStatus = 0;
    }


    @Override
	void attack (Character target){

        if (invisibilityStatus > 0) {
            System.out.println("Rogue is still invisible");
            invisibilityStatus--;
        } else {
            System.out.println("Rogue is now VISIBLE");
            deactivateInvisibility();
        }
        
        if (target instanceof Engineer) {  
            target.takeDamage(damage);
        }
 
        if (target instanceof Rogue) {  
            target.takeDamage(damage);
        }

        if (target instanceof Mage) {
			target.takeDamage(damage);
        }

        if (target instanceof Barbarian) {
			target.takeDamage(damage);  
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
        } else {
            if (invisibilityStatus > 0) {
                invisibilityStatus--;
                System.out.println("Rogue is still invisible");
            } else {
                System.out.println("Rogue is now VISIBLE");
                deactivateInvisibility();
            }

            if (target instanceof Mage) {
                Mage m = (Mage) target;
                if (m.hasSpellBook);
                m.loseSpellBook();
                hasStolenSpellBook = true; 
                System.out.println("Rogue has stolen the Mage's Spell Book");
            }
        }
    }

    void activateInvisibilitiy() {
        invisibilityStatus = 1;
        System.out.println(name + " has turned invisible");
        isInvisible = true;
    }

    void deactivateInvisibility() {
        isInvisible = false;
    }
}

