package selbetti.oop.meta;

import java.util.regex.Pattern;

/**
 *
 */
public class Validations {

	public static boolean doesNotContains(String str, String s) {
		return str != null && !str.contains( s );
	}

	public static boolean isAtMostLonger(String str, int i) {
		return str != null && str.length() <= i;
	}

	public static boolean isValidEmail(String email) {
		String regEx = "[^@]+@[^\\.@:]+\\.[^@:]+";
		return isValidRegExp( email, regEx );
	}

	public static boolean isValidRegExp( String str, String regExp ) {
		return Pattern.compile( regExp ).matcher( str ).matches();
	}

	public static boolean isAtLeastLonger(String str, int size ){
		return str != null && str.length() >= size;
	}
}
