import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileIOMain {

	public static void main(String[] args) {
		// create a String to hold the relative path for our file
		String filePath = "example.txt";

		// create a file object to pass in the file path
		File file = new File(filePath);
		

		// this is an example of a checked exception
		try {
			Scanner scan = new Scanner(file);

			while (scan.hasNextLine()) {
				String line = scan.nextLine(); // reading from text file
				System.out.println(line); // printing those lines to the console
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Hey, something went wrong with the file!");
			// e.printStackTrace();
		}

	}

}
