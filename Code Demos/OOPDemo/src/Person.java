
public class Person {

	// instance variables
	private String fullName;
	private int age;
	private String address;

	// this is a constructor
	// if you don't create a blank constructor java will do it for you
	// unless you add an overloaded constructor, then you will need to create a
	// default
	public Person() { 
		fullName = "test";
		age = 13;
		address = "123 Woodward";
	}
	
	// overloaded constructor - allows us to set values on object creation
	public Person(String fullName, int age) {
		this.fullName = fullName;
		this.age = age;
		
	}
	
	// overloaded constructor - allows us to set values on object creation
	// if you don't use the keyword this, you will need to use a different variable name as the parameter
	public Person(String fullName, int age, String add) {
		this.fullName = fullName;
		this.age = age;
		address = add;
		
	}
	public String getFullName() {
		return fullName;
	}

	// if you don't want to use the keyword this, make sure the parameter name is different
	public void setFullName(String name) {
		fullName = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [fullName=" + fullName + ", age=" + age + ", address=" + address + "]";
	}
	
	


}
