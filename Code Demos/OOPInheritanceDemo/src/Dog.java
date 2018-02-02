
public class Dog extends Animal {

	private String breed;

	public Dog() {

	}

	public Dog(String name, String gender, int age, String breed) {
		super(name, gender, age);
		this.breed = breed;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public String toString() {
		return String.format("Name: %-10s Age: %-10s Gender: %-10s Breed: %-10s", super.getName(), super.getAge(),
				super.getGender(), breed);
	}

}
