
// interfaces can inherit behavior from other interfaces by using the extends keyword
// you can extends multiple interfaces by seperating them with a comma ex. extends PetFriendly, Mammal
// interfaces can not inherit from classes
public interface Animal /* extends PetFriendly */ {

	// all methods in an interface must be abstract
	// therefore we don't need to use the keywords public and abstract

	public abstract void sleep(); // we don't need to use the keywords public and abstract but it will still work
	void eat();
	int age(int ageInHuman);

}
