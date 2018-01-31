/*
 * Goal: Find the average of the diagonal and the average of the reverse 
 * diagonal for the 2D array
 */
public class Array2DCodeChallenge {

	public static void main(String[] args) {
		int[][] arr2 = { { 3, 4, 7, 8 }, { 8, 12, 34, 56 }, { 9, 32, 11, 15 }, { 6, 72, 33, 4 } };

		int sum = 0;
		int sumRevDiag = 0;
		int revDiagPos = arr2.length - 1;

		for (int i = 0; i < arr2.length; i++) {
			sum += arr2[i][i]; // for regular diagonal
			sumRevDiag += arr2[i][revDiagPos]; // for reverse diagonal
			revDiagPos--; // for reverse diagonal
		}

		double avgSum = sum / (double) arr2.length;
		double avgSumDiag = sumRevDiag / (double) arr2.length;

		// this is just an example of how to use a for each loop to print all the
		// elements in a 2D array
		// do not use this if you are trying to find the average of the diagonal
		for (int[] row : arr2) { // this is the row of arrays
			for (int column : row) { // this is to iterate through the elements in the columns of the array
				System.out.println(column);
			}

		}
	}

}
