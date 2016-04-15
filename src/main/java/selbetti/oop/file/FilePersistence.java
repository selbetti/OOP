package selbetti.oop.file;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FilePersistence<T> {

	final Map<Long, Integer> index = new HashMap<>();
	final Map<Long, String> tmpFileSystem = new HashMap<>();

	volatile long idGenerator = 0l;

	final Function<T, String> serializer;
	final Function<String, T> unserializer;

	public long insert( T s ) {
		final long id = getNextId();
		final String data = serializer.apply( s );
		index.put( id, data.length() );
		tmpFileSystem.put( id, data );
		return id;
	}

	public long getNextId() {
		return idGenerator++;
	}
}
