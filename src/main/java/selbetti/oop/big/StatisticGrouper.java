package selbetti.oop.big;

import java.util.HashMap;
import java.util.Map;

public class StatisticGrouper implements RequestReaderListener<Request> {

	Map<String, Statistic> data = new HashMap<>();
	Statistic total = new Statistic();

	@Override
	public void accept( Request req ) {
		data.computeIfAbsent( req.getName(), ( s ) -> new Statistic() )
				.addTimeAndIncrementCounter( req.getTimeRequest() );
		total.addTimeAndIncrementCounter( req.getTimeRequest() );
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
