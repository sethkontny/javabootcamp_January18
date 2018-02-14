package com.gc.lambda;
import java.util.ArrayList;

public class Lamda {
	public static void main(String[] args) {
		
		//Example of a functional interface
		Anono thing = () -> {			
				System.out.println("Hi");			
		};
		
		thing.test();
		
		
		
		//iterating through a collection and filtering using lambdas
		ArrayList<String> items = new ArrayList<String>();
		items.add("Apples");
		items.add("Oranges");
		items.add("Kiwi");
		items.add("Apricot");
		items.add("Rotten Eggs");
		
		items.stream().filter(v-> v.charAt(0) == 'A').forEach(f->System.out.println(f));
		
		System.out.println(items.stream());
		
		//Display the list
		for(String val: items){
			System.out.println(val);
		}
	}
}
