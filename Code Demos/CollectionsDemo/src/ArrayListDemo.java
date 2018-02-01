import java.util.ArrayList;

public class ArrayListDemo {

	public static void main(String[] args) {
		// This is not the most efficient data structure to use when adding to middle
		// because all index positions need to move
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> test = new ArrayList<String>();
		names.add("Antonella");
		names.add("James");
		names.add("Ben");
		names.add("Andrew");

		test.add("Antonella");
		test.add("Ben");
		names.removeAll(test); // this is a scrubber to compare values from another list

		System.out.println(names);
		System.out.println();
		for (int i = 0; i < names.size(); i++) {
			// System.out.println(names.get(i));
			if (names.get(i).equalsIgnoreCase("James")) {
				// System.out.println("James is in the list");
			}
		}

		for (String name : names) {
			System.out.println(name);
		}

		names.add(0, "Nikki"); // adding values like this causes all the elements to move down in the index
		System.out.println(names);

		names.set(0, "Blaise");
		System.out.println(names);

		System.out.println(names.contains("Brad"));
		System.out.println(names.contains("Andrew"));

		//names.remove(4); // removes Andrew
		//System.out.println(names);

		//names.clear();
		//System.out.println(names);
	}

}
