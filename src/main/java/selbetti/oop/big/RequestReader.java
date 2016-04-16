package selbetti.oop.big;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.function.Consumer;

public class RequestReader {

	BufferedReader file;

	public RequestReader( File pFile )
	{
		try {
			file = new BufferedReader( new FileReader( pFile ) );
		} catch ( final FileNotFoundException e ) {
			throw new RuntimeException( e );
		}
	}

	public Request readRequest() throws ParseException {
		try {
			final String line = file.readLine();
			if ( line != null )
				return Request.loadObject( line );
			else
				return null;
		} catch ( final IOException e ) {
			return null;
		}
	}

	public void readEntireFile( Consumer<Request> requestConsumer ) throws ParseException {
		Request request = null;
		while ( ( request = readRequest() ) != null )
			requestConsumer.accept( request );
	}

}
