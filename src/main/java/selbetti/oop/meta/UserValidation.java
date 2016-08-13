package selbetti.oop.meta;

/**
 *
 */
public class UserValidation {

	final static BertucciValidator validator = BertucciValidator.create( User.class );

	public static boolean validate(User user) throws IllegalAccessException {
		return validator.isValid( user );
		/*isValidEmail( user.email )
			&& doesNotContains( user.username, " " )
			&& isAtLeastLonger( user.name, 3 )
			&& isAtMostLonger( user.name, 255 )*/
	}
}
