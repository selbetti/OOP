package selbetti.oop.user;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {

	final List<User> users;

	public UserService() {
		users = new ArrayList<>();
	}

	public List<User> retrieveAllUsers() {
		return users;
	}

	public void insertUser( User user ) {
		users.add( user );
	}

	public void updateUser( int i, User user ) {
		// TODO Auto-generated method stub

	}

	public User retrieveUser( int i ) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> searchByName( String string ) {
		// TODO Auto-generated method stub
		return null;
	}
}
