package selbetti.oop.big;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StatisticTest {

	@Test
	public void ensureMediaCalc()
	{
		final Statistic statis = new Statistic();
		statis.addTimeAndIncrementCounter( 100L );
		statis.addTimeAndIncrementCounter( 300L );

		assertEquals( 200.0, statis.getAverage(), 0 );
	}

	@Test
	public void ensureThatSumTimeIsCorrect()
	{
		final Statistic statis = new Statistic();
		statis.addTimeAndIncrementCounter( 100L );
		statis.addTimeAndIncrementCounter( 300L );

		assertEquals( 2, statis.getRequestCount() );
		assertEquals( 400L, statis.getRequestTotalTime() );
	}
}
