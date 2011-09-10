import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class UserTest extends UnitTest {

	@Before
	public void setUp() {
		Fixtures.deleteDatabase();
	}
	
    @Test
    public void userTest() {
        User s = new User("sakowski.mariusz@gmail.com", "abc").save();
		User r = new User("jakub.orlowski@gmail.com", "cde").save();
		
		assertEquals("users where persisted", 2, User.count());
		assertEquals("email is stored", "sakowski.mariusz@gmail.com", s.email);
    }

}
