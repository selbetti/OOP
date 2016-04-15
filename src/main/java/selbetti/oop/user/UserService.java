package selbetti.oop.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {

	final AtomicLong idGenerator = new AtomicLong();
	final Map<Long, User> users = new HashMap<>();

	public List<User> retrieveAllUsers() {
		return new ArrayList<>( users.values() );
	}

	public void insertUser( User user ) {
		user = user.withId( idGenerator.incrementAndGet() );
		users.put( user.getId(), user );
	}

	public void updateUser( long i, User user ) {
		user = user.withId( i );
		users.put( i, user );
	}

	public User retrieveUser( long i ) {
		return users.get( i );
	}

	public List<User> searchByName( String string ) {
		return null;
	}
}
