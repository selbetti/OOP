package selbetti.oop.user;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class User {

	long id;
	String name;
	String email;

	public User( String name, String email ) {
		this( 0, name, email );
	}

	public User withId( long id ) {
		return new User( id, name, email );
	}
}
