package selbetti.oop.big;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticGrouperTest {

	final List<Request> requestListUser1 = Arrays.asList(
			new Request( "1", null, null, 1 ),
			new Request( "1", null, null, 2 ) );

	final List<Request> requestListUser2 = Arrays.asList(
			new Request( "2", null, null, 5 ) );

	final List<Request> requestListAllUsers = new ArrayList<>();

	@Before
	public void Init() {
		requestListAllUsers.addAll( requestListUser1 );
		requestListAllUsers.addAll( requestListUser2 );
	}

	@Test
	public void ensureThatCanIncludeOneStatistic() {
		final StatisticGrouper sGrouper = new StatisticGrouper();
		sGrouper.accept( requestListUser2.get( 0 ) );
		assertEquals( 1, sGrouper.data.keySet().size() );
		assertEquals( 1, sGrouper.total.getRequestCount() );
	}

	@Test
	public void ensureCanIncludeMoreThanOneStatistic() {
		final StatisticGrouper sGrouper = new StatisticGrouper();
		for ( final Request request : requestListAllUsers )
			sGrouper.accept( request );

		assertEquals( 3, sGrouper.data.get( "1" ).getRequestTotalTime() );
		assertEquals( 5, sGrouper.data.get( "2" ).getRequestTotalTime() );

	}

}
