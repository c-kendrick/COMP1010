import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class mage extends Character {
    boolean hasSpellBook;
    ArrayList<String> spells;
    int maxHealth;
    boolean attackBoostActive;
    boolean poisonUsed;
    

    public mage(String r) {
		health = 200;
        maxHealth = 200;
		damage = 15;
		race = r;
        intelligence = 10;
        initiative = 3;
        hasSpellBook = true;
        attackBoostActive = false;
        poisonUsed = false;

        spells = new ArrayList<>();
        spells.add("Poison");
        spells.add("Heal");
        spells.add("Attack Boost");
        spells.add("Unstable");
	}
    public mage(String race, int health, int damage, int intelligence, int initiative) {
        this.race = race;
        this.health = health;
        this.damage = damage;
        this.intelligence = intelligence;
        this.initiative = initiative;

        hasSpellBook = true;
        attackBoostActive = false;
        poisonUsed = false;
    }

    @Override
    void specialAbility(Character target) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        if (!hasSpellBook) {
            System.out.println("Spellbook is stolen! You can only cast the Unstable Spell.");
            castUnstableSpell(target, rand);
            return;
        }

        System.out.println("Choose a spell to cast:");
        System.out.println("1. Poison - Reduces opponent's damage  (and may blind a raging barbarian)");
        System.out.println("2. Heal - Restores 10 HP (max 200)");
        System.out.println("3. Attack Boost - Increase Mage's damage by 10");
        System.out.println("4. Unstable - Random damage to both enemy and self");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                if (poisonUsed) {
                    System.out.println("Poison spell has already been used and cannot be used again.");
                    break;
                }
                
                System.out.println("Mage casts Poison Spell!");
                if (target instanceof barbarian) {
                    barbarian bar = (barbarian) target;
                    if (bar.isRaging) {
                        bar.enterBlindedRampage();
                        System.out.println("Barbarian enters a Blinded Rampage!");
                    } else {
                        bar.damage -= 10;
                        System.out.println("Barbarian's damage is reduced by 10.");
                    }
                } else {
                    target.damage -= 10;
                    System.out.println(target.getClass().getSimpleName() + "'s damage is reduced by 10.");
                }
                poisonUsed = true;
                break;
            case 2:
                System.out.println("Mage casts Heal Spell");
                int healed = Math.min(10, maxHealth - health);
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
                castUnstableSpell (target, rand);
                break;
            default:
                System.out.println("Invalid spell choice");
        }
    }
    void castUnstableSpell(Character target, Random rand) {
        int damageToEnemy = rand.nextInt(11) + 15; //15-25
        int damageToSelf = rand.nextInt(11) + 10; //10-20

        System.out.println("Mage casts Unstable Spell!");
        System.out.println("Deals " + damageToEnemy + " to enemy and " + damageToSelf + " to self.");

        target.takeDamage(damageToEnemy);
        this.takeDamage(damageToSelf);
    }
    
    void loseSpellBook() {
        hasSpellBook = false;
        System.out.println("Mage's spellbook has been stolen!");
    }
    
    void regainSpellBook() {
        hasSpellBook = true;
        System.out.println("Mage has recovered their spellbook!");
    }

    void attack(Character target) {
        System.out.println("Mage attacks with basic magic!");
        target.takeDamage(damage);
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println("Mage takes " + damage + " damage. Remaining HP: " + this.health);
    }

}

