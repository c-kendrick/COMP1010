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
    




public class Assignment {

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
            case 1 -> new Barbarian(race);
            case 2 -> new Rogue(race);
            case 3 -> new Mage(race);
            case 4 -> new Engineer(race);
            default -> throw new IllegalArgumentException("Unknown choice");
        };
    }

    public static void gameDriver(Character player, Dungeon room) {
        if (room == null) {
            System.out.println("CONGRATULATIONS! You have won the game!");
            //give game stats
            return;
        }

        if (player.health <= 0) {
            System.out.println("You have lost the game. :(");
            //give game stats
            return;
        }

        room.combatDungeon(player);

        gameDriver(player, room.next);
    }


    public static void main(String[] args) {
        String name = getName();
        String race = getRace(name);
        int clan = getClan(name, race);
        Character player = create(clan, race);

        Dungeon room = null;
        for (int i = 5; i >= 1; i--) 
            room = new Dungeon(i, room);
        

        gameDriver(player, room);
    }
}