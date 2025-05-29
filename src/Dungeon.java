import java.util.ArrayList;
import java.util.Scanner;

public class Dungeon {
    Dungeon next;
    int difficulty;
    ArrayList<Character> enemyList;

    public Dungeon(int difficulty) {
        this.difficulty = difficulty;
        enemyList = new ArrayList();
        
        int numEnemies =((int)(Math.random() * 5) + 1) + difficulty;

        for (int i = 0; i < numEnemies; i++) {
           enemyList.add(createEnemy());
        }
    }

    Character createEnemy() {
        int choice = (int)(Math.random() * 4) + 1;

        int health = ((int)(Math.random() * 5) + 1) * difficulty;
        int damage = ((int)(Math.random() * 5) + 1) * difficulty;

        switch (choice) {
            case 1: return new barbarian(getRace(), health, damage, difficulty, difficulty);
            case 2: return new rogue(getRace());
            case 3: return new mage(getRace());
            case 4: return new engineer(getRace());
            default: return new barbarian(getRace());
        }
    }

    String getRace() {
        int answer = (int)(Math.random() * 5) + 1;
        switch(answer) {
            case 1:
                return "Elf";
            case 2:
                return "Orc";
            case 3:
                return "Alien";
            case 4:
                return "Robot";
            default: throw new IllegalArgumentException("Unknown choice");
        }
    }

    void combatDungeon(Character player) {
        while (enemyList.size() > 0) {
            System.out.println("You are facing " + enemyList.size() + " enemies!");

            combatEnemy(player, enemyList.get(0));
            if (enemyList.get(0).health <= 0)
                enemyList.remove(0);
        }

        System.out.println("You have defeated the dungeon!");
    }

    static void combatEnemy(Character player, Character enemy) {
        while (enemy.health > 0) {
            player.getAction(enemy);

            if (enemy.health > 0) {
                System.out.println("Enemy still alive.");
            } else {
                System.out.println("Enemy killed!");
            }

            enemy.genChoices(player);

            if (player.health > 0) {
                System.out.println("Your health: " + player.health);
            } else {
                System.out.println("You have died!");
                //to do: kill game
                break;
            }
        }
    }


}
