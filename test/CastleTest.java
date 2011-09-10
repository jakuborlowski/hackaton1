
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
		storage.put(health.resourceID, 100);

		Castle cast1 = new Castle(storage);

		assertEquals(10, cast1.countResource(coal.resourceID));
		assertEquals(100, cast1.countResource(health.resourceID));

		cast1.addResource(coal.resourceID, 5);

		assertEquals(15, cast1.countResource(coal.resourceID));

		cast1.addResource(health.resourceID, 10);

		assertEquals(110, cast1.countResource(health.resourceID));
		
		cast1.removeResource(coal.resourceID, 5);
		
		assertEquals(10, cast1.countResource(coal.resourceID));
		
		cast1.removeResource(health.resourceID, 10);
		
		assertEquals(100, cast1.countResource(health.resourceID));

	}
}
