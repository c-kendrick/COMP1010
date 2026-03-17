public class Rogue extends Character {
    int invisibilityStatus;
    boolean isInvisible;
    boolean hasStolenSpellBook;

	public Rogue(Race race, String name) {
        health = 100;
		damage = 15;

        sameConstructor(race, name);
	}
	
    public Rogue(Race race, int health, int damage, String name) {
        this.health = health;
        this.damage = damage;

        sameConstructor(race, name);
    }

    public void sameConstructor(Race race, String name) {
        this.race = race;
        this.name = name;
        initiative = 20;
        intelligence = 5;
        maxHealth = health;
        maxDamage = damage;
        isInvisible = false; 
        hasStolenSpellBook = false; 
        killCount = 0;
        abilityPointsLeft = 5;
        abilityPointsMax = abilityPointsLeft;
        invisibilityStatus = 0;
        isFleeing = false;
        combatBonusApplied = 0;
    }

    @Override
	void attack (Character target){
        if (!isEnemyInvisible(target)) {
            checkOwnInvisStatus();
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

        /* Only if ALREADY invisible
         * Rogue steals Mage's spellbook
         */
        if (target instanceof Mage mage) {
            checkOwnInvisStatus();
            if (mage.hasSpellBook) {
                mage.loseSpellBook();
                hasStolenSpellBook = true;
                System.out.println("Rogue has stolen the Mage's Spell Book");
            }
        } else {
            attack(target);
        }
    }

    void checkOwnInvisStatus() {
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
        if (target instanceof Rogue rogue) {
            return rogue.isInvisible;
        } 
        return false;
    }


/*    boolean isEnemyInvisible(Character target) {
        if (target instanceof Rogue) {
            Rogue rogue = (Rogue) target;
            return rogue.isInvisible;
        }
        return false;
    }*/
}

