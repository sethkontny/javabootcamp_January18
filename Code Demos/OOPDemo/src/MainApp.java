import java.util.ArrayList;

public class MainApp {

	public static void main(String[] args) {
		Person nick = new Person();
		Person ronald = new Person();
		
		new Person();

		nick.setFullName("Nick Soule");
		ronald.setFullName("Ronald Kim");
		System.out.println(nick.getFullName());
		System.out.println(ronald.getFullName());
		// nick.setAge(28);
		ronald.setAge(26);
		nick.setAddress("123 Main St. ");
		ronald.setAddress("1600 Pennsylvania");

		System.out.println(nick.getAge() + " " + nick.getAddress());

		System.out.println(nick);
		System.out.println(ronald);

		Person david = new Person("David Musko", 27);
		System.out.println(david);
		david.setFullName("Dave Musko");
		System.out.println(david);
		
		new Person();
		Person[] personArr = {new Person(), new Person(), david};
		personArr[0] = nick;
		personArr[1] = new Person();
		personArr[1].setFullName("James Drain");
		
		ArrayList<Person> peopleList = new ArrayList<>();
		peopleList.add(nick);
		System.out.println(peopleList);
		String nicksAddress = peopleList.get(0).getAddress();
		System.out.println(nicksAddress);
		
		Person p4 = new Person("Antonella S", 31, "This is a test");
		System.out.println(p4);
		


	}

}
