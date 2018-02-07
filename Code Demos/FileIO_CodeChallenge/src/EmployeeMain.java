/* Create a class named Employees with these variables:
 * 
 * String name
 * String age (can also be an int variable)
 * String favoriteFood
 * 
 * Your goal: 
 * Write an application that lets users add 
 * new employees with the associated fields. 
 * Users should also be able to see a list of all 
 * employees and retrieve data regarding their age 
 * and/or favorite food if the employee is selected 
 * from the list. 
 * 
 */
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeMain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cont = "y";

		while (cont.equalsIgnoreCase("y")) {

			System.out.println("Employees: ");
			System.out.println("________________");
			// FileMethods.createFile("employees.txt");
			ArrayList<Employee> eeList = FileMethods.readFromFile("employees.txt");
			int i = 0;
			for (Employee e : eeList) {
				System.out.println(++i + ". " + e.getName());
			}

			int choice = Validator.getInt(scan, "Please enter a selection from above ", 1, i);
			String name = eeList.get(choice - 1).getName();
			String age = eeList.get(choice - 1).getAge();
			String favFood = eeList.get(choice - 1).getFavFood();
			System.out.println(name + " is " + age + " years old, and " + name + "\'s favorite food is " + favFood);
			System.out.println();
			System.out.println("Would you like to add an employee to the list? y/n ");
			String addEmp = scan.nextLine();
			if (addEmp.equalsIgnoreCase("y")) {
				String newName = Validator.getString(scan, "Name: ");
				String newAge = Validator.getString(scan, "Age: ");
				String newFavFood = Validator.getString(scan, "Favorite Food: ");
				Employee eeNew = new Employee(newName, newAge, newFavFood);
				FileMethods.writeToFile("employees.txt", eeNew);
				System.out.println(eeNew + " was added to the list!");
			}

			// this is where your code should end if your using this as a template
			System.out.println("Do you want to continue? y/n");
			cont = scan.nextLine();

		}

		// Let the user know the program has ended
		System.out.println("Goodbye");

		scan.close();

	}

}
