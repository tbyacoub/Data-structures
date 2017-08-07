package interfaces;

/**
 * Interface for a class that implements a binary search tree (BST).
 * 
 * The trees are unbounded and allow duplicate elements, but do not allow null
 * elements. As a general precondition, null elements are not passed as
 * arguments to any of the methods.
 * 
 * The tree supports iteration through its elements in INORDER, PREORDER, and
 * POSTORDER.
 * 
 * @author tbyacoub
 *
 * @param <T>
 */
public interface BSTInterface<T extends Comparable<? super T>> {
	
	/**
	 * Used to specify traversal order.
	 */
	static final int INORDER = 1;
	static final int PREORDER = 2;
	static final int POSTORDER = 3;
	
	boolean isEmpty();
	
	int size();
	
	boolean contains(T element);
	
	boolean remove(T element);
	
	T get(T element);
	
	void add(T element);
	
	int reset(int orderType);
	
	T getNext(int orderType);

}
