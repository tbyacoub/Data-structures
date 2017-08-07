package dataStructures;

import interfaces.LogInterface;

public class Log<T> implements LogInterface<T> {

	private Node head;

	private int size;

	private String logName;

	private class Node {
		private Node next;
		private T info;
	}

	public Log(String logName) {
		head = null;
		size = 0;
		this.logName = logName;
	}

	@Override
	public void insert(T element) {
		Node toAdd = new Node();
		toAdd.info = element;
		toAdd.next = head;
		head = toAdd;
		size++;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T element) {
		Node cur = head;
		while (cur.next != null) {
			if (cur.info.equals(element)) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}

	@Override
	public void clear() {
		head = null;
	}

	@Override
	public String getName() {
		return logName;
	}

	@Override
	public String toString() {
		String retVal = "Log: " + logName + "\nLog size: " + size + "\nLog contant:";
		Node cur = head;
		while (cur != null) {
			retVal += "\n\t*" + cur.info;
			cur = cur.next;
		}
		return retVal;
	}

}
