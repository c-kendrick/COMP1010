<<<<<<< HEAD
import java.util.Scanner;

public class engineer extends Character {
=======
public class Engineer extends Character {
>>>>>>> 57b10028f4b973624b2938d53c327e7f71176faf
    boolean deployedDevice;
    String builtDevice;
    int wallHealth;
    boolean powerCoreActive;

    public Engineer(String r) {
        health = 200;
        damage = 15;
        race = r;
        intelligence = 10;
        initiative = 2;
        deployedDevice = false;
        builtDevice = "";
        wallHealth = 0;
        powerCoreActive = false;
    }

    void build() {
<<<<<<< HEAD
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
=======
        
>>>>>>> 57b10028f4b973624b2938d53c327e7f71176faf
    }


    void attack(Character target) {
<<<<<<< HEAD
        System.out.println("Engineer attacks" + target.getClass().getSimpleName());

        if (target instanceof barbarian) {
            barbarian bar = (barbarian) target;
=======
        // attacking barbarian
        if (target instanceof Barbarian) {
            Barbarian bar = (Barbarian) target;
>>>>>>> 57b10028f4b973624b2938d53c327e7f71176faf

        if (deployedDevice && builtDevice.equals("CAGE") && bar.isRaging) {
            bar.deactivateRage();
            System.out.println("Cage pacified the Barbarian's rage!");
        }
            target.takeDamage(damage);  
        } else if (target instanceof rogue) {
            rogue rog = (rogue) target;

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
