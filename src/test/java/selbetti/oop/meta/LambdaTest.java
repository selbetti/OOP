package selbetti.oop.meta;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 */
public class LambdaTest {


	@Test
	public void testeBemBoboLambda(){
		assertEquals( 3, runMathOperation( this::sum ) );
		assertEquals( 1, runMathOperation( this::minus ) );
	}

	public int runMathOperation2( int f, int s, int typeOfOperation ) {
		if ( typeOfOperation == 1 )
			return f + s;
		else
			return f - s;
	}

	public int runMathOperation( MathOperation operation ){
		return operation.execute( 2, 1 );
	}

	int sum( int x, int y ) {
		return x + y;
	}

	int minus( int x, int y ){
		return x - y;
	}


	interface MathOperation {
		int execute( int x, int y );
	}
}

