import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Mage extends Character {
    boolean hasSpellBook;
    ArrayList<String> spells;
    

    public Mage(String race, String name) {
		this.race = race;
		this.name = name;
		health = 100;
        maxHealth = health;
		damage = 5;
        maxDamage = damage;
        intelligence = 10;
        initiative = 3;
        hasSpellBook = true;
        killcount = 0;
        specialAbLeft = 7;
        specialAbMax = specialAbLeft;
        killcount = 0;
        
	}

    public Mage(String race, int health, int damage, int intelligence, int initiative, String name) {
        this.race = race;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.intelligence = intelligence;
        this.initiative = initiative;

        specialAbLeft = 7;
        specialAbMax = specialAbLeft;
        maxHealth = health;
        maxDamage = damage;
        hasSpellBook = true;
    }

    @Override
    public void genChoices(Character target) {
        int choice = (int)(Math.random() * 5) + 1;

        if (choice < 5)
            useAbility(target, choice);

        if (choice == 5)
            attack(target);

    }

    @Override
    public void specialAbility(Character target) {
        if (specialAbLeft < 1) {
            System.out.println("Too tired to use special abilities.");
            System.out.println("Attacking normally instead.");
            attack(target);
            return;
        }

        Scanner scanner = new Scanner(System.in);

        if (!hasSpellBook) {
            System.out.println("Spellbook is stolen! You can only cast the Unstable Spell.");
            unstableSpell(target);
            return;
        }

        System.out.println("Choose a spell to cast:");
        System.out.println("1. Poison - Reduces opponent's damage by 10 (and may blind a raging barbarian).");
        System.out.println("2. Heal - Restores random HP (30-90), up to max health.");
        System.out.println("3. Attack Boost - Increase Mage's damage by 10 temporarily, 5 permanently.");
        System.out.println("4. Unstable - Random damage to both enemy (30-90) and self (10-30).");

        int choice = scanner.nextInt();   
        useAbility(target, choice);

    }

    
    void useAbility(Character target, int choice) {
        if (specialAbLeft < 1) {
            System.out.println("Too tired to use special abilities.");
            System.out.println("Attacking normally instead.");
            attack(target);
            return;
        }

        specialAbLeft--;
        
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
                if (bar.damage > 10) { // to prevent going into the negatives and healing enemies 
                    bar.damage -= 10;
                    System.out.println(target.name + " damage has been reduced to: " + target.damage);
                } else {
                    bar.damage = 1;
                }
            }
        } else {
            if (target.damage > 10) { // to prevent going into the negatives and healing enemies 
                target.damage -= 10;
            } else {
                target.damage = 1;
            }
            System.out.println(target.name + " damage has been reduced to: " + target.damage);
        }
    }

    void healSpell() {
        System.out.println("Mage casts Heal Spell");
        Random rand = new Random();
        int potentialHeal = rand.nextInt(90) + 30; // 30-90
        int healed = Math.min(potentialHeal, maxHealth - health);
        health += healed;
        System.out.println("Mage heals for " + healed + ". Current HP:"  + health);
    }

    void attackBoostSpell() {
        damage += 10;
        maxDamage += 5; // damage gets reset to maxDamage during rest state
    }

    void unstableSpell(Character target) {
        Random rand = new Random();
        int damageToEnemy = rand.nextInt(61) + 30; //30-90
        int damageToSelf = rand.nextInt(21) + 10; //10-30

        System.out.println(name + " casts Unstable Spell!");
        System.out.println("Deals: " + damageToEnemy + " to " + target.name + " and " + damageToSelf + " to self.");

        target.takeDamage(damageToEnemy);
        this.takeDamage(damageToSelf);

        if (health <= 0) {
            System.out.println(name + ": 'Oops!'");
            System.out.println(name + " has accidentally destroyed themselves.");
        } else {
            System.out.println(name + " HP: " + health);
        }
    }
    
    @Override
    void attack(Character target) {
        target.takeDamage(damage);
        System.out.println(name + " attacked " + target.name + " with basic magic for " + damage + " points.");

    }

    @Override
    void characterRest() {
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

