import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    @Test
    public void testFail() {
        assertEquals(1, 2); // this should fail
    }
    @Test
    public void testEquipment() {
        equipment test = new equipment(6, "test", 10, 20, 0, 50, 4);
        assertEquals(6, test.ID);  
    }
    @Test
    public void testCharacter(){
        Character test = assignment.create(3,"elf");
        assertEquals("elf",test.race);
    }
}