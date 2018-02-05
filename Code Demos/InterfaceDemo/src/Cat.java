
public class Cat implements Animal, PetFriendly {

	@Override
	public void sleep() {
		System.out.println("I am a cat, and I like to sleep all day");

	}

	@Override
	public void eat() {
		System.out.println("I like cat food");

	}

	@Override
	public int age(int ageInHuman) {
		// TODO Auto-generated method stub
		return ageInHuman * 9;
	}

	@Override
	public void petMe() {
		System.out.println("Please pet me, or I'll harass you");
		
	}

	@Override
	public void fetch() {
		System.out.println("I don't fetch well");
		
	}

	@Override
	public void walkMe() {
		System.out.println("No thanks, I'll walk myself");
		
	}

}
