package selbetti.oop.meta;

import static org.junit.Assert.assertTrue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.Test;

/**
 *
 */
public class ProxyTest {

	@Test
	public void ensureThatCannotSendHttpRequest(){
		final DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.send( "http://ws.selbetti.com.br/usuarios", new User() );
	}

	@Test
	public void ensureThatCanBeNotifiedFromFailuresOnHttpRequest(){
		final AtomicBoolean failureStatus = new AtomicBoolean( false );

		final HttpClient httpClient = HttpClientWrapper.create( new DefaultHttpClient(), failureStatus );

		try { httpClient.send("http://ws.selbetti.com.br/usuarios", new User()); }
		catch ( RuntimeException e ){
			e.printStackTrace();
		}

		assertTrue( failureStatus.get() );
	}
}
