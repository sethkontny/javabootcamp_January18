import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileMethods {
	public static void createDirectory(String dirString) {

		Path dirPath = Paths.get(dirString);
		System.out.println(dirPath.toAbsolutePath());

		// this will only execute if the file is not there
		if (Files.notExists(dirPath)) {
			try {
				Files.createDirectory(dirPath);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("I'm not sure what happened, contact customer service");
			}
		}

	}

	public static void createFile(String fileString) {

		// if you don't want this to go into a folder just use the get method taking in
		// one parameter for the filename
		// use the overloaded get method if you want to add a file inside of a folder
		Path filePath = Paths.get(fileString);

		if (Files.notExists(filePath)) {
			try {
				Files.createFile(filePath);
				System.out.println("Your file was created successfully");
			} catch (IOException e) {
				System.out.println("Hey, something went wrong with the file creation!");
				// e.printStackTrace();
			}
		}

	}

	public static void writeToFile(String fileString, Employee newEE) {
		//Employee addingEmp = newEE;
		Path writeFile = Paths.get(fileString);

		File file = writeFile.toFile(); // the toFile() converts the path to a file object

		try {
			// PrintWriter printOut = new PrintWriter(new FileOutputStream(file)); // this
			// will overwrite the file each time
			PrintWriter printOut = new PrintWriter(new FileOutputStream(file, true)); // the true parameter allows us to
																						// append to the end of the file
			printOut.println(newEE);

			printOut.close(); // closing flushes our data and closes the PrintWriter
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	public static ArrayList<Employee> readFromFile(String filePath) {
		ArrayList<Employee> empList = new ArrayList<>();
		Path readFile = Paths.get(filePath); // the hardcoded value can be changed to use the method
																// parameters -- this should be replaced with the
																// parameters from the method when ready to use
		File file = readFile.toFile();

		try {
			FileReader fr = new FileReader(file);
			// the benefit of using the buffer is to help us store a block of memory that we
			// can go back to and read data from later -- this is more efficient than the
			// Scanner
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			String[] emps = new String[35];
			while (line != null) {
				emps = line.split(",");
				Employee eee = new Employee(emps[0],emps[1],emps[2]);
				empList.add(eee);
				//System.out.println(line);
				line = reader.readLine();
			}
			reader.close(); // this flushes the buffer and closes it

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Something went wrong with this!");
			e.printStackTrace();
		}
		return empList;
	}
}
