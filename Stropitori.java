package stropitori;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 
 * @author Alexa
 *
 */
public class Stropitori {

	public Sprinkler[] sprinklers;
	public long length;
	public String name;

	/**
	 * Method which calculates the maximum number of active sprinklers. It goes
	 * through the array of sprinklers and checks whether it can be turned on
	 * and in which direction. If it can be turned to the left, it is, and the
	 * position of the last sprinkled point if the position of the sprinkler. If
	 * it can be to the right, the last sprinkled point gets the value of the
	 * position plus the power of the sprinkle. Then, the number of active
	 * sprinkles is incremented. If it cannot be turned on, the last sprinkled
	 * point gets the value of the position of the sprinkle, but the number of
	 * active sprinkles is not incremented.
	 * 
	 * The conditions are: 
	 * --> in order to be able to sprinkle to the left, the
	 * sprinkler must not go over the field or tough the last sprinkled point.
	 * --> in order to be able to sprinkle to the right, the sprinkler must not
	 * go over the field or touch the next sprinkler.
	 * 
	 * @return
	 */
	public int getSolution() {
		int count = 0;

		long lowerBound = -1;

		for (int i = 0; i < sprinklers.length; i++) {

			long left = sprinklers[i].position - sprinklers[i].power;
			long right = sprinklers[i].position + sprinklers[i].power;

			if (left >= 0 && left > lowerBound) {
				count++;
				lowerBound = sprinklers[i].position;
				continue;
			}

			if (i == (sprinklers.length - 1)) {
				if (right <= length && lowerBound < right) {
					count++;
					lowerBound = right;
					continue;
				}
				lowerBound = sprinklers[i].position;
				continue;
			}
			if (right < sprinklers[i + 1].position && right <= length && lowerBound < right) {
				count++;
				lowerBound = right;

				continue;
			}
			lowerBound = sprinklers[i].position;
		}
		return count;
	}

	/**
	 * Method which read the name of the stadium, the number of sprinklers and
	 * the length of the stadium. It then read the positions of the sprinklers,
	 * adds them to sprinklers' array and continues to read the power of each
	 * sprinkler and add the value to each one of them.
	 */
	public void read() {
		MyScanner sc = new MyScanner();

		name = sc.nextLine();
		int numberOfSprinklers = sc.nextInt();
		length = sc.nextLong();

		sprinklers = new Sprinkler[numberOfSprinklers];

		for (int i = 0; i < numberOfSprinklers; i++) {
			sprinklers[i] = new Sprinkler(sc.nextInt());
		}

		for (int i = 0; i < numberOfSprinklers; i++) {
			sprinklers[i].power = sc.nextInt();
		}
	}

	/**
	 * Main method, which calls the methods that read the information from the
	 * file and calculate the maximum number of active sprinklers. It then
	 * writes the result to the output file.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Stropitori teren = new Stropitori();

		teren.read();

		try {
			PrintWriter writer = new PrintWriter("stropitori.out", "UTF-8");
			int count = teren.getSolution();

			writer.println(count);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
