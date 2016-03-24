package selbetti.oop.user;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith( MockitoJUnitRunner.class )
public class UserServiceTest {

	@Mock
	List<User> users;

	UserService userService;

	@Before
	public void createUserService() {
		userService = new UserService( users );
	}

	@Test
	public void ensureThatCanRetrieveAllUsers() {
		doReturn( 0 ).when( users ).size();
		final List<User> foundUsers = userService.retrieveAllUsers();
		assertEquals( 0, foundUsers.size() );
	}

	@Test
	public void ensureThatCanInsertNewUser() {
		final User user = new User( "MockTest", "mock@te.st" );
		userService.insertUser( user );
		verify( users ).add( user );
	}

}
