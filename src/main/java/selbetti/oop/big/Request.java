package selbetti.oop.big;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Request {

	String name;
	Date date;
	String line;
	long timeRequest;

	public static Request loadObject( String line ) throws ParseException {
		try {
			final String[] dados = line.split( " " );
			return new Request( getNome( dados ), getDate( dados ), line, getTimeRequest( dados ) );
		} catch ( final ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e ) {
			return null;
		}
	}

	private static long getTimeRequest( String[] dados ) {
		return Long.valueOf( dados[10] );
	}

	private static Date getDate( final String[] dados ) throws ParseException {
		return new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss.SSSSSS" ).parse( dados[3].replace( "[", "" ).trim() + " "
				+ dados[4] );
	}

	private static String getNome( final String[] dados ) {
		String nome = dados[12];
		nome = nome.substring( nome.indexOf( "=" ) + 1, nome.lastIndexOf( '"' ) );
		return nome;
	}
}
