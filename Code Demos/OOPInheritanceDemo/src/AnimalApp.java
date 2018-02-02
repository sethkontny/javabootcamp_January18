import java.util.ArrayList;

public class AnimalApp {

	public static void main(String[] args) {
		Animal a = new Animal();
		a.setName("Generic Animal");
		a.setAge(5);
		System.out.println(a.calcAge());

		Cat c = new Cat();
		c.setName("Felix");
		c.setAge(5);
		System.out.println(c.calcAge());

		Dog d = new Dog();
		d.setName("Fido");
		d.setAge(14);
		d.setGender("male");
		System.out.println(d);

		// you can set any child to its parent type
		// implicit cast -- this works because Cat is a child of Animal
		// if we do this we lose the ability to access the methods of the Cat and Dog
		// class because they are set to the type of Animal and Animal does not know
		// what methods the child classes have in them
		Animal a2 = new Cat(); 
		// a2.setHairType(); -- therefore this is not possible
		a2 = new Dog();
		// a2.setBreed(); -- this is not possible either

		// the disadvantage to doing this is that you lose access to methods that are
		// only in the Dog
		// class
		Animal a3 = new Dog();

		ArrayList<Animal> animalList = new ArrayList<>();
		animalList.add(new Cat("Sandy", "female", 3, "long hair"));
		animalList.add(new Dog("Frank", "male", 13, "Husky"));
		animalList.add(new Animal("no name", "male", 2));

		Cat newCat = (Cat) animalList.get(0); // if you assign an index to a child from the Animal class you need to
												// cast it as the type

		// this for loop works exactly like the for each below
		// if using this type of loop you need to use the get method to access the index
		// location
		for (int i = 0; i < animalList.size(); i++) {

			if (animalList.get(i) instanceof Cat) { // instanceof check to see what type the element in the list belongs
													// to
				System.out.println("This is a cat: " + animalList.get(i));
			} else {
				System.out.println("This is not a cat! " + i);

			}
		}

		for (Animal i : animalList) {

			if (i instanceof Cat) {
				System.out.println("This is a cat: " + i);
			} else {
				System.out.println("This is not a cat!" + i);

			}

		}

		Cat c2 = new Cat();
		// when trying turn a child into it's parent or superclass you need to do an
		// explicit cast
		Animal a4 = (Animal) c2;
		System.out.println(a4);

		System.out.println(d.getClass()); // returns Dog
		System.out.println(a4.getClass()); // returns Cat even though we cast it as an Animal
		System.out.println(a.getClass()); // returns Animal

	}

}
