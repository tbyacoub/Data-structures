package adt;

import java.util.Scanner;

import dataStructures.ArrayStack;
import exceptions.PostFixException;
import interfaces.BoundedStackInterface;

public class PostFixEvaluator {

	private static final String OPERATIONS = "/*+-";

	@SuppressWarnings("resource")
	public static int evaluate(String expression) {
		BoundedStackInterface<Integer> stack = new ArrayStack<>(50);
		Scanner scan = new Scanner(expression);
		int operation;
		int operand1;
		int operand2;
		int result = 0;
		while (scan.hasNext()) {
			if (scan.hasNextInt()) {
				if (stack.isFull())
					throw new PostFixException("Too many operands - stack overflow!");
				stack.push(scan.nextInt());
			} else {
				operation = OPERATIONS.indexOf(scan.next());
				if (operation == -1)
					throw new PostFixException("Syntax error - Illegal symbol!");
				if (stack.isEmpty())
					throw new PostFixException("Not enough operands - stack underflow!");
				operand1 = stack.top();
				stack.pop();
				if (stack.isEmpty())
					throw new PostFixException("Not enough operands - stack underflow!");
				operand2 = stack.top();
				stack.pop();
				switch (operation) {
				case 0:
					result = operand1 / operand2;
					break;
				case 1:
					result = operand1 * operand2;
					break;
				case 2:
					result = operand1 + operand2;
					break;
				case 3:
					result = operand1 - operand2;
					break;
				}
				stack.push(result);
			}
		}
		if (stack.isEmpty())
			throw new PostFixException("Not enough operands - stack underflow!");
		result = stack.top();
		stack.pop();
		if (!stack.isEmpty())
			throw new PostFixException("Too many operands - stack overflow!");
		scan.close();
		return result;
	}

}
