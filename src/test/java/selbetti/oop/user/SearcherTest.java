package selbetti.oop.user;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.Test;

public class SearcherTest {

	final Searcher searcher = new Searcher();

	@Test
	public void ensureThatCanFilterOnlyEvenNumber() {
		final List<Integer> listOfNumbers = Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 );
		final List<Integer> evenNumbers = searcher.search( listOfNumbers, new EvenNumberFilter() );

		assertEquals( 5, evenNumbers.size() );
		assertEquals( 2, (int)evenNumbers.get( 0 ) );
		assertEquals( 4, (int)evenNumbers.get( 1 ) );
		assertEquals( 6, (int)evenNumbers.get( 2 ) );
		assertEquals( 8, (int)evenNumbers.get( 3 ) );
		assertEquals( 10, (int)evenNumbers.get( 4 ) );
	}

	@Test
	public void ensureThatCanFilterOnlyEvenNumberWithLambdas() {
		final List<Integer> listOfNumbers = Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 );
		final List<Integer> evenNumbers = searcher.search( listOfNumbers, ( i ) -> {
			return i % 2 == 0;
		} );

		assertEquals( 5, evenNumbers.size() );
		assertEquals( 2, (int)evenNumbers.get( 0 ) );
		assertEquals( 4, (int)evenNumbers.get( 1 ) );
		assertEquals( 6, (int)evenNumbers.get( 2 ) );
		assertEquals( 8, (int)evenNumbers.get( 3 ) );
		assertEquals( 10, (int)evenNumbers.get( 4 ) );
	}

	@Test
	public void ensureThatCanFilterOnlyEvenNumberWithLambdas2() {
		final List<Integer> listOfNumbers = Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 );
		final List<Integer> evenNumbers = searcher.search( listOfNumbers, i -> i % 2 == 0 );

		assertEquals( 5, evenNumbers.size() );
		assertEquals( 2, (int)evenNumbers.get( 0 ) );
		assertEquals( 4, (int)evenNumbers.get( 1 ) );
		assertEquals( 6, (int)evenNumbers.get( 2 ) );
		assertEquals( 8, (int)evenNumbers.get( 3 ) );
		assertEquals( 10, (int)evenNumbers.get( 4 ) );

		Searcher.runAnyWay( ( ) -> {

		} );
	}

	@Test
	public void ensureThatCanFilterOnlyEvenNumberWithLambdas3() {
		final List<Integer> listOfNumbers = Arrays.asList( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 );
		final List<Integer> evenNumbers = searcher.search( listOfNumbers, SearcherTest::isEven );

		assertEquals( 5, evenNumbers.size() );
		assertEquals( 2, (int)evenNumbers.get( 0 ) );
		assertEquals( 4, (int)evenNumbers.get( 1 ) );
		assertEquals( 6, (int)evenNumbers.get( 2 ) );
		assertEquals( 8, (int)evenNumbers.get( 3 ) );
		assertEquals( 10, (int)evenNumbers.get( 4 ) );

		Searcher.runAnyWay( ( ) -> {

		} );
	}

	Boolean returnTrueIfNumberIsEven( Integer t ) {
		return t % 2 == 0;
	}

	static Boolean isEven( Integer t ) {
		return t % 2 == 0;
	}
}

class EvenNumberFilter implements Function<Integer, Boolean> {

	@Override
	public Boolean apply( Integer t ) {
		return t % 2 == 0;
	}
}
