
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    @Test
    public void testFail() {
        assertEquals(1, 2); // this should fail
    }

    @Test
    public void testEquipment() {
        Equipment test = new Equipment(6, "test", 10, 20, 50, 4, true, false);
        assertEquals(6, test.ID);
        assertEquals("test", test.name);
        assertEquals(10, test.health);
        assertEquals(20, test.strength);
        assertEquals(50, test.initiative);
        assertEquals(4, test.type);
        assertEquals(true, test.unlocked);
    }

    @Test
    public void testTypename(){
        assertEquals("Helmet", Equipment.typeName(1));
        assertEquals("Armour", Equipment.typeName(2));
        assertEquals("Item", Equipment.typeName(3));
        assertEquals("Weapon", Equipment.typeName(4));
        assertEquals("Invalid", Equipment.typeName(5));
    }

    @Test
    public void testCharacter() {
        Race t = new Race();
        t.setRaceName(1);
        Barbarian test = new Barbarian(t ,500, 10, "testName");
        assertEquals("Elf", test.race.raceName);
        assertEquals("testName",test.name);
        assertEquals(0,test.initiative);
        assertEquals(5,test.intelligence);
        assertEquals(500,test.health);
        assertEquals(10,test.damage);
        assertEquals(3,test.abilityPointsLeft);
        assertEquals(0,test.killCount);
        assertEquals(false,test.isRaging);
        assertEquals(false,test.isBlindedRampage);
        assertEquals(false,test.isFleeing);
        assertEquals(0,test.combatBonusApplied);
    }
}
