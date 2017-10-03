package adn;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Adn {

	/**
	 * Method which checks whether the Mars sequence is the same as one of the
	 * ones from Earth.
	 * 
	 * @param sc
	 *            - the scanner with which the info from the file i sread
	 * @return 1 - if they are the same, 0 - otherwise
	 */
	public static int oneSequence(MyScanner sc) {
		String human = sc.next();
		String alien = sc.next();

		System.out.println("1: " + human + " " + alien);

		if (human.compareTo(alien) == 0) {
			return 1;
		}

		return 0;
	}

	/**
	 * Method which uses a bidimensional matrix to check whether a sequence is
	 * an overlapping of two other sequences.
	 * 
	 * @param sc
	 *            - the scanner with which the info from the file i sread
	 * @return 1 - if true, 0 - otherwise
	 */
	public static int twoSequences(MyScanner sc) {
		String human1 = sc.next();
		String human2 = sc.next();

		String alien = sc.next();

		System.out.println("2: " + human1 + " " + human2 + " " + alien);

		int h1Len = human1.length();
		int h2Len = human2.length();

		if ((h1Len + h2Len) != alien.length()) {
			return 0;
		}
		int[][] matrix = new int[h1Len + 1][h2Len + 1];

		for (int i = 0; i <= h1Len; i++) {
			for (int j = 0; j <= h2Len; j++) {
				matrix[i][j] = 0;
			}
		}

		for (int i = 0; i <= h1Len; i++) {
			for (int j = 0; j <= h2Len; j++) {
				if (i == 0 && j == 0) {
					matrix[i][j] = 1;
					// If we use none of the first word's letter and j of the
					// second's
				} else if (i == 0 && human2.charAt(j - 1) == alien.charAt(j - 1)) {
					matrix[i][j] = matrix[i][j - 1];
				} else if (i == 0 && human2.charAt(j - 1) != alien.charAt(j - 1)) {
					matrix[i][j] = 0;
					// If we use none of the second word's letters and i of the
					// first's
				} else if (j == 0 && human1.charAt(i - 1) == alien.charAt(i - 1)) {
					matrix[i][j] = matrix[i - 1][j];
				} else if (j == 0 && human1.charAt(i - 1) != alien.charAt(i - 1)) {
					matrix[i][j] = 0;
					// If we use i letter from the first and j letter from the
					// second and the i letter from the first is the same as the
					// i+j-1 from the alien sequence
				} else if (human1.charAt(i - 1) == alien.charAt(i + j - 1)
						&& human2.charAt(j - 1) != alien.charAt(i + j - 1)) {
					matrix[i][j] = matrix[i - 1][j];
					continue;
					// If we use i letter from the first and j letter from the
					// second and the j letter from the second is the same as
					// the
					// i+j-1 from the alien sequence
				} else if (human1.charAt(i - 1) != alien.charAt(i + j - 1)
						&& human2.charAt(j - 1) == alien.charAt(i + j - 1)) {
					matrix[i][j] = matrix[i][j - 1];
					continue;
					// If we use i letter from the first and j letter from the
					// second and the i letter from the first and the j letter
					// from the second are the same as the i+j-1 from the alien
					// sequence
				} else if (human1.charAt(i - 1) == alien.charAt(i + j - 1)
						&& human2.charAt(j - 1) == alien.charAt(i + j - 1)) {
					matrix[i][j] = matrix[i][j - 1];
					if (matrix[i][j] == 0) {
						matrix[i][j] = matrix[i - 1][j];
					}
				}
			}
		}

		return matrix[h1Len][h2Len];
	}

	public static int threeSequences(MyScanner sc) {
		String human1 = sc.next();
		String human2 = sc.next();
		String human3 = sc.next();
		String alien = sc.next();

		//System.out.println("3: " + human1 + " " + human2 + " " + human3 + " " + alien);

		int h1Len = human1.length();
		int h2Len = human2.length();
		int h3Len = human3.length();

		if ((h1Len + h2Len + h3Len) != alien.length()) {
			return 0;
		}

		int[][][] matrix = new int[h1Len + 1][h2Len + 1][h3Len + 1];

		for (int i = 0; i <= h1Len; i++) {
			for (int j = 0; j <= h2Len; j++) {
				for (int k = 0; k <= h3Len; k++) {
					matrix[i][j][k] = 0;
				}
			}
		}

		for (int i = 0; i <= h1Len; i++) {
			for (int j = 0; j <= h2Len; j++) {
				for (int k = 0; k <= h3Len; k++) {
					// System.out.println(i + " " + j + " " + k);

					if (i == 0 && j == 0 && k == 0) {
						matrix[i][j][k] = 1;
						/*
						 * If we use none of the first and third word's letter
						 * and j of the second's
						 */
					} else if (i == 0 && k == 0 && human2.charAt(j - 1) == alien.charAt(j - 1)) {
						matrix[i][j][k] = matrix[i][j - 1][k];
					} else if (i == 0 && k == 0 && human2.charAt(j - 1) != alien.charAt(j - 1)) {
						matrix[i][j][k] = 0;
						/*
						 * If we use none of the second and third word's letters
						 * and i of the first's
						 */
					} else if (j == 0 && k == 0 && human1.charAt(i - 1) == alien.charAt(i - 1)) {
						matrix[i][j][k] = matrix[i - 1][j][k];
					} else if (j == 0 && k == 0 && human1.charAt(i - 1) != alien.charAt(i - 1)) {
						matrix[i][j][k] = 0;
						/*
						 * If we use none of the first and second word's letter
						 * and k of the third's
						 */
					} else if (j == 0 && i == 0 && human3.charAt(k - 1) == alien.charAt(k - 1)) {
						matrix[i][j][k] = matrix[i][j][k - 1];
					} else if (j == 0 && i == 0 && human3.charAt(k - 1) != alien.charAt(k - 1)) {
						matrix[i][j][k] = 0;
						/*
						 * If we use i letter from the first and j letter from
						 * the second and the i letter from the first is the
						 * same as the i+j-1 from the alien sequence
						 */
					} else if (i == 0 && human2.charAt(j - 1) == alien.charAt(i + j + k - 1)
							&& human3.charAt(k - 1) != alien.charAt(i + j + k - 1)) {
						matrix[i][j][k] = matrix[i][j - 1][k];
					} else if (i == 0 && human2.charAt(j - 1) != alien.charAt(i + j + k - 1)
							&& human3.charAt(k - 1) == alien.charAt(i + j + k - 1)) {
						matrix[i][j][k] = matrix[i][j][k - 1];
					} else if (i == 0 && human2.charAt(j - 1) != alien.charAt(i + j + k - 1)
							&& human3.charAt(k - 1) == alien.charAt(i + j + k - 1)) {
						matrix[i][j][k] = matrix[i][j - 1][k];
						if (matrix[i][j][k] == 0) {
							matrix[i][j][k] = matrix[i][j][k - 1];
						}
					} else if (j == 0 && human1.charAt(i - 1) == alien.charAt(i + j + k - 1)
							&& human3.charAt(k - 1) != alien.charAt(i + j + k - 1)) {
						matrix[i][j][k] = matrix[i - 1][j][k];
					} else if (j == 0 && human1.charAt(i - 1) != alien.charAt(i + j + k - 1)
							&& human3.charAt(k - 1) == alien.charAt(i + j + k - 1)) {
						matrix[i][j][k] = matrix[i][j][k - 1];
					} else if (j == 0 && human1.charAt(i - 1) == alien.charAt(i + j + k - 1)
							&& human3.charAt(k - 1) == alien.charAt(i + j + k - 1)) {
						matrix[i][j][k] = matrix[i][j][k - 1];
						if (matrix[i][j][k] == 0) {
							matrix[i][j][k] = matrix[i - 1][j][k];
						}
					} else if (k == 0 && human1.charAt(i - 1) == alien.charAt(i + j + k - 1)
							&& human2.charAt(j - 1) != alien.charAt(i + j + k - 1)) {
						matrix[i][j][k] = matrix[i - 1][j][k];
					} else if (k == 0 && human1.charAt(i - 1) != alien.charAt(i + j + k - 1)
							&& human2.charAt(j - 1) == alien.charAt(i + j + k - 1)) {
						matrix[i][j][k] = matrix[i][j - 1][k];
						if (matrix[i][j][k] == 0) {
							matrix[i][j][k] = matrix[i - 1][j][k];
						}
					} else if (k == 0 && human1.charAt(i - 1) == alien.charAt(i + j + k - 1)
							&& human2.charAt(j - 1) == alien.charAt(i + j + k - 1)) {
						matrix[i][j][k] = matrix[i][j - 1][k];
					} else if (i == 0 || j == 0 || k == 0) {
						matrix[i][j][k] = 0;
					} else if (human1.charAt(i - 1) == alien.charAt(i + j + k - 1)
							&& human2.charAt(j - 1) != alien.charAt(i + j + k - 1)
							&& human3.charAt(k - 1) != alien.charAt(i + j + k - 1)) {
						matrix[i][j][k] = matrix[i - 1][j][k];
						/*
						 * If we use i letters from the first and j letters from
						 * the second and the j letter from the second is the
						 * same as the i+j-1 from the alien sequence
						 */
					} else if (human1.charAt(i - 1) != alien.charAt(i + j + k - 1)
							&& human2.charAt(j - 1) == alien.charAt(i + j + k - 1)
							&& human3.charAt(k - 1) != alien.charAt(i + j + k - 1)) {
						matrix[i][j][k] = matrix[i][j - 1][k];
						/*
						 * If we use i letters from the first and j letters from
						 * the second and the i letter from the first and the j
						 * letter from the second are the same as the i+j-1 from
						 * the alien sequence
						 */
					} else if (human1.charAt(i - 1) != alien.charAt(i + j + k - 1)
							&& human2.charAt(j - 1) != alien.charAt(i + j + k - 1)
							&& human3.charAt(k - 1) == alien.charAt(i + j + k - 1)) {
						matrix[i][j][k] = matrix[i][j][k - 1];
					} else if (human1.charAt(i - 1) == alien.charAt(i + j + k - 1)
							&& human2.charAt(j - 1) == alien.charAt(i + j + k - 1)
							&& human3.charAt(k - 1) == alien.charAt(i + j + k - 1)) {
						matrix[i][j][k] = matrix[i][j - 1][k];
						if (matrix[i][j][k] == 0) {
							matrix[i][j][k] = matrix[i - 1][j][k];
							if (matrix[i][j][k] == 0) {
								matrix[i][j][k] = matrix[i][j][k - 1];
							}
						}
					}
				}
			}
		}

		return matrix[h1Len][h2Len][h3Len];

	}

	public static int fourSequences(MyScanner sc) {
		String human1 = sc.next();
		String human2 = sc.next();
		String human3 = sc.next();
		String human4 = sc.next();
		String alien = sc.next();

		System.out.println("4: ");
		
		return 0;
	}

	public static void read() {
		MyScanner sc = new MyScanner();
		try {
			PrintWriter writer = new PrintWriter("adn.out", "UTF-8");
			int T = sc.nextInt();

			for (int i = 0; i < T; i++) {
				int nr = sc.nextInt();
				int solution = 0;

				switch (nr) {
				case 1:
					solution = oneSequence(sc);
					break;
				case 2:
					solution = twoSequences(sc);
					break;
				case 3:
					solution = threeSequences(sc);
					break;
				case 4:
					solution = fourSequences(sc);
					break;
				default:
					break;
				}

				writer.println(solution);

			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		read();
	}

}
