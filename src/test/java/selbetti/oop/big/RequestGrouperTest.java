package selbetti.oop.big;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RequestGrouperTest {

	final List<Request> requestListUser1 = Arrays.asList(
			new Request( "1", null, null ),
			new Request( "1", null, null ) );

	final List<Request> requestListUser2 = Arrays.asList(
			new Request( "2", null, null ) );

	final List<Request> requestListAllUsers = new ArrayList<>();

	@Before
	public void Init() {
		requestListAllUsers.addAll( requestListUser1 );
		requestListAllUsers.addAll( requestListUser2 );
	}

	@Test
	public void ensureThatCanGroup2DifferentUsers() throws ParseException
	{
		final RequestWriter writer = mock( RequestWriter.class );
		final RequestGrouper grouper = new RequestGrouper( writer );
		for ( final Request request : requestListAllUsers )
			grouper.accept( request );
		assertEquals( 2, grouper.data.keySet().size() );
	}

	@Test
	public void ensureThatGroupHasValidValues() throws ParseException
	{
		final RequestWriter writer = mock( RequestWriter.class );
		final RequestGrouper grouper = new RequestGrouper( writer );
		requestListAllUsers.forEach( grouper::accept );
		assertEquals( requestListUser1, grouper.data.get( "1" ) );
		assertEquals( requestListUser2, grouper.data.get( "2" ) );

	}
}
