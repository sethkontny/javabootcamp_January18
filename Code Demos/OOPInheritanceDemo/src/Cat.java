
public class Cat extends Animal {
	private String hairType;

	public Cat() {

	}

	@Override
	public int calcAge() {
		// TODO Auto-generated method stub
		return super.getAge();
	}

	public Cat(String name, String gender, int age, String hairType) {
		super(name, gender, age);
		this.hairType = hairType;
	}

	public String getHairType() {
		return hairType;
	}

	public void setHairType(String hairType) {
		this.hairType = hairType;
	}

	public String toString() {
		return String.format("Name: %-10s Age: %-10s Gender: %-10s HairType: %-10s", super.getName(), super.getAge(),
				super.getGender(), hairType);
	}

}
