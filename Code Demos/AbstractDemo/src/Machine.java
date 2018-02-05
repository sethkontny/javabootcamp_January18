
public abstract class Machine {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	// if I create abstract methods in any class the class must be declared as abstract
	// abstract methods never have a body and always end in a semicolon like a statement
	public abstract void start(String name, int num);
	public abstract void doStuff();
	public abstract void shutDown();
	
	public void run(String name, int num) {
		start(name, num);
		doStuff();
		shutDown();
	}

}
