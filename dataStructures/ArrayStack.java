package dataStructures;

import exceptions.StackOverFlowException;
import exceptions.StackUnderFlowException;
import interfaces.BoundedStackInterface;

public class ArrayStack<T> implements BoundedStackInterface<T> {

	private final int DEFCAP = 100;

	private Object[] stack;

	private int topIndex = -1;

	public ArrayStack() {
		stack = new Object[DEFCAP];
	}

	public ArrayStack(int capacity) {
		stack = new Object[capacity];
	}

	@Override
	public void pop() throws StackUnderFlowException {
		if (isEmpty())
			throw new StackUnderFlowException("Cannot pop from an empty stack!");
		topIndex--;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T top() throws StackUnderFlowException {
		if (isEmpty())
			throw new StackUnderFlowException("Cannot call top on an empty stack!");
		return (T) stack[topIndex];
	}

	@Override
	public boolean isEmpty() {
		return topIndex == -1;
	}

	@Override
	public void push(T element) throws StackOverFlowException {
		if (isFull())
			throw new StackOverFlowException("Cannot push on a full stack!");
		stack[++topIndex] = element;
	}

	@Override
	public boolean isFull() {
		return topIndex == (stack.length - 1);
	}

}
