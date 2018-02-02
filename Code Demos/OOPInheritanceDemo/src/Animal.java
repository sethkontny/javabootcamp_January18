
public class Animal {

	private String name;
	private String gender;
	private int age;

	// no argument constructor to be able to use getters and setters for value
	// assignment
	public Animal() {

	}

	public Animal(String name, String gender, int age) {

		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public int calcAge() {
		return age * 10;
	}

	public String toString() {
		return String.format("Name: %-10s Age: %-10s Gender: %-10s", name, age, gender);
	}

}
