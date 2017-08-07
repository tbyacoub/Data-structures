package interfaces;

public interface UnBoundedStackInterface<T> extends StackInterface<T> {

	/**
	 * Places element at the top of this stack
	 * 
	 * @param element
	 *            to be added to this stack.
	 */
	void push(T element);

}
