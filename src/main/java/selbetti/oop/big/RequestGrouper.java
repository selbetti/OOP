package selbetti.oop.big;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestGrouper implements RequestReaderListener<Request> {

	Map<String, List<Request>> data = new HashMap<>();

	final RequestWriter rWriter;

	@Override
	public void accept( Request req ) {
		data.computeIfAbsent( req.getName(), ( s ) -> new ArrayList<>() )
				.add( req );
	}

	@Override
	public void finish() {

		data.forEach( rWriter::sortAndWrite );
	}
}
