package selbetti.oop.big;

import java.io.File;
import java.text.ParseException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Executor {

	String outputFolder;
	String inputFile;

	public static void main( String[] args ) throws ParseException {
		// TODO Auto-generated method stub
		new Executor( args[0], args[1] ).runJob();
	}

	public void runJob() throws ParseException {
		final RequestReader reader = new RequestReader( new File( inputFile ) );
		final RequestWriter writer = RequestWriter.forceCreateFromFolder( new File( outputFolder ) );
		final RequestGrouper grouper = new RequestGrouper( writer );
		reader.readEntireFile( grouper );
	}

}
