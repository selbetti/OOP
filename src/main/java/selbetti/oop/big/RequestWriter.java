package selbetti.oop.big;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RequestWriter {

	File pFile;

	public RequestWriter( File file ) {
		pFile = file;
	}

	public static RequestWriter forceCreateFromFolder( File file ) {
		if ( !file.exists() && !file.mkdirs() )
			throw new IllegalArgumentException();
		return new RequestWriter( file );
	}

	public void sortAndWrite( String userID, List<Request> requests ) {
		requests.sort( ( r1, r2 ) -> r1.date.compareTo( r2.date ) );
		try ( final FileWriter fWriter = new FileWriter( pFile + "/" + userID + ".txt" ) ) {
			for ( final Request request : requests )
				fWriter.write( request.getLine() + "\n" );
		} catch ( final IOException e ) {
			throw new RuntimeException( e );
		}

	}

}
