package selbetti.oop.meta;

/**
 *
 */
public class DefaultHttpClient implements HttpClient {

	public void send(String s, User user) {
		//if ( new Random().nextBoolean() )
			throw new RuntimeException("Random failure");
	}
}
