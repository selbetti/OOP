package selbetti.oop.big;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Request {

	String name;
	String date;

	public static Request loadObject( String line ) {
		try {
			final String[] dados = line.split( " " );
			String nome = dados[11];
			nome = nome.substring( 7, nome.indexOf( '"' ) );
			return new Request( nome, dados[3] );
		} catch ( final Exception e ) {
			return null;
		}
	}
}
