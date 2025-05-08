
public class equipment {
    String name;
    int health;
    int strength;
    int defence;
    int initiative;
    int type;
    
    public equipment(String name, int health, int strength, int defence, int initiative, int type) {
        if (type < 1 || type > 4) {
            throw new IllegalArgumentException("Type must be between 1 and 4 inclusive.");
        }
        this.name = name;
        this.health= health;
        this.strength = strength;
        this.defence = defence;
        this.initiative = initiative;
        this.type = type;
    }
    public String typeName(int type){
        String typeName="";
        if (type == 1 ){
            typeName = "helmet";
        }
        if (type == 2 ){
            typeName = "Armour";
        }
        if (type == 3 ){
            typeName = "Item";
        }
        if (type == 4 ){
            typeName = "wepon";
        }
        return typeName;
    }
    
    
    
    public String toString(){   
        return "Equipment{" +"Name= " + name + ", Health= " + health + ", Strength= " + strength +", Defence= " + defence +", Initiative= " + initiative + ", Type= " + typeName(type) +'}';
    }
}
