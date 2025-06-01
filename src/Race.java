import java.util.Scanner;

public class Race {
    private String raceName;

    public Race() {
        raceName = "";
    }

    public void genRaceName() {
        int answer = (int)(Math.random() * 4) + 1;
        setRaceName(answer);
    }

    public void askRace() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice < 1 || choice > 4) {
            System.out.println("What is your race?");
            System.out.println("Please choose from the following options:\n");
            System.out.println("1. Elf");
            System.out.println("2. Orc");
            System.out.println("3. Human");
            System.out.println("4. Dwarf\n");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); 

                if (choice >= 1 && choice <= 4) {
                    setRaceName(choice);
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); 
            }
        }
    }

    public void setRaceName(int choice) {
        switch (choice) {
            case 1:
                raceName = "Elf";
                break;
            case 2:
                raceName = "Orc";
                break;
            case 3:
                raceName = "Human";
                break;
            case 4:
                raceName = "Dwarf";
                break;
            default:
                raceName = "Unknown";
                break;
        }
    }

    public String getRaceName() {
        return raceName;
    }

    public int compareRace(Character target) {
        String enemyRace = target.race.getRaceName();
        return compareStrength(raceName, enemyRace);
    }

    private int compareStrength(String self, String enemy) {
        if (isStrongAgainst(self, enemy)) 
            return 1;

        if (isWeakAgainst(self, enemy)) 
            return -1;

        return 0; // neutral by default, neither combatant has natural strength over the other
    }

    private boolean isStrongAgainst(String self, String enemy) {
        return (self.equals("Elf") && enemy.equals("Orc")) ||
               (self.equals("Orc") && enemy.equals("Human")) ||
               (self.equals("Human") && enemy.equals("Dwarf")) ||
               (self.equals("Dwarf") && enemy.equals("Elf"));
    }

    private boolean isWeakAgainst(String self, String enemy) {
        return isStrongAgainst(enemy, self);
    }
}