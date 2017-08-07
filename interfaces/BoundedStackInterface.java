package interfaces;

import exceptions.StackOverFlowException;

public interface BoundedStackInterface<T> extends StackInterface<T> {

	/**
	 * Adds element to the top of this stack.
	 * 
	 * @param element
	 *            Element to be added to this stack.
	 * @throws StackOverFlowException
	 *             if this stack is full.
	 */
	void push(T element) throws StackOverFlowException;

	/**
	 * Checks if this stack is full.
	 * @return true if this stack is full, otherwise returns false.
	 */
	boolean isFull();

}
