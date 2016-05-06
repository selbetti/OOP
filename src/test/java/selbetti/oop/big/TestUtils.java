package selbetti.oop.big;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TestUtils {

	static String readFile( final String name ) throws IOException {
		final Scanner scanner = new Scanner( new File( name ) );
		try {
			return scanner.useDelimiter( "\\Z" ).next();
		} finally {
			scanner.close();
		}
	}

	static boolean deleteRecursively( File directory ) {
		if ( directory.isDirectory() )
			for ( final File file : directory.listFiles() )
				if ( !file.delete() && !deleteRecursively( file ) )
					return false;
		return directory.delete() || !directory.exists();
	}
}
