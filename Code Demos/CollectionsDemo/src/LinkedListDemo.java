import java.util.LinkedList;

public class LinkedListDemo {

	public static void main(String[] args) {
		LinkedList<String> list1 = new LinkedList<String>();
		
		list1.add("Jordan");
		list1.add("Vince");
		list1.add("Cara");
		
		System.out.println(list1);
		
		for (int i = 0; i < list1.size(); i++) {
			System.out.println(list1.get(i));
			
		}
		
		list1.removeFirst();
		list1.removeLast();
		System.out.println(list1);
		
		

	}

}
