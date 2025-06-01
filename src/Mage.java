import java.util.Random;
import java.util.Scanner;

public class Mage extends Character {
    boolean hasSpellBook;
    
    public Mage(Race race, String name) {
		health = 100;
		damage = 5;

        sameConstructor(race, name);
	}

    public Mage(Race race, int health, int damage, String name) {
        this.health = health;
        this.damage = damage;
        
        sameConstructor(race, name);
    }

    public void sameConstructor(Race race, String name) {
        this.race = race;
        this.name = name;
        intelligence = 15;
        initiative = 10;
        maxHealth = health;
        maxDamage = damage;
        hasSpellBook = true;
        abilityPointsLeft = 7;
        abilityPointsMax = abilityPointsLeft;
        killCount = 0;
        isFleeing = false;
        combatBonusApplied = false;
    }

    @Override
    public void genChoices(Character target) {
        int choice = (int)(Math.random() * 5) + 1;

        // "2" is to heal. If NPC chooses 2 while on max health, re-gen choice.
        if (choice == 2 && health == maxHealth) {
            genChoices(target); 
            return;
        }
            
        if (choice < 5)
            useAbility(target, choice);

        if (choice == 5)
            attack(target);
    }

    @Override
    public void specialAbility(Character target) {
        if (abilityPointsLeft < 1) {
            System.out.println("Too tired to use special abilities.");
            System.out.println("Attacking normally instead.");
            attack(target);
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a spell to cast:");
        System.out.println("1. Poison - Reduces opponent's damage by 10 (and may blind a raging barbarian).");
        System.out.println("2. Heal - Restores random HP (30-90), up to max health.");
        System.out.println("3. Attack Boost - Increase Mage's damage by 10 temporarily, 5 permanently.");
        System.out.println("4. Unstable - Random damage to both enemy (30-90) and self (10-30).");

        int choice = scanner.nextInt();   
        useAbility(target, choice);
    }
    
    void useAbility(Character target, int choice) {
        if (abilityPointsLeft < 1 || !hasSpellBook) {
            System.out.println("Too tired to use special abilities or does not have spell book.");
            System.out.println("Attacking normally instead.");
            attack(target);
            return;
        }

        abilityPointsLeft--;
        
        switch (choice) {
            case 1:   
                poisonSpell(target);
                break;
            case 2:
                healSpell();
                break;
            case 3:
                attackBoostSpell();
                break;
            case 4: 
                unstableSpell(target);
                break;
            default:
                System.out.println("Invalid spell choice");
        }
    }

    void poisonSpell(Character target) {
        System.out.println(name + " casts Poison Spell!");

        if (target instanceof Barbarian) {
            Barbarian bar = (Barbarian) target;

            if (bar.isRaging) {
                bar.enterBlindedRampage();
                System.out.println(target.name + " enters a Blinded Rampage!");
            } else {
                if (bar.damage > 10) { // To prevent going into the negatives and healing enemies 
                    bar.damage -= 10;
                } else {
                    bar.damage = 1;
                }
                System.out.println(target.name + " damage has been reduced to: " + target.damage);
            }
        } else {
            if (!isInvisible(target)) {
                if (target.damage > 10) { // To prevent going into the negatives and healing enemies 
                    target.damage -= 10;
                } else {
                    target.damage = 1;
                }
                System.out.println(target.name + " damage has been reduced to: " + target.damage);
            } else {
                System.out.println(target.name + " is invisible and cannot be attacked!");
            }
        }
    }

    void healSpell() {
        System.out.println("Mage casts Heal Spell");
        Random rand = new Random();
        int potentialHeal = rand.nextInt(90) + 30; // 30-90

        // Ensures you don't go over maxHealth 
        int healed = Math.min(potentialHeal, maxHealth - health);
        health += healed;
        System.out.println(name + " heals for " + healed + ". Current HP:"  + health);
    }

    void attackBoostSpell() {
        /* Damage gets reset to maxDamage during rest state between dungeons.
         * So this permanently increases damage by 5 for entire game.
         * Increases damage by 10 for the duration of just this dungeon.
         */

        damage += 10;
        maxDamage += 5; 
        System.out.println(name + " boosted their damage to " + damage);
    }

    void unstableSpell(Character target) {
        Random rand = new Random();
        int damageToEnemy = rand.nextInt(61) + 30; // 30-90
        int damageToSelf = rand.nextInt(21) + 10; // 10-30

        System.out.println(name + " casts Unstable Spell!");

        if (!isInvisible(target)) {
            target.takeDamage(damageToEnemy);
            System.out.println("Deals: " + damageToEnemy + " to " + target.name + " and " + damageToSelf + " to self.");
        } else {
            System.out.println(target.name + " is invisible and was not attacked!");
            System.out.println(name + " takes " + damageToSelf + " damage to self.");
        }
        
        takeDamage(damageToSelf);

        if (health <= 0) {
            System.out.println(name + ": 'Oops!'");
            System.out.println(name + " has accidentally destroyed themselves.");
        } else {
            System.out.println(name + " HP: " + health);
        }
    }
    
    @Override
    void attack(Character target) {
        if (!isInvisible(target)) {
            target.takeDamage(damage);
            System.out.println(name + " attacked " + target.name + " with basic magic for " + damage + " points.");
        } else {
            System.out.println(target.name + " is invisible and cannot be attacked!");
        }
    }

    boolean isInvisible(Character target) {
        if (target instanceof Rogue) {
            Rogue rog = (Rogue) target;
            return rog.isInvisible;
        } 
        return false;
    }

    @Override
    void characterRest() {
        // Rest in between dungeons
        regainSpellBook();
    }

    void loseSpellBook() {
        hasSpellBook = false;
        System.out.println(name + "'s spellbook has been stolen!");
    }
    
    void regainSpellBook() {
        hasSpellBook = true;
        System.out.println(name + " has recovered their spellbook!");
    }




}

