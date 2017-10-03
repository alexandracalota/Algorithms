package points;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 
 * @author Alexa
 *
 */
public class Points {

	LinkedList<Point> pointsArr = new LinkedList<Point>();
	LinkedList<Interval> intervalsArr = new LinkedList<Interval>();

	/**
	 * Reads the number of points, the number of intervals and then adds the
	 * numbers one by one in a list of points and the intervals in a list of
	 * intervals. It then sorts the intervals.
	 */
	public void read() {

		MyScanner sc = new MyScanner();

		int N = sc.nextInt();
		int M = sc.nextInt();

		for (int i = 0; i < N; i++) {
			this.pointsArr.add(new Point(sc.nextInt()));
		}

		for (int i = 0; i < M; i++) {
			this.intervalsArr.add(new Interval(sc.nextInt(), sc.nextInt()));
		}

		Collections.sort(intervalsArr);
	}

	/**
	 * Gets the minimum number of intervals which have to be used in order to
	 * cover all given points. The method goes through the list of points until
	 * all points have been covered. Until there are no more uncovered points,
	 * the method takes the first uncovered point and chooses from the intervals
	 * that cover it the one that can cover the most points going to the right.
	 * Then, all points covered by the interval are removed from the list, so
	 * that when we extract the first point, it is the first uncovered point.
	 * Each time an interval is found for a point, the number of intervals used
	 * is incremented.
	 * 
	 * @return
	 */
	public int getMin() {
		int nrI = 0, maxCoverage = 0, count = 0;

		while (!pointsArr.isEmpty() && !intervalsArr.isEmpty()) {
			nrI++;
			maxCoverage = 0;
			count = 0;
			Interval solution = null;
			Interval auxI = intervalsArr.peek();
			Point auxP = pointsArr.poll();
			while (auxI.lowerBound <= auxP.value && !intervalsArr.isEmpty()) {
				intervalsArr.poll();
				count = auxI.higherBound - auxI.lowerBound;
				if (maxCoverage < count) {
					solution = auxI;
					maxCoverage = count;
				}
				if (!intervalsArr.isEmpty()) {
					auxI = intervalsArr.peek();
				}
			}
			while (solution.higherBound >= auxP.value && !pointsArr.isEmpty()) {
				auxP = pointsArr.poll();
				if (!pointsArr.isEmpty()) {
					auxP = pointsArr.peek();
				}
			}
		}

		return nrI;
	}

	/**
	 * Main method. The information from the input file is read, the minimum
	 * number of intervals is calculated and then written to the output file.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Points points = new Points();

		points.read();

		int nrIntervals = points.getMin();

		System.out.println("Number of intervals: " + nrIntervals);

		try {
			PrintWriter writer = new PrintWriter("points.out", "UTF-8");

			writer.println(nrIntervals);

			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
