
public class BasketballCoaches {
	private Person headCoach;
	private Person assistantCoach;
	
	// because I don't have a constructor declared java will create a default
	// constructor for me

	public Person getHeadCoach() {
		return headCoach;
	}

	public void setHeadCoach(Person headCoach) {
		this.headCoach = headCoach;
	}

	public Person getAssistantCoach() {
		return assistantCoach;
	}

	public void setAssistantCoach(Person assistantCoach) {
		this.assistantCoach = assistantCoach;
	}
	
	public void printCoaches() {
		System.out.println("Head Coach: " + headCoach.toString());
		System.out.println("Assistant Coach: " + assistantCoach.toString());
	}

}
