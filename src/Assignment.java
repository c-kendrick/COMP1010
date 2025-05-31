import java.util.Scanner;
    
public class Assignment {
    static boolean endlessMode = false;

    public static void gameWon(Character player) {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("CONGRATULATIONS! You have won the game!");
        System.out.println("");
        System.out.println("You killed: " + player.killcount + " enemies!");
        System.out.println("Your health at the end of the game: " + player.health);
        System.out.println("Your SCORE (in gold): $" + player.gold);
    }

    public static void gameLost(Character player) {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("The game is over.");
        System.out.println("You killed: " + player.killcount + " enemies!");
        System.out.println("Your SCORE (in gold): $" + player.gold);
    }

public static int getDifficulty() {
    Scanner scanner = new Scanner(System.in);
    int choice = -1;

    while (true) {
        System.out.println("Choose difficulty:");
        System.out.println("1. Endless (Normal)");
        System.out.println("2. Normal");
        System.out.println("3. Hard");

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice >= 1 && choice <= 3) {
                return choice;
            } else {
                System.out.println("Invalid number. Please enter 1, 2, or 3.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }
}

public static void generateDungeons(Character player, int difficulty) {
        if (difficulty == 1) {
            endlessMode = true;
            difficulty = 2; // need to play on normal difficulty at "2"
        }

        int steps = 5; // max of 5 dungeons

        int lower = 10 + (difficulty - 2) * 6;
        // lowest difficulty
        // if:
        //      difficulty == 2, lower = 10
        //      difficulty == 3, lower = 16
        //      difficulty == 4, lower = 22

        int upper = lower + (steps - 1) * 3;
        // highest difficulty.
        // if:
        //      difficulty == 2, upper = 22.
        //      difficulty == 3, upper = 28
        //      difficulty == 4, upper = 34   

        int count = ((upper - lower) / 3) + 1;

        int idx = count; // room number (5 to 1 decreasing)

        // generate recursive data structure of Dungeon room (object)
        Dungeon room = null;
        for (int i = upper; i >= lower; i -= 3) {
            room = new Dungeon(i, room, idx, count);
            idx--;
        }

        gameDriver(player, room, difficulty);
}

    public static String getName() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Hey, you! You're finally awake.");
        System.out.println("What is your name?");
        String name = scanner.nextLine();

        return name;
    }

    public static String getRace() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (true) {
            System.out.println("What is your race?");
            System.out.println("Please choose from the following options:");
            System.out.println("1. Elf");
            System.out.println("2. Orc");
            System.out.println("3. Human");
            System.out.println("4. Dwarf");
            System.out.println("");

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: return "Elf";
                case 2: return "Orc";
                case 3: return "Human";
                case 4: return "Dwarf";
                default:
                    System.out.println("Invalid number. Please enter a number between 1 and 4.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number between 1 and 4.");
            scanner.nextLine();
        }
    }
}


    public static int getClan() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (true) {
            System.out.println("What is your class?");
            System.out.println("1. Barbarian?");
            System.out.println("2. Rogue?");
            System.out.println("3. Mage?");;
            System.out.println("4. Engineer?");
            System.out.println("");
            
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

    public static Character create(int answer) {
        return switch(answer) {
            case 1 -> new Barbarian(getRace(), getName());
            case 2 -> new Rogue(getRace(), getName());
            case 3 -> new Mage(getRace(), getName());
            case 4 -> new Engineer(getRace(), getName());
            default -> throw new IllegalArgumentException("Unknown choice");
        };
    }

    public static void newEquipmentSet(Character player) {

    }

    public static void rest(Character player, int difficulty) {
        player.health = player.maxHealth;
        player.specialAbLeft = player.specialAbMax;
        player.damage = player.maxDamage;
        player.characterRest();

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("=======================================");
        System.out.println("You find a campfire and decide to rest.");
        System.out.println("Your health & special abilities have been reset to full.");
        
        System.out.println("");
        System.out.println("After relaxing by the crackling logs,");
        System.out.println("and watching the embers fly up,");
        System.out.println("you discover a chest filled with treasure,");
        System.out.println("and several brand spanking new sets of armour and weapons.");
        System.out.println("");
        int gold = (int)((Math.random() * 20) + 5) * difficulty;
        player.gold += gold;
        System.out.println("Gold found: $" + gold);
        System.out.println("Your current amount of gold: $" + player.gold);
        
        //to do: add armour sets and let player choose between armour.
        System.out.println("");
        System.out.println("========");
        
        System.out.println("Would you like to continue on to the next dungeon?");
        System.out.println("");
        System.out.println("1. Bring it on.");
        System.out.println("2. Let's leave the dungeons for now (exits game).");
        System.out.println("3. Edit Equipments.");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 2) {
            gameLost(player);
            return;
        }
        if(choice == 3){
            Equipment.equip();
            player.health +=  Equipment.stats[0];
            player.maxHealth += Equipment.stats[0];

            player.damage +=  Equipment.stats[1];
            player.maxDamage += Equipment.stats[1];
            
            player.initiative +=  Equipment.stats[3];
            
            System.out.println("New health: " + player.health);
            System.out.println("New strength: " + player.damage);
            System.out.println("New initiative: " + player.initiative);

        }

    }

    //recursive function to play the game
    public static void gameDriver(Character player, Dungeon room, int difficulty) {
        if (room == null) {
            if (endlessMode == true) {
                System.out.println("Stage: " + difficulty + " of INFINITY complete.");
                generateDungeons(player, difficulty++);
            } else {
                gameWon(player);
                return;
            }        
        }

        if (player.killcount > 0)
            rest(player, difficulty);

        if (room.defeatDungeon(player)) {
            gameDriver(player, room.next, difficulty);
        } else {
            gameLost(player);
            return;
        }
    }

    public static void main(String[] args) {
        int difficulty = getDifficulty();
        int clan = getClan();
        Character player = create(clan);


        generateDungeons(player, difficulty);
    }
}