import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
    
class StatusEffect {
    int turnsLeft;
    Character target;
    String effect;
    // info used for damage amount or other numbers needed
    int info;

    //ArrayList<String> inventory = new ArrayList<>();

}   
    




public class assignment {

    //to do: move to another class
    public static String getName() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("You! You're finally awake.");
        System.out.println("What is your name?");
        String name = scanner.nextLine();

        return name;
    }

    public static String getRace(String name) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (true) {
            System.out.println("Okay " + name + ", what is your race?");
            System.out.println("Please choose from the following options:");
            System.out.println("1. Elf");
            System.out.println("2. Orc");
            System.out.println("3. Alien");
            System.out.println("4. Robot");

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: return "Elf";
                case 2: return "Orc";
                case 3: return "Alien";
                case 4: return "Robot";
                default:
                    System.out.println("Invalid number. Please enter a number between 1 and 4.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number between 1 and 4.");
            scanner.nextLine();
        }
    }
}

    public static int getClan(String name, String race) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (true) {
            System.out.println("Okay " + race + ", what is your class?");
            System.out.println("1. Barbarian?");
            System.out.println("2. Rogue?");
            System.out.println("3. Mage?");;
            System.out.println("4. Engineer?");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= 1 && choice <= 4) {
                    return choice;
                } else {
                    System.out.println("Invalid number. Please a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.nextLine();
            }

        }
    }

    public static Character create(int answer, String race) {
        return switch(answer) {
            case 1 -> new barbarian(race);
            case 2 -> new rogue(race);
            case 3 -> new mage(race);
            case 4 -> new engineer(race);
            default -> throw new IllegalArgumentException("Unknown choice");
        };
    }

    public static void getAction(Character player, Character enemy) {
        Scanner scanner = new Scanner(System.in);
        int answer = -1;

        while (true) {
            System.out.println("What do you do?");
            System.out.println("1. Attack?");
            System.out.println("2. Special attack?");
            System.out.println("3. Flee?");
            System.out.println("4. Defend?");
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
                player.attack(enemy);
                System.out.println("Enemy health now: " + enemy.health);
                break;
            case 2:
                System.out.println("Enemy health at start of fight: " + enemy.health);
                player.specialAbility(enemy);
                System.out.println("Enemy health now: " + enemy.health);
                break;
            case 3:
                if (player.flee(enemy)) {
                    System.out.println("Flee successful");
                    break;
                }
                System.out.println("Flee not successful.");
                getAction(player, enemy);
            case 4:
                System.out.println("Began defense");
                break;
        }
    }
    public static void combat(Character player, Character enemy) {
        while (enemy.health > 0) {
            getAction(player, enemy);

            if (enemy.health > 0) {
                System.out.println("Enemy still alive.");
            } else {
                System.out.println("Enemy killed!");
            }
        }
    }


    public static void main(String[] args) {
        Random r= new Random();
            
        String name = getName();
        String race = getRace(name);
        int answer = getClan(name, race);
        

        Character player = create(answer, race);
        int num = (int)(Math.random() * 4) + 1;
        Character enemy = create(num, "elf");

        System.out.println("A random " + enemy.getClass().toString() + " appeared!");
        

        getAction(player, enemy);




    }
}