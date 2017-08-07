package application;

import java.util.Scanner;

import dataStructures.ArrayStack;
import interfaces.BoundedStackInterface;

/**
 * Sample use of stack. Outputs strings in reverse order of entry.
 * 
 * @author tbyacoub
 *
 */
public class ReverseStrings {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		BoundedStackInterface<String> stack = new ArrayStack<String>(3);
		String line;
		for (int i = 1; i <= 3; i++) {
			System.out.println("Enter a line of text > ");
			line = scan.nextLine();
			stack.push(line);
		}
		scan.close();

		System.out.println("\nReverse is:\n");
		while (!stack.isEmpty()) {
			line = stack.top();
			stack.pop();
			System.out.println(line);
		}
	}

}
