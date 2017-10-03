package points;

/**
 * Class which describes an interval, containing its lower bound and its higher
 * bound. It also implements the Comparable interface, so that a list of
 * Interval objects can be sorted first the lower bound and then by the higher
 * bound.
 * 
 * @author Alexa
 *
 */
public class Interval implements Comparable<Interval> {
	public int lowerBound;
	public int higherBound;

	/**
	 * Constructor which sets the lower and higher bound using given parameters.
	 * 
	 * @param lowerBound
	 * @param higherBound
	 */
	public Interval(int lowerBound, int higherBound) {
		this.lowerBound = lowerBound;
		this.higherBound = higherBound;
	}

	/**
	 * compareTo method of the Comparable interface.
	 */
	@Override
	public int compareTo(Interval that) {
		if (this.lowerBound < that.lowerBound) {
			return -1;
		} else {
			if (this.lowerBound > that.lowerBound) {
				return 1;
			} else {
				int thisLength = this.higherBound - this.lowerBound;
				int thatLength = that.higherBound - that.higherBound;

				if (thisLength > thatLength) {
					return 1;
				} else {
					if (thisLength < thatLength) {
						return -1;
					}
				}
			}
		}
		return 0;
	}
}
