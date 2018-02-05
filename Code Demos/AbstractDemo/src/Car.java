
public class Car extends Machine {

	@Override
	public void start(String name, int num) {
		System.out.println(name + " " + num + "Starting ignition...");
		
	}

	@Override
	public void doStuff() {
		System.out.println("Someone is driving me!");
		
	}

	@Override
	public void shutDown() {
		System.out.println("Switching off the ignition");
		
	}

}
