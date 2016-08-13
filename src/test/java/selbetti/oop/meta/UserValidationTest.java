package selbetti.oop.meta;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 */
public class UserValidationTest {

	@Test
	public void ensureThatCanValidateUser() throws IllegalAccessException {
		final User douglas = createValidUser();
		assertTrue( UserValidation.validate( douglas ) );
	}

	private User createValidUser() {
		final User user = new User();
		user.email = "douglas.schuelter@selbetti.com.br";
		user.name = "Douglas Schuelter";
		user.username = "douglas.schuelter";
		user.password = "1234567789";
		user.profiles = asList( new Profile() );
		return user;
	}
}
