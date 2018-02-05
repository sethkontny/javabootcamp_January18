
public class Camera extends Machine {

	@Override
	public void start(String name, int num) {
		System.out.println(name + " " + num + " Starting camera");
		
	}

	@Override
	public void doStuff() {
		System.out.println("Taking a photo");
		
	}

	@Override
	public void shutDown() {
		System.out.println("Turning camera off");
		
	}

}
