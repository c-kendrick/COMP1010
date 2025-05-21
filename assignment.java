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

        System.out.println("Okay " + name + ", what is your race?");
        return scanner.nextLine();
    }

    public static int getClan(String name, String race) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Okay " + race + ", what is your class?");
        System.out.println("1. Barbarian?");
        System.out.println("2. Rogue?");
        System.out.println("3. Mage?");;
        System.out.println("4. Engineer?");

        return scanner.nextInt();
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

        System.out.println("What do you do?");
        System.out.println("1. Attack?");
        System.out.println("2. Special attack?");
        System.out.println("3. Flee?");
        System.out.println("4. Defend?");

        int answer = scanner.nextInt();

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
                System.out.println("Flee successful");
                break;
            case 4:
                System.out.println("Began defense");
                break;
            default:
                throw new IllegalArgumentException("Unknown choice");
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
        
        while (enemy.health > 0) {
            getAction(player, enemy);

            if (enemy.health > 0) {
                System.out.println("Enemy still alive.");
            } else {
                System.out.println("Enemy killed!");
            }
        }
        




    }
}