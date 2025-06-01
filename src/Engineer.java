import java.util.Scanner;

public class Engineer extends Character {
    int wallHealth;
    boolean RRBuilt;
    boolean wallBuilt;
    boolean trackerBuilt;
    boolean PCBuilt;
    int numAttack;

    public Engineer( Race race, String name) {
        health = 175;
        damage = 15;

        sameConstructor(race, name);
    }

    public Engineer( Race race, int health, int damage, String name) {
        this.health = health;
        this.damage = damage;
        sameConstructor(race, name);
    }

    public void sameConstructor(Race race, String name) {
        this.race = race;
        this.name = name;
        intelligence = 20;
        initiative = 5;
        maxHealth = health;
        maxDamage = damage;
        wallHealth = 0;
        killCount = 0;
        abilityPointsLeft = 3;
        abilityPointsMax = abilityPointsLeft;
        numAttack = 0; 
        RRBuilt = false;
        wallBuilt = false;
        trackerBuilt = false;
        PCBuilt = false;
        isFleeing = false;
        combatBonusApplied = 0;
    }

    @Override
    void genChoices(Character target) {
        numAttack++;

        //On first attack, NPC will always build instead of attacking.
        if (numAttack == 1) {
            if (target instanceof Barbarian)
                buildWall(target);
            
            if (target instanceof Rogue)
                buildTracker(target);
            
            if (target instanceof Mage)
                buildRoboRogue(target);

            if (target instanceof Engineer)
                buildPC(target);
            
            return;
        }

        // From second attack onwards, NPC will either attack, or build Power-Core (which can stack effects, unlike other buildings).
        int choice = (int)(Math.random() * 3) + 1;
        if (choice == 1)
            buildPC(target);

        if (choice > 1)
            attack(target);
    }

    @Override
    void specialAbility(Character target) {
        System.out.println("Choose a device to build:");
        System.out.println("1. RoboRogue - Steals all Mage's spellbook");
        System.out.println("2. Tracker - Reveals all invisible Rogues");
        System.out.println("3. Wall - Defend against Barbarian");
        System.out.println("4. Power Core - Increases damage temporarily by 10, permanently by 5");

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
        if (!RRBuilt && abilityPointsLeft > 0) {
            abilityPointsLeft--;
            RRBuilt = true;

            System.out.println(name + " built a RoboRogue.");
            if (target instanceof Mage) {
                Mage mage = (Mage) target;
                RRSteal(mage);
            }
        } else {
            System.out.println(name + " already built a RoboRogue or out of Ability points, attacking normally instead");
            attack(target);
        }
    }

    void buildTracker(Character target) {
        if (!trackerBuilt && abilityPointsLeft > 0) {
            abilityPointsLeft--;
            trackerBuilt = true;

            System.out.println(name + " built a Tracker.");
            if (target instanceof Rogue) {
                Rogue rog = (Rogue) target;
                trackRogue(rog);
            }
        } else {
            System.out.println(name + " already built a Tracker or out of Ability points, attacking normally instead");
            attack(target);
        }
    }

    void buildPC(Character target) {
        if (abilityPointsLeft > 0) {
            abilityPointsLeft--;
            PCBuilt = true;

            System.out.println(name + " built a Power-Core.");
            damage += 10;
            maxDamage += 5;
        } else {
            System.out.println(name + " out of Ability points, attacking normally instead");
            attack(target);
        }
    }

    void buildWall(Character target) {
        if (!wallBuilt && abilityPointsLeft > 0) {
            abilityPointsLeft--;
            wallBuilt = true;
            wallHealth = 150;

            System.out.println(name + " built a wall with " + wallHealth + " HP");
        } else {
            System.out.println(name + " already built a Wall or out of Ability points, attacking normally instead");
            attack(target);
        }
    }

    @Override
    void characterRest() {
        // Rest in between dungeons
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
        RRSteal(mage);
        System.out.println(name + " attacked " + mage.name + " for " + damage + " points");      
        mage.takeDamage(damage);
    }

    void RRSteal(Mage mage) {
        if (RRBuilt && mage.hasSpellBook) {
            System.out.println("RoboRogue stole mage's spellbook!");
            mage.loseSpellBook();
        }
    }

    void attackRogue(Rogue rog) {
        trackRogue(rog);

        if (!rog.isInvisible || trackerBuilt) { 
            System.out.println(name + " attacked " + rog.name + " for " + damage + " points");      
            rog.takeDamage(damage);
        } else {
            System.out.println(name + " cannot attack invisible rogue!");
        }
    }

    void trackRogue(Rogue rog) {
        if (trackerBuilt && rog.isInvisible) {
            rog.isInvisible = false;
            System.out.println("Tracker revealed the Rogue!");
        }
    }
}
