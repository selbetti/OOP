package selbetti.oop.big;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;

import org.junit.Test;

public class RequestTest {

	private static final String FIRST_LINE = "177.126.180.83 - - [15/04/2016 19:45:12.357332 -0300] \"GET /meme.jpg HTTP/1.1\" 200 2148 \"-\" \"userid=4d39a692-dc6f-11e3-8db0-a41731050d2d\"";

	@Test
	public void ensureValidObject() throws ParseException
	{
		final Request r = Request.loadObject( FIRST_LINE );
		assertNotNull( "objeto carregado nao pode ser nulo", r );
		assertEquals( 1460760669332L, r.date.getTime() );
		assertEquals( "4d39a692-dc6f-11e3-8db0-a41731050d2d", r.name );
		assertEquals( 2148L, r.timeRequest );
	}

	@Test
	public void ensureInvalidObject() throws ParseException
	{
		final Request request = Request.loadObject( "" );
		assertNull( request );
	}

}
