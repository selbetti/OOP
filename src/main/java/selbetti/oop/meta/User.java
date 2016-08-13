package selbetti.oop.meta;

import java.util.List;
import lombok.Data;

/**
 *
 */
@Data
public class User {

	@ValidRegExp( "[^@]+@[^@:]+\\.[^@:]+" )
	String email;

	@Range( min=3, max=255 )
	String name;

	@DoesNotContains( " " )
	String username;

	@Range( min=3, max=255 )
	String password;

	@Size( min=1 )
	List<Profile> profiles;
}
