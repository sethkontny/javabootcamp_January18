import java.util.Stack;

public class StackDemo {

	public static void main(String[] args) {
		
		// Stacks are LIFO (last in first out) -- think pancakes or cleaning dishes
		Stack<String> st = new Stack<String>();
		// this is adding things to the stack
		st.push("bottom");
		printStack(st);
		st.push("second");
		printStack(st);
		st.push("third");
		printStack(st);
		
		// remove some items
		String popped = st.pop(); // assigned the removed string to a variable to reference what was removed later
		System.out.println(popped);
		st.pop(); // this still removes an item, but we don't have a way to recall what it was
		printStack(st);

	}

	private static void printStack(Stack<String> st) {
		if (st.empty()) {
			System.out.println("The stack is empty");
		} else {
			System.out.println(st + " TOP");
		}
	}
}
