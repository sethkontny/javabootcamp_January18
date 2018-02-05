import java.util.ArrayList;

public class MachineApp {

	public static void main(String[] args) {
		// Machine m1 = new Machine(); -- this is not possible
		Machine m1 = new Car(); // I can set child objects to the type of the abstract class
		Machine m2 = new Camera();

		Camera c1 = new Camera();
		c1.setId(780);
		c1.run("Kodak", 56);

		Car c2 = new Car();
		c2.setId(7789);
		c2.run("Jeep", 12);

		Chevrolet c3 = new Chevrolet();
		c3.run("Chevrolet", 5579);

		ArrayList<Machine> machineList = new ArrayList<>();
		machineList.add(new Camera());
		machineList.add(c1);

		Camera fromList = (Camera) machineList.get(0);

	}

}
