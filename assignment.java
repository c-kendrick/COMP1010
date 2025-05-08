class Character {
    int health;
    int damage;
    int intelligence;
    int initiative;
    String race;
    Boolean isWounded;
    Boolean isPoisoned;

    //ArrayList<String> inventory = new ArrayList<>();


    void takeDamage(int amount) {
        health -= amount;
    }

    }   
    

class Barbarian extends Character {

	public Barbarian(String r) {
		health = 40;
		damage = 20;
		race = r;
        intelligence = 5;
        initiative = 5;
	}

    void attack(Character target) {
        target.takeDamage(10);
        System.out.println(target.getClass().getSimpleName());
    }
	
}

class Rogue extends Character {

	public Rogue(String r) {
		health = 10;
		damage = 30;
		race = r;
	}
	
}

class Equipment {

}

public class assignment {


    public static void main(String[] args) {
        Barbarian john = new Barbarian("John");
        Rogue alex = new Rogue("Alex");
        System.out.println(alex.health);
        john.attack(alex);
        System.out.println(alex.health);
        
    }
}