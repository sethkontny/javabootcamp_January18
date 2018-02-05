
public class Bear implements Animal {
	private int monthsInHibernation;

	public int getMonthsInHibernation() {
		return monthsInHibernation;
	}

	public void setMonthsInHibernation(int monthsInHibernation) {
		this.monthsInHibernation = monthsInHibernation;
	}

	@Override
	public void sleep() {
		System.out.println("I am a bear, and I am currently hibernating");

	}

	@Override
	public void eat() {
		System.out.println("I will eat you if you get in my way!");

	}

	@Override
	public int age(int ageInHuman) {
		
		return ageInHuman;
	}

}
