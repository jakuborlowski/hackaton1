
import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class CastleTest extends UnitTest {

	@Test
	public void castleTest() {

		Resource coal = new Resource("coal", "Wegiel", true, true);
		Resource health = new Resource("health", "Sila zamku", false, false);

		HashMap<String, Integer> storage = new HashMap();
		storage.put(coal.resourceID, 10);

		HashMap<String, Integer> construction = new HashMap();
		construction.put(health.resourceID, 100);

		Castle cast1 = new Castle(storage, construction);

		assertEquals(10, cast1.countStorage(coal.resourceID));
		assertEquals(100, cast1.countConstruction(health.resourceID));

		cast1.addStorage(coal.resourceID, 5);

		assertEquals(15, cast1.countStorage(coal.resourceID));

		cast1.addConstruction(health.resourceID, 10);

		assertEquals(110, cast1.countConstruction(health.resourceID));
		
		cast1.removeStorage(coal.resourceID, 5);
		
		assertEquals(10, cast1.countStorage(coal.resourceID));
		
		cast1.removeConstruction(health.resourceID, 10);
		
		assertEquals(100, cast1.countConstruction(health.resourceID));

	}
}
