package selbetti.oop.user;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Searcher {

	public <T> List<T> search( List<T> objects, Function<T, Boolean> filter ) {
		final List<T> newList = new ArrayList<>();
		for ( final T o : objects )
			if ( filter.apply( o ) )
				newList.add( o );
		return newList;
	}

	public static void runAnyWay( Runnable runnable ) {
		try {
			runnable.run();
		} catch ( final Throwable c ) {
			throw new RuntimeException( c );
		}
	}
}
