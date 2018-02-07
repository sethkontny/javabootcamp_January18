
public class Employee {
	private String name;
	private String age;
	private String favFood;

	public Employee(String name, String age, String favFood) {
		super();
		this.name = name;
		this.age = age;
		this.favFood = favFood;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFavFood() {
		return favFood;
	}

	public void setFavFood(String favFood) {
		this.favFood = favFood;
	}

	@Override
	public String toString() {
		return name + "," + age + "," + favFood;
	}

}
