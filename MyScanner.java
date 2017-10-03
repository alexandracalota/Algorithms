
// sursa: http://codeforces.com/blog/entry/7018

package stropitori;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Custom Scanner which reads lines from a file and parses the information
 * according to what the user is expecting.
 * 
 * @author Alexa
 *
 */
class MyScanner {
	BufferedReader br;
	StringTokenizer st;

	/**
	 * Constructor which initializes a buffered reader using the known input
	 * file name. The block is surrounded by the methods try and catch in order
	 * to account for possible errors.
	 */
	public MyScanner() {

		FileReader input;
		try {
			input = new FileReader("stropitori.in");

			br = new BufferedReader(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads a line, but parses it using StringTokenizer and returns the next
	 * element.
	 * 
	 * @return the next element
	 */
	String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	/**
	 * Returns the next value read as an integer.
	 * 
	 * @return the next integer
	 */
	int nextInt() {
		return Integer.parseInt(next());
	}

	/**
	 * Returns the next value read as a long.
	 * 
	 * @return the next long
	 */
	long nextLong() {
		return Long.parseLong(next());
	}
	
	/**
	 * Returns the next line in the file.
	 * 
	 * @return the next line
	 */
	String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}
