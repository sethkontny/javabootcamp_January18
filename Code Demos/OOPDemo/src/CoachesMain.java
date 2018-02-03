
public class CoachesMain {

	public static void main(String[] args) {
		Person emily = new Person("Emily Blanford", 25);
		Person mercy = new Person();
		mercy.setFullName("Mercy Harmon");
		mercy.setAge(25);
		mercy.setAddress("333 Main St.");
		
		BasketballCoaches coaches = new BasketballCoaches();
		coaches.setAssistantCoach(emily);
		coaches.setHeadCoach(mercy);
		
		coaches.printCoaches();
		coaches.getAssistantCoach().setAddress("12345 Testing");

	}

}
