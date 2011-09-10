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
        storage.put(coal.resourceId, 10);
        storage.put(health.resourceId, 100);

        Castle cast1 = new Castle(storage);

        assertEquals(10, cast1.countResource(coal.resourceId));
        assertEquals(100, cast1.countResource(health.resourceId));

        cast1.addResource(coal.resourceId, 5);

        assertEquals(15, cast1.countResource(coal.resourceId));

        cast1.addResource(health.resourceId, 10);

        assertEquals(110, cast1.countResource(health.resourceId));

        cast1.removeResource(coal.resourceId, 5);

        assertEquals(10, cast1.countResource(coal.resourceId));

        cast1.removeResource(health.resourceId, 10);

        assertEquals(100, cast1.countResource(health.resourceId));

        cast1.removeResource(coal.resourceId, 5);

        assertEquals(10, cast1.countResource(coal.resourceId));

        cast1.removeResource(health.resourceId, 10);

        assertEquals(100, cast1.countResource(health.resourceId));
        
        int howMany = cast1.removeResource(coal.resourceId, 20);
        
        assertEquals(10, howMany);
        
        assertEquals(0, cast1.countResource(coal.resourceId));

    }
}
