package selbetti.oop.big;

import lombok.Getter;

@Getter
public class Statistic {

	long requestCount;
	long requestTotalTime;

	public void addTimeAndIncrementCounter( long time ) {
		requestTotalTime += time;
		requestCount++;
	}

	public double getAverage() {
		return requestTotalTime / requestCount;
	}

}
