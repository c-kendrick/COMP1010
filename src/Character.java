import java.util.Scanner;

public class Character {
    int gold;
    int maxHealth;
    int health;
    int damage;
    int intelligence;
    int initiative;
    String race;
    String name;
    Boolean isWounded;
    Boolean isPoisoned;
    int killcount;
    boolean hasRaged;
    int specialAbLeft;
    int specialAbMax;

    public Character() {

    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    
    void attack(Character target) {
    }

    void specialAbility(Character target) {

    }

    boolean flee(Character target) {
        int num;
        if (initiative < target.initiative) {
            num = (int)(Math.random() * 10) + 1;
            if (num > 7) {
                return true;
            }
            return false;
        } 
        
        if (initiative == target.initiative) {
            num = (int)(Math.random() * 10) + 1;
            if (num > 5) {
                return true;
            } else {
                return false;
            }
        }
        
        if (initiative > target.initiative) {
            num = (int)(Math.random() * 10) + 1;
            if (num > 3) {
                return true;
            } else {
                return false;
            }
        }
        
        return true;
    }

    void genChoices(Character target) {

    }

    void getAction(Character enemy, Dungeon room, int difficulty) {
        Scanner scanner = new Scanner(System.in);
        int answer = -1;

        while (true) {
            System.out.println("What do you do?");
            System.out.println("1. Attack?");
            System.out.println("2. Special attack? " + specialAbLeft + " left.");
            System.out.println("3. Flee? (Lose all gold!)");
            System.out.println("");

            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                scanner.nextLine();
                if (answer >= 1 && answer <= 4) {
                    break;
                } else {
                    System.out.println("Invalid number. Please enter a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.nextLine();
            }

        }

        switch(answer) {
            case 1:
                System.out.println("Enemy health at start of fight: " + enemy.health);
                attack(enemy);
                System.out.println("Enemy health now: " + enemy.health);
                break;
            case 2:
                System.out.println("Enemy health at start of fight: " + enemy.health);
                specialAbility(enemy);
                System.out.println("Enemy health now: " + enemy.health);
                break;
            case 3:
                if (flee(enemy)) {
                    System.out.println("Flee successful");
                    gold = 0;
                    health = maxHealth;
                    specialAbLeft = specialAbMax;

                    System.out.println("");
                    System.out.println("You find a campfire and decide to rest.");
                    System.out.println("Your health & special abilities have been reset to full.");

                    Assignment.gameDriver(this, room, difficulty);
                    return;
                }
                System.out.println("Flee not successful.");
                getAction(enemy, room, difficulty);
                return;
            case 4:
                System.out.println("Began defense");
                break;
        }
    }
}
