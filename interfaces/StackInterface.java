package interfaces;

import exceptions.StackUnderFlowException;

public interface StackInterface<T> {

	/**
	 * Throws StackUnderFlowException if stack is empty, otherwise removes top
	 * element from this stack.
	 * 
	 * @throws StackUnderFlowException
	 */
	void pop() throws StackUnderFlowException;

	/**
	 * Throws StackUnderFlowException if stack is empty, otherwise returns top
	 * element from this stack.
	 * 
	 * @throws StackUnderFlowException
	 */
	T top() throws StackUnderFlowException;

	/**
	 * Returns true if this stack is empty, otherwise returns false.
	 * 
	 * @return true if this stack is empty.
	 */
	boolean isEmpty();

}
