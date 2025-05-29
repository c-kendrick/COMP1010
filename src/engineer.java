import java.util.Scanner;

public class Engineer extends Character {
    boolean deployedDevice;
    String builtDevice;
    int wallHealth;
    boolean powerCoreActive;

    public Engineer(String race, String name) {
        this.race = race;
        this.name = name;

        health = 150;
        damage = 15;
        intelligence = 10;
        initiative = 2;
        deployedDevice = false;
        builtDevice = "";
        wallHealth = 0;
        powerCoreActive = false;
        killcount = 0;
        maxHealth = health;
        specialAbLeft = 1000;
        specialAbMax = specialAbLeft;
    }

    public Engineer(String race, int health, int damage, int intelligence, int initiative, String name) {
        this.race = race;
        this.health = health;
        this.damage = damage;
        this.intelligence = intelligence;
        this.initiative = initiative;
        this.name = name;

        specialAbLeft = 1000;
        specialAbMax = specialAbLeft;
        maxHealth = health;
        builtDevice = "";
        wallHealth = 0;
        deployedDevice = false;
        powerCoreActive = false;
        killcount = 0;
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
        if (deployedDevice) {
            System.out.println("Device already deployed:" + builtDevice);
            return;
        }

        System.out.println("Choose a device to build:");
        System.out.println("1. Cage - Pacifies the rage of a Barbarian");
        System.out.println("2. Tracker - Reveals an invisible Rogue");
        System.out.println("3. Wall - Build a wall that has 50hp");
        System.out.println("4. Power Core - Increases damage by 10");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        useAbility(target, choice);
    }

    
    void useAbility(Character target, int choice) {
        switch (choice) {
            case 1:
                builtDevice = "CAGE";
                deployedDevice = true;
                System.out.println("Engineer built a Cage.");
                break;
            case 2:
                builtDevice = "TRACKER";
                deployedDevice = true;
                System.out.println("Engineer built a Tracker.");
                break;
            case 3:
                builtDevice = "WALL";
                wallHealth = 50;
                deployedDevice = true;
                System.out.println("Engineer built a Wall with 50 HP.");
                break;
            case 4:
                builtDevice = "POWER_CORE";
                if (!powerCoreActive) {
                    damage += 10;
                    powerCoreActive = true;
                    System.out.println("Engineer activated Power Core. Damage increases by 10.");
                } else {
                    System.out.println("Power Core already active.");
                }
                deployedDevice = true;
                break;
            default:
                System.out.println("Invalid Choice. No device built.");
        }
    }

    @Override
    void attack(Character target) {
        System.out.println("Engineer attacks" + target.getClass().getSimpleName());

        if (target instanceof Barbarian) {
            Barbarian bar = (Barbarian) target;

        if (deployedDevice && builtDevice.equals("CAGE") && bar.isRaging) {
            bar.deactivateRage();
            System.out.println("Cage pacified the Barbarian's rage!");
        }
            target.takeDamage(damage);  
        } else if (target instanceof Rogue) {
            Rogue rog = (Rogue) target;

            if (deployedDevice && builtDevice.equals("TRACKER") && rog.isInvisible) {
                rog.isInvisible = false;
                System.out.println("Tracker revealed the Rogue!");
            }
            target.takeDamage(damage);

        } else {
            target.takeDamage(damage);
        }
    }

    void defend(int incomingDamage) {
        if (deployedDevice && builtDevice.equals("WALL")) {
            System.out.println("Wall absorbs " + incomingDamage + " damage.");
            wallHealth -= incomingDamage;

            if (wallHealth <= 0) {
                System.out.println("Wall is destroyed!");
                wallHealth = 0;
                deployedDevice = false;
                builtDevice = "";
            } else {
                System.out.println("Wall HP remaining: " + wallHealth);
            }
        } else {
            System.out.println("Engineer takes " + incomingDamage + " damage.");
            this.health -= incomingDamage;
        }
    }
}
