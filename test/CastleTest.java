import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;


public class CastleTest extends UnitTest {

    @Test
    public void castleTest() {
        
		Resource coal = new Resource("coal", "Wegiel", true, true);
		Resource castleHealth = new Resource("castleHealth", "Sila zamku", false, false);
		
		HashMap<String, Integer> storage = new HashMap();
		storage.put(coal.resourceID, 10);
		
		HashMap<String, Integer> construction = new HashMap();
		construction.put(castleHealth.resourceID, 100);
		
		Castle cast1 = new Castle(storage, construction);
		
		assertEquals(10, cast1.countStorage(coal.resourceID));
    }

}
