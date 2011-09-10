
import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class ChangeTest extends UnitTest {

    @Test
    public void applyTest() {

        Resource coal = new Resource("coal", "Wegiel", true, true);

        Castle castle1 = new Castle(new HashMap<String, Integer>());
        Castle castle2 = new Castle(new HashMap<String, Integer>());
        castle1.setEnemyCastle(castle2);
        castle2.setEnemyCastle(castle1);

        assertEquals(0, castle1.countResource(coal.resourceID));
        assertEquals(0, castle2.countResource(coal.resourceID));

        /**
         * add 5 to my castle
         */
        Change change1 = new Change(coal.resourceID, "Add", 5.0, 0);
        change1.apply(castle1);
        assertEquals(5, castle1.countResource(coal.resourceID));

        /**
         * add 8 to enemy castle
         */
        Change change2 = new Change(coal.resourceID, "Add", 8.0, 1);
        change2.apply(castle1);
        assertEquals(8, castle1.getEnemyCastle().countResource(coal.resourceID));

        /**
         * remove 1 from my castle
         */
        Change change3 = new Change(coal.resourceID, "Remove", 1.0, 0);
        change3.apply(castle1);
        assertEquals(4, castle1.countResource(coal.resourceID));

        /**
         * remove 2 from enemy castle
         */
        Change change4 = new Change(coal.resourceID, "Remove", 2.0, 1);
        change4.apply(castle1);
        assertEquals(6, castle1.getEnemyCastle().countResource(coal.resourceID));
    }
}
