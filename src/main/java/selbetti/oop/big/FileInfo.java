package selbetti.oop.big;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileInfo {

	BufferedReader file;

	public FileInfo( File pFile )
	{
		try {
			file = new BufferedReader( new FileReader( pFile ) );
		} catch ( final FileNotFoundException e ) {
			throw new RuntimeException( e );
		}
	}

	public String readLine() {
		try {
			return file.readLine();
		} catch ( final IOException e ) {
			return null;
		}
	}

}
