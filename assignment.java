import java.util.ArrayList;
    
class StatusEffect {
    int turnsLeft;
    Character target;
    String effect;
    // info used for damage amount or other numbers needed
    int info;

    public StatusEffect(int turnsLeft, Character target, String effect, int info) {
        this.turnsLeft = turnsLeft;
        this.target = target;
        this.effect = effect;
        this.info = info;
    }
}



public class assignment {

    public void turnCounter() {
        ArrayList<StatusEffect> effects = new ArrayList<>();
    }


    public static void main(String[] args) {
        Barbarian john = new Barbarian("John");
        Rogue alex = new Rogue("Alex");
        System.out.println(alex.health);
        john.attack(alex);
        System.out.println(alex.health);
        
    }
}