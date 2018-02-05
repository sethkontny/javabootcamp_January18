import java.util.ArrayList;

public class AnimalApp {

	public static void main(String[] args) {
		// Animal ab = new Animal(); -- we can not do this
		Animal a = new Bear(); // a child can be assigned to the type of interface
		Bear b = new Bear();
		b.setMonthsInHibernation(8); // can only access methods of the child class when the type is set to the child
										// class
		
		b.sleep();
		b.eat();
		b.age(72);
		
		Tiger t = new Tiger();
		t.walkMe();
		
		ArrayList<Animal> animalList = new ArrayList<>();
		animalList.add(new Tiger());
		animalList.add(new Bear());
		animalList.add(new Cat());
		animalList.add(new Dog());
		Tiger t2 = (Tiger) animalList.get(0);
		t2.walkMe();

	}

}
