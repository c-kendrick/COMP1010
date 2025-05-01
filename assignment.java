class Character {
    int health;
    int damage;
    int intelligence;
    int initiative;
    String race;
    Boolean isWounded;
    Boolean isPoisoned;

    ArrayList<String> inventory = new ArrayList<>();

    }   
    

class Barbarian extends Character {

	public Barbarian(String r) {
		health = 40;
		damage = 20;
		race = r;
        intelligence = 5;
        initiative = 5;
	}
	
}

class Rogue extends Character {

	public Rogue(String r) {
		health = 10;
		damage = 30;
		Race = r;
	}
	
}

class Equipment {

}

public class assignment {


    public static void main(String[] args) {

        
    }
}