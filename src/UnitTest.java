
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    @Test
    public void testFail() {
        assertEquals(1, 2); // this should fail
    }

    @Test
    public void testEquipment() {
        Equipment test = new Equipment(6, "test", 10, 20, 0, 50, 4, true);
        assertEquals(6, test.ID);
        assertEquals("test", test.name);
        assertEquals(10, test.health);
        assertEquals(20, test.strength);
        assertEquals(0, test.defence);
        assertEquals(50, test.initiative);
        assertEquals(4, test.type);
        assertEquals(true, test.unlocked);
    }

    @Test
    public void testCharacter() {
        Character test = assignment.create(3, "elf");
        assertEquals("elf", test.race);
    }
}
