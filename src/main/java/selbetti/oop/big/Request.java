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

	public static Request loadObject( String line ) throws ParseException {
		try {
			final String[] dados = line.split( " " );
			String nome = dados[12];
			final Date data = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss.SSSSSS" ).parse( dados[3].replace( "[", "" ).trim() + " "
					+ dados[4] );
			nome = nome.substring( nome.indexOf( "=" ) + 1, nome.lastIndexOf( '"' ) );
			return new Request( nome, data );
		} catch ( final ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e ) {
			return null;
		}
	}
}
