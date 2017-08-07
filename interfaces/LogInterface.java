package interfaces;

public interface LogInterface<T> {

	/**
	 * Places element into this StringLog, if this StringLog is not full.
	 * 
	 * @param element
	 *            Element to be inserted.
	 */
	void insert(T element);
	// Preconditions: This StringLog is not full.

	/**
	 * Returns true if this StringLog is full. Otherwise returns false.
	 * 
	 * @return true if StringLog is full. False otherwise.
	 */
	boolean isFull();

	/**
	 * Returns the number of Strings in this StringLog
	 * 
	 * @return Number of Strings.
	 */
	int size();

	/**
	 * Checks if the given element is already in this StringLog ignoring case
	 * differences when doing comparisons.
	 * 
	 * @param element
	 *            Element to check.
	 * @return True if element is in this stringLog, false otherwis.
	 */
	boolean contains(T element);

	/**
	 * Makes this StringLog empty.
	 */
	void clear();

	/**
	 * Get the name of this StringLog
	 * 
	 * @return The name of this StringLog.
	 */
	String getName();

	/**
	 * Formates all strings in this StringLog to a nice format.
	 * 
	 * @return A nicely formatted string representing this StringLog.
	 */
	String toString();
}
