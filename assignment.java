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

    void attack(Character target, int amount) {
        target.takeDamage(amount);
    }


}   
    

class Barbarian extends Character {

    boolean isRaging;

	public Barbarian(String r) {
		health = 150;
		damage = 30;
		race = r;
        intelligence = 5;
        initiative = 5;
        isRaging = false;
	}

    void attack(Character target) {
        if (target instanceof Engineer) {
            Engineer eng = (Engineer) target;
            
            if (eng.deployedDevice) {
                if (isRaging)
                    eng.deployedDevice = false;
            } else {                
                target.takeDamage(damage);
            }

            
            
        }

        if (target instanceof Rogue) {
            Rogue rog = (Rogue) target;
            if (!rog.isInvisible) {
                target.takeDamage(damage);
            }
        }

        if (target instanceof Mage) {
            
        }
    }

    void rageAttack(Character target) {
        isRaging = true;
        damage += 20;
        attack(target);
        damage -= 20;
        isRaging = false;
    }
	
    
}

class Rogue extends Character {
    boolean isInvisible;

	public Rogue(String r) {
		health = 100;
		damage = 15;
		race = r;
	}
	
}

class Engineer extends Character {
    boolean deployedDevice;
}

class Mage extends Character {

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