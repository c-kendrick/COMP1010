import java.util.ArrayList;

public class Dungeon {
    Dungeon next;
    int dungeonIdx;
    int dungeonAmt;
    int difficulty;
    ArrayList<Character> enemyList;

    public Dungeon(int difficulty, Dungeon next, int idx, int count) {
        this.next = next;
        this.difficulty = difficulty;
        this.dungeonIdx = idx;
        this.dungeonAmt = count;

        populate();
    }

    void populate() {
        enemyList = new ArrayList<>();        
        int numEnemies = ((int)(Math.random() * 3) + 1) + (difficulty / 2);

        for (int i = 0; i < numEnemies; i++) 
           enemyList.add(createEnemy());
    }

    Character createEnemy() {
        Race race = new Race();
        race.genRaceName();

        int health = ((int)(Math.random() * 5) + 2) * difficulty;
        int damage = ((int)(Math.random() * 3) + 1) * (difficulty/2);

        int choice = (int)(Math.random() * 4) + 1;

        switch (choice) {
            case 1: return new Barbarian(race, health, damage, "Enemy");
            case 2: return new Rogue(race, health, damage, "Enemy");
            case 3: return new Mage(race, health, damage, "Enemy");
            case 4: return new Engineer(race, health, damage, "Enemy");
            default: return new Barbarian(race, health, damage, "Enemy");
        }
    }

    boolean defeatDungeon(Character player) {
        // The boolean is to check if the player can defeat the dungeon.
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("==============================");
        System.out.println("Dungeon: " + dungeonIdx + " of " + dungeonAmt);
        System.out.println("");
        System.out.println("");

        System.out.println("Your Health Points (HP): " + player.health);
        System.out.println("Your Ability Points (AP): " + player.abilityPointsLeft);
        System.out.println("Your Strength: " + player.damage);
        System.out.println("");

        int enemyIdx;
        for (int i = 0; i < enemyList.size(); i++) {
            enemyIdx = i + 1;
            System.out.println(enemyIdx + ": " + enemyList.get(i).race.getRaceName() + " " + enemyList.get(i).getClass().getSimpleName() + " HP: " + enemyList.get(i).health + " Strength: " + enemyList.get(i).damage);
        }

        // Dungeon loop:
        while (enemyList.size() > 0) {
            System.out.println("");
            System.out.println("You are facing " + enemyList.size() + " enemies!");
            System.out.println("");

            if(!defeatEnemy(player, enemyList.get(0))) 
                return false;
            
            if (enemyList.get(0).health <= 0)
                enemyList.remove(0);
        }

        System.out.println("");
        System.out.println("------");
        System.out.println("You have defeated the dungeon!");
        return true;
    }

    boolean defeatEnemy(Character player, Character enemy) {
        // The boolean is to check if the player can defeat the enemy.
        System.out.println("");
        System.out.println("");
        System.out.println("You are facing a " + enemy.race.getRaceName() + " " + enemy.getClass().getSimpleName());
        System.out.println("HP: " + enemy.health + ", Strength: " + enemy.damage);

        player.combatBonusApplied = player.race.compareRace(enemy);

        switch (player.combatBonusApplied) {
            case 1:
                combatAdvantage(player);
                break;
            case 0:
                System.out.println("Your combat effectiveness is neutral.");
                break;
            case -1:
                combatAdvantage(enemy);
                break;
        }

        // Combat loop:
        while (enemy.health > 0) {
            player.getAction(enemy, this, difficulty);

            if (enemy.health > 0) {
                enemy.genChoices(player);
            } else {
                System.out.println("");
                System.out.println("~~~");
                System.out.println("Enemy killed!");
                System.out.println("~~~");
                player.killCount++;
            }

            if (player.health > 0) {
                System.out.println("");
                System.out.println("===========");
                System.out.println("Your health: " + player.health);
            } else {
                return false;
            }
        }

        // Only focusing on reversing player damage, because enemy is dead.
        if (player.combatBonusApplied == 1) {
            player.damage -=5;
            player.combatBonusApplied = 0;
        }

        return true;
    }

    void combatAdvantage(Character target) {
        target.damage += 5;
        System.out.println(target.name + " damage increased by 5");
    }
}
