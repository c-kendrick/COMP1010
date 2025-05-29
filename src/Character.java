import java.util.Scanner;

public class Character {
    int health;
    int damage;
    int intelligence;
    int initiative;
    String race;
    Boolean isWounded;
    Boolean isPoisoned;

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

    void getAction(Character enemy) {
        Scanner scanner = new Scanner(System.in);
        int answer = -1;

        while (true) {
            System.out.println("What do you do?");
            System.out.println("1. Attack?");
            System.out.println("2. Special attack?");
            System.out.println("3. Flee?");
            System.out.println("Please enter the number of your desired action");

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
                    // TO DO: leave dungeon 
                    break;
                }
                System.out.println("Flee not successful.");
                getAction(enemy);
            case 4:
                System.out.println("Began defense");
                break;
        }
    }
}
