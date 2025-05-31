import java.util.Scanner;

public class Engineer extends Character {
    String builtDevice;
    int wallHealth;

    boolean RRBuilt;
    boolean wallBuilt;
    boolean trackerBuilt;
    boolean PCBuilt;

    public Engineer(String race, String name) {
        this.race = race;
        this.name = name;

        health = 100;
        damage = 15;
        intelligence = 6;
        initiative = 5;
        builtDevice = "";
        wallHealth = 0;
        killcount = 0;
        maxHealth = health;
        maxDamage = damage;
        specialAbLeft = 3;
        specialAbMax = specialAbLeft;

        RRBuilt = false;
        wallBuilt = false;
        trackerBuilt = false;
        PCBuilt = false;
    }

    public Engineer(String race, int health, int damage, int intelligence, int initiative, String name) {
        this.race = race;
        this.health = health;
        this.damage = damage;
        this.intelligence = intelligence;
        this.initiative = initiative;
        this.name = name;

        specialAbLeft = 3;
        specialAbMax = specialAbLeft;
        maxHealth = health;
        maxDamage = damage;
        builtDevice = "";
        wallHealth = 0;
        killcount = 0;

        RRBuilt = false;
        wallBuilt = false;
        trackerBuilt = false;
        PCBuilt = false;
    }

    @Override
    void genChoices(Character target) {
        int choice = (int)(Math.random() * 5) + 1;

        if (choice < 5)
            useAbility(target, choice);

        if (choice == 5)
            attack(target);

    }

    @Override
    void specialAbility(Character target) {
        System.out.println("Choose a device to build:");
        System.out.println("1. RoboRogue (AP Cost: 2) - Steals all mage's spellbook");
        System.out.println("2. Tracker (AP Cost: 3) - Reveals an invisible Rogue & doubles your damage for this dungeon only.");
        System.out.println("3. Wall (AP Cost: 1) - Defend against Barbarian");
        System.out.println("4. Power Core (AP Cost: 1) - Increases damage temporarily by 10, permanently by 5");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        useAbility(target, choice);
    }
    
    void useAbility(Character target, int choice) {
        switch (choice) {
            case 1:
                buildRoboRogue(target);
                break;
            case 2: 
                buildTracker(target);
                break;
            case 3:
                buildWall(target);   
                break;
            case 4:
                buildPC(target);
                break;
            default:
                System.out.println("Invalid Choice. No device built.");
        }
    }

    void buildRoboRogue(Character target) {
        if (!RRBuilt && specialAbLeft > 1) {
            specialAbLeft -= 2;
            RRBuilt = true;
            System.out.println(name + " built a RoboRogue.");
        } else {
            System.out.println("Already built or out of Ability points, attacking normally instead");
            attack(target);
        }
    }

    void buildTracker(Character target) {
        if (!trackerBuilt && specialAbLeft > 2) {
            specialAbLeft -= 3;
            trackerBuilt = true;
            damage += damage;
            System.out.println(name + " built a Tracker.");
        } else {
            System.out.println("Already built or out of Ability points, attacking normally instead");
            attack(target);
        }
    }

    void buildPC(Character target) {
        if (specialAbLeft > 0) {
            specialAbLeft--;
            PCBuilt = true;
            System.out.println(name + " built a Power Core.");
            damage += 10;
            maxDamage += 5;
        }
    }

    void buildWall(Character target) {
        if (!wallBuilt && specialAbLeft > 0) {
            specialAbLeft--;
            wallBuilt = true;
            wallHealth = 150;
            System.out.println(name + " built a wall with " + wallHealth + " HP");
        } else {
            System.out.println("Already built or out of Ability points, attacking normally instead");
            attack(target);
        }
    }

    @Override
    void characterRest() {
        RRBuilt = false;
        trackerBuilt = false;
        wallBuilt = false;
        PCBuilt = false;
        System.out.println("All of your devices have been disassembled.");
    }

    @Override
    void attack(Character target) {
        if (target instanceof Barbarian || target instanceof Engineer) {
            System.out.println(name + " attacked " + target.name + " for " + damage + " points");        
            target.takeDamage(damage);
        }
            
        if (target instanceof Mage) {
            Mage mage = (Mage) target;
            attackMage(mage);
        }
        
        if (target instanceof Rogue) {
            Rogue rog = (Rogue) target;
            attackRogue(rog);
        } 
    }

    void attackMage(Mage mage) {
        if (RRBuilt) {
            System.out.println("RoboRogue stole mage's spellbook!");
            mage.loseSpellBook();
        }

        System.out.println(name + " attacked " + mage.name + " for " + damage + " points");      
        mage.takeDamage(damage);
    }

    void attackRogue(Rogue rog) {
        if (trackerBuilt && rog.isInvisible) {
            rog.isInvisible = false;
            System.out.println("Tracker revealed the Rogue!");
        }

        if (!rog.isInvisible) { // if no tracker is built, rogue is still invisible {}
            System.out.println(name + " attacked " + rog.name + " for " + damage + " points");      
            rog.takeDamage(damage);
        }
    }
}
