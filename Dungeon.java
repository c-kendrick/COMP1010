public class Dungeon {
    Dungeon next;
    int difficulty;


    public Dungeon(int difficulty) {
        this.difficulty = difficulty;
    }

    void populateDungeon() {
        int numEnemies =((int)(Math.random() * 5) + 1) + difficulty;
        
        

        for (int i = 0; i < numEnemies; i++) {
           
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
        };
    }
}
