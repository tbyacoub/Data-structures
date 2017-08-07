package adt;

import dataStructures.ArrayStack;
import exceptions.StackUnderFlowException;

public class Balance {

	private String openChar;

	private String closingChar;

	private ArrayStack<Integer> stack;

	public Balance(String openChar, String closingChar) {
		this.openChar = openChar;
		this.closingChar = closingChar;

	}

	public boolean test(String expression) {
		stack = new ArrayStack<>(expression.length());
		int openIndex;
		int closingIndex;
		for (int i = 0; i < expression.length(); i++) {
			char curr = expression.charAt(i);
			openIndex = openChar.indexOf(curr);
			if (openIndex != -1) {
				stack.push(openIndex);
			} else {
				closingIndex = closingChar.indexOf(curr);
				if (closingIndex != -1) {
					try {
						if (stack.top() == closingIndex) {
							stack.pop();
						} else {
							return false;
						}
					} catch (StackUnderFlowException sufe) {
						return false;
					}
				}
			}
		}
		return true;
	}

}
