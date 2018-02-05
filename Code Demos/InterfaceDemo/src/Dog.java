
public class Dog implements Animal, PetFriendly {

	@Override
	public void sleep() {
		System.out.println("I am a dog, and I never sleep");
		
	}

	@Override
	public void eat() {
		System.out.println("I eat all the things!");
		
	}

	@Override
	public int age(int ageInHuman) {
		
		return ageInHuman * 7;
	}

	@Override
	public void petMe() {
		System.out.println("I like lots of attention");
		
	}

	@Override
	public void fetch() {
		System.out.println("I play fetch well");
		
	}

	@Override
	public void walkMe() {
		System.out.println("You should walk me everyday");
		
	}

}
