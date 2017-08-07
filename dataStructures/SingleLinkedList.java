package dataStructures;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import interfaces.Coms228List;

public class SingleLinkedList<E> implements Coms228List<E> {

	private class Node {
		private Node next;
		private E info;
	}

	private Node head;

	private int numItems;

	public SingleLinkedList() {
		Node dummy = new Node();
		dummy.info = null;
		head = dummy;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	@Override
	public void clear() {
		head.next = null;
	}

	@Override
	public boolean add(E o) {
		Node toAdd = new Node();
		toAdd.info = o;
		toAdd.next = head.next;
		head.next = toAdd;
		numItems++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		Node cur = head;
		Node toRemove = null;
		while (cur.next != null) {
			if (cur.next.info.equals(o)) {
				toRemove = cur.next;
				cur.next = toRemove.next;
				numItems--;
				return true;
			}
			cur = cur.next;
		}
		return false;
	}

	@Override
	public boolean contains(Object o) {
		Node cur = head;
		while (cur.next != null) {
			if (cur.next.info.equals(o)) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new SingleLLIterator();
	}
	
	private class SingleLLIterator implements Iterator<E>{
		
		Node current = head;

		@Override
		public boolean hasNext() {
			return current.next != null;
		}

		@Override
		public E next() {
			current = current.next;
			return current.info;
		}
		
	}

	@Override
	public E get(int pos) {
		if (pos >= numItems)
			throw new IndexOutOfBoundsException();
		int count = 0;
		Node cur = head;
		while (count <= pos) {
			cur = cur.next;
			count++;
		}
		return cur.info;
	}

	@Override
	public E set(int pos, E o) {
		if (pos >= numItems)
			throw new IndexOutOfBoundsException();
		int count = 0;
		Node cur = head;
		while (count < pos) {
			cur = cur.next;
			count++;
		}
		Node replaced = cur.next;
		cur.next.info = o;
		return replaced.info;
	}

	@Override
	public void add(int pos, E o) {
		if (pos > numItems)
			throw new IndexOutOfBoundsException();
		int count = 0;
		Node cur = head;
		Node toAdd = new Node();
		toAdd.info = o;
		while (count < pos) {
			cur = cur.next;
			count++;
		}
		numItems++;
		toAdd.next = cur.next;
		cur.next = toAdd;
	}

	@Override
	public E remove(int pos) {
		if (pos >= numItems)
			throw new IndexOutOfBoundsException();
		Node cur = head;
		int count = 0;
		while (count < pos) {
			cur = cur.next;
			count++;
		}
		Node toRemove = cur.next;
		cur.next = toRemove.next;
		numItems--;
		return toRemove.info;
	}

	@Override
	public int indexOf(Object o) {
		Node cur = head;
		int index = 0;
		while (cur.next != null) {
			if (cur.next.info.equals(o)){
				return index;
			}
			cur = cur.next;
			index++;
		}
		return -1;
	}

	@Override
	public List<E> subList(int from, int to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int startPos) {
		// TODO Auto-generated method stub
		return null;
	}

	private class SingleLLListIterator implements ListIterator<E> {

		public SingleLLListIterator() {
			// TODO Auto-generated constructor stub
		}
		
		public SingleLLListIterator(int startPos) {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public E previous() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void set(E e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void add(E e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public String toString() {
		String retVal = "";
		Node cur = head;
		while (cur.next != null) {
			retVal += cur.next.info + "\n";
			cur = cur.next;
		}
		return retVal;
	}

}
