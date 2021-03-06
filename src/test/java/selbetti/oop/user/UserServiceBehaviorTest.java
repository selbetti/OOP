package selbetti.oop.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class UserServiceBehaviorTest {

	@Test
	public void ensureThatCanRetrieveAllUsers() {
		final UserService userService = new UserService();
		final Collection<User> users = userService.retrieveAllUsers();
		assertNotNull( users );
		assertEquals( 0, users.size() );
	}

	@Test
	public void ensureThatCanCreateAnUser() throws Exception {
		final UserService userService = new UserService();
		userService.insertUser( new User( "Test", "test@te.st" ) );

		final List<User> users = userService.retrieveAllUsers();
		assertEquals( 1, users.size() );
		assertEquals( "Test", users.get( 0 ).getName() );
	}

	@Test
	public void ensureThatCanUpdateUser() {
		final UserService userService = new UserService();
		userService.insertUser( new User( "Test", "test@te.st" ) );
		assertEquals( 1, userService.retrieveAllUsers().size() );

		userService.updateUser( 1, new User( "Test2", "test2@te.st" ) );
		assertEquals( 1, userService.retrieveAllUsers().size() );

		final User user = userService.retrieveUser( 1 );
		assertEquals( "Test2", user.getName() );
	}

	@Test
	public void ensureThatCanSearchForUsersByName() {
		final UserService userService = new UserService();
		for ( int i = 0; i < 10; i++ )
			userService.insertUser( new User( "Test" + i, "test@te.st" ) );

		final List<User> foundUsers = userService.searchByName( "Test" );
		assertEquals( 10, foundUsers.size() );
	}

}
