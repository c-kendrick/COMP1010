import java.util.ArrayList;
import java.util.Scanner;

public class Dungeon {
    Dungeon next;
    int idx;
    int count;
    int difficulty;
    ArrayList<Character> enemyList;

    public Dungeon(int difficulty, Dungeon next, int idx, int count) {
        this.next = next;
        this.difficulty = difficulty;
        this.idx = idx;
        this.count = count;

        enemyList = new ArrayList();        
        int numEnemies =((int)(Math.random() * 3) + 1) + (difficulty / 2);

        for (int i = 0; i < numEnemies; i++) {
           enemyList.add(createEnemy());
        }
    }

    Character createEnemy() {
        int choice = (int)(Math.random() * 4) + 1;

        int health = ((int)(Math.random() * 5) + 2) * difficulty;
        int damage = ((int)(Math.random() * 3) + 1) * (difficulty/2);

        switch (choice) {
            case 1: return new Barbarian(getRace(), health, damage, difficulty, difficulty, "Enemy");
            case 2: return new Rogue(getRace(), health, damage, difficulty, difficulty, "Enemy");
            case 3: return new Mage(getRace(), health, damage, difficulty, difficulty, "Enemy");
            case 4: return new Engineer(getRace(), health, damage, difficulty, difficulty, "Enemy");
            default: return new Barbarian(getRace(), health, damage, difficulty, difficulty, "Enemy");
        }
    }

    String getRace() {
        int answer = (int)(Math.random() * 4) + 1;
        switch(answer) {
            case 1:
                return "Elf";
            case 2:
                return "Orc";
            case 3:
                return "Human";
            case 4:
                return "Dwarf";
            default: throw new IllegalArgumentException("Unknown choice");
        }
    }

    boolean defeatDungeon(Character player) {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("==============================");
        System.out.println("Dungeon: " + idx + " of " + count);
        System.out.println("");
        System.out.println("");

        System.out.println("Your HP: " + player.health);
        System.out.println("Your Strength: " + player.damage);
        System.out.println("Your Special Ability points: " + player.specialAbLeft);


        while (enemyList.size() > 0) {
            System.out.println("");
            System.out.println("");
            System.out.println("You are facing " + enemyList.size() + " enemies!");

            if(!defeatEnemy(player, enemyList.get(0))) 
                return false;
            
            if (enemyList.get(0).health <= 0)
                enemyList.remove(0);
        }

        System.out.println("You have defeated the dungeon!");
        return true;
    }

    boolean defeatEnemy(Character player, Character enemy) {
        System.out.println("");
        System.out.println("");
        System.out.println("You are facing a " + enemy.race + " " + enemy.getClass().getSimpleName());
        System.out.println("HP: " + enemy.health + ", Strength: " + enemy.damage);
        while (enemy.health > 0) {
            player.getAction(enemy, this, difficulty);

            if (enemy.health > 0) {
                enemy.genChoices(player);
            } else {
                System.out.println("");
                System.out.println("~~~");
                System.out.println("Enemy killed!");
                System.out.println("~~~");
                player.killcount++;
            }

            if (player.health > 0) {
                System.out.println("");
                System.out.println("===========");
                System.out.println("Your health: " + player.health);
            } else {
                return false;
            }
        }
        return true;
    }


}
