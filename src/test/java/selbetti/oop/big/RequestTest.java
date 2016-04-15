package selbetti.oop.big;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RequestTest {
	private static final String FIRST_LINE = "177.126.180.83 - - [08/Apr/2016:18:19:05.268007 -0300] \"GET /meme.jpg HTTP/1.1\" 200 2148 \"-\" \"userid=4d3acd7e-dc6f-11e3-8db0-a41731050d2d\"";

	@Test
	public void ensureValidObject()
	{
		final Request r = Request.loadObject( FIRST_LINE );
		assertNotNull( r );
		assertEquals( "08/Apr/2016:18:19:05.268007", r.date );
		assertEquals( "nome4d3acd7e-dc6f-11e3-8db0-a41731050d2d", r.name );
	}

	@Test
	public void ensureInvalidObject()
	{
		final Request request = Request.loadObject( "" );
		assertNull( request );
	}
}
