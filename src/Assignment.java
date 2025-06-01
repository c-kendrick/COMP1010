import java.util.Scanner;
    
public class Assignment {
    static boolean endlessMode = false;

    public static void gameWon(Character player) {
        for (int i = 0; i < 14; i++) 
            System.out.println("");

        System.out.println("CONGRATULATIONS! You have won the game!");
        System.out.println("");
        System.out.println("You killed: " + player.killCount + " enemies!");
        System.out.println("Your health at the end of the game: " + player.health);
        System.out.println("Your SCORE (in gold): $" + player.gold);
    }

    public static void gameLost(Character player) {
        for (int i = 0; i < 14; i++) 
            System.out.println("");
        
        System.out.println("The game is over.");
        System.out.println("You killed: " + player.killCount + " enemies!");
        System.out.println("Your SCORE (in gold): $" + player.gold);
    }

    public static int getDifficulty() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (true) {
            System.out.println("");
            System.out.println("Choose difficulty:");
            System.out.println("");
            System.out.println("1. Endless (Normal)");
            System.out.println("");
            System.out.println("-----------");
            System.out.println("2. Normal");
            System.out.println("-----------");
            System.out.println("");
            System.out.println("3. Hard");
            System.out.println("");
            System.out.println("Type the number (1, 2, or 3) and hit enter.");
            System.out.println("");

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

    public static void generateDungeons(Character player, int difficulty, int steps) {
        if (difficulty == 1) {
            endlessMode = true;
            difficulty = 2; // need to play on normal difficulty at "2"
        }

        /* lowest difficulty
         * if
         *      difficulty == 2, lower = 8
         *      difficulty == 3, lower = 16
         *      difficulty == 4, lower = 22
         */

        int lower = 8 + (difficulty - 2) * 6;

        /* highest difficulty
         * if
         *      difficulty == 2, upper = 22
         *      difficulty == 3, upper = 28
         *      difficulty == 4, upper = 34
         */

        int upper = lower + (steps - 1) * 3;
        int dungeonAmt = ((upper - lower) / 3) + 1;
        int dungeonIndex = dungeonAmt; // room number (5 to 1 decreasing)
        
        Dungeon room = null;
        for (int i = upper; i >= lower; i -= 3) {
            room = new Dungeon(i, room, dungeonIndex, dungeonAmt);
            dungeonIndex--;
        }

        // generate recursive data structure of Dungeon room (object)
        gameDriver(player, room, difficulty);
    }

    public static String getName() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Hey, you! You're finally awake.");
        System.out.println("What is your name?");
        System.out.println("(Type in your name and hit enter).");
        System.out.println("");
        String name = scanner.nextLine();

        return name;
    }

    public static int getClan() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (true) {
            System.out.println("What is your class?");
            System.out.println("");
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
        Race newRace = new Race();
        newRace.askRace();

        return switch(answer) {
            case 1 -> new Barbarian(newRace, getName());
            case 2 -> new Rogue(newRace, getName());
            case 3 -> new Mage(newRace, getName());
            case 4 -> new Engineer(newRace, getName());
            default -> throw new IllegalArgumentException("Unknown choice");
        };
    }

    public static int rest(Character player, int difficulty) {
        player.health = player.maxHealth;
        player.abilityPointsLeft = player.abilityPointsMax;
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
        Equipment.unlockTwoRandomEquipments();
        
        System.out.println("");
        int gold = (int)((Math.random() * 20) + 5) * difficulty;
        player.gold += gold;
        System.out.println("Gold found: $" + gold);
        System.out.println("Your current amount of gold: $" + player.gold);
        
        System.out.println("");
        System.out.println("========");
        System.out.println("Would you like to continue on to the next dungeon?");
        System.out.println("");
        System.out.println("----");
        System.out.println("1. Let's leave the dungeons for now (exits game).");
        System.out.println("");
        System.out.println("2. Edit inventory; equip armour and weapons.");
        System.out.println("");
        System.out.println("3. Bring it on.");
        System.out.println("----");
        System.out.println("");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            gameLost(player);
            return choice;
        }

        if (choice == 2){
            //getting rid of previous equipment effects
            player.health -=  Equipment.stats[0];
            player.maxHealth -= Equipment.stats[0];
            player.damage -=  Equipment.stats[1];
            player.maxDamage -= Equipment.stats[1];
            player.initiative -=  Equipment.stats[2];

            Equipment.equip();
            //adding new equipment effects
            player.health +=  Equipment.stats[0];
            player.maxHealth += Equipment.stats[0];
            player.damage +=  Equipment.stats[1];
            player.maxDamage += Equipment.stats[1];
            player.initiative +=  Equipment.stats[2];

            System.err.println(Equipment.stats[0]);
            System.out.println("New health: " + player.health);
            System.out.println("New strength: " + player.damage);
            System.out.println("New initiative: " + player.initiative);
        }

        return choice;
    }

    public static void gameDriver(Character player, Dungeon room, int difficulty) {
        //recursive function to play the game
        if (room == null) {
            if (endlessMode == true) {
                System.out.println("Stage: " + difficulty + " of INFINITY complete.");
                // restarts game 
                generateDungeons(player, difficulty++, difficulty + 2);
            } else {
                gameWon(player);
                return;
            }        
        }

        /* If player is new to the game (and has no kills)
         * they should not be "resting", if they aren't
         * AND if they aren't fleeing from combat
         * they rest before moving onto the next dungeon.
         */

        if (player.killCount > 0 && !player.isFleeing) 
            if (rest(player, difficulty) == 1)
                return;

        // If the player defeats the dungeon, recursively call this function.
        if (room.defeatDungeon(player)) {
            gameDriver(player, room.next, difficulty);
            player.isFleeing = false;
            return;
        } else {
            gameLost(player);
            return;
        }
    }

    public static void main(String[] args) {
        Equipment.generateEquipments();
        int difficulty = getDifficulty();
        int clan = getClan();
        Character player = create(clan);

        /* difficulty + 2 is to determine how many dungeons are created.
         * 4 dungeons if difficulty "2" or "normal" is picked.
         * This scales with difficulty.
         */

        int numDungeons = difficulty + 2;
        generateDungeons(player, difficulty, numDungeons);
    }
}