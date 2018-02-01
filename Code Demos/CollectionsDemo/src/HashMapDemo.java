import java.util.HashMap;
import java.util.Scanner;

public class HashMapDemo {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		HashMap<Integer, Double> raceTimes = new HashMap<Integer, Double>();

		raceTimes.put(5, 23.56);
		raceTimes.put(15, 85.36);
		raceTimes.put(25, 123.43);

		// this will only put a value if the key does not exist
		raceTimes.putIfAbsent(22, 78.97);
		int userDistKm = 0;
		System.out.println("Please enter the race distance in km: (please enter 0 to exit)");

		userDistKm = scan.nextInt();

		while (userDistKm != 0) {
			System.out.print("Best distance for " + userDistKm + " km race is: ");
			System.out.print(raceTimes.get(userDistKm));
			System.out.println(" minutes.");
			System.out.println();

			System.out.println("Please enter the race distance in km: (please enter 0 to exit)");
			userDistKm = scan.nextInt();
		}

		System.out.println("Goodbye!");

		System.out.println(raceTimes.containsKey(100)); // returns a boolean of true if the key exists
		System.out.println(raceTimes.containsValue(78.97)); // returns a boolean of true if the value exists
		System.out.println(raceTimes.keySet()); // this returns a set of the keys that exist -- may be in different
												// order each time
		
		raceTimes.clear();
		System.out.println(raceTimes);

	}

}
