import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Mage extends Character {
    boolean hasSpellBook;
    ArrayList<String> spells;
    int maxHealth;
    boolean attackBoostActive;
    boolean poisonUsed;
    

    public Mage(String race, String name) {
		this.race = race;
		this.name = name;
		health = 150;
        maxHealth = health;
        maxHealth = 150;
		damage = 15;
        maxDamage = damage;
        intelligence = 10;
        initiative = 3;
        hasSpellBook = true;
        attackBoostActive = false;
        poisonUsed = false;
        killcount = 0;
        specialAbLeft = 7;
        specialAbMax = specialAbLeft;

        spells = new ArrayList<>();
        spells.add("Poison");
        spells.add("Heal");
        spells.add("Attack Boost");
        spells.add("Unstable");
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
        attackBoostActive = false;
        poisonUsed = false;

        spells = new ArrayList<>();
        spells.add("Poison");
        spells.add("Heal");
        spells.add("Attack Boost");
        spells.add("Unstable");
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
            castUnstableSpell(target);
            return;
        }

        System.out.println("Choose a spell to cast:");
        System.out.println("1. Poison - Reduces opponent's damage (and may blind a raging barbarian)");
        System.out.println("2. Heal - Restores 10 HP (max 200)");
        System.out.println("3. Attack Boost - Increase Mage's damage by 10");
        System.out.println("4. Unstable - Random damage to both enemy and self");

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
        
        // to do: delegate
        switch (choice) {
            case 1:
                if (poisonUsed) {
                    System.out.println("Poison spell has already been used and cannot be used again.");
                    break;
                }
                
                System.out.println("Mage casts Poison Spell!");
                if (target instanceof Barbarian) {
                    Barbarian bar = (Barbarian) target;
                    if (bar.isRaging) {
                        bar.enterBlindedRampage();
                        System.out.println("Barbarian enters a Blinded Rampage!");
                    } else {
                        if (bar.damage > 10) { // to prevent going into the negatives and healing enemies 
                            bar.damage -= 10;
                            System.out.println("Barbarian's damage is reduced by 10.");
                        } else {
                            bar.damage = 1;
                        }
                    }
                } else {
                    if (target.damage > 10) { // to prevent going into the negatives and healing enemies 
                        target.damage -= 10;
                        System.out.println("Barbarian's damage is reduced by 10.");
                    } else {
                        target.damage = 1;
                    }
                    System.out.println(target.getClass().getSimpleName() + "'s damage is reduced by 10.");
                }
                poisonUsed = true;
                break;
                
            case 2:
                System.out.println("Mage casts Heal Spell");
                int healed = Math.min(20, maxHealth - health);
                health += healed;
                System.out.println("Mage heals for " + healed + ". Current HP:"  + health);
                break;
            case 3:
                if (!attackBoostActive) {
                    damage += 10;
                    attackBoostActive = true;
                    System.out.println("Mage's attack damage increases by 10");
                } else {
                    System.out.println("Attack Boost is already active");
                }
                break;
            case 4: 
                castUnstableSpell (target);
                break;
            default:
                System.out.println("Invalid spell choice");
        }
    }

    void castUnstableSpell(Character target) {
        Random rand = new Random();
        int damageToEnemy = rand.nextInt(11) + 15; //15-25
        int damageToSelf = rand.nextInt(11) + 10; //10-20

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

    void loseSpellBook() {
        hasSpellBook = false;
        System.out.println("Mage's spellbook has been stolen!");
    }
    
    void regainSpellBook() {
        hasSpellBook = true;
        System.out.println("Mage has recovered their spellbook!");
    }


}

