package dataStructures;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import interfaces.Coms228List;

public class ResizingArrayList<E> implements Coms228List<E> {

	private Object[] theArray;
	private int numItems;
	public static final int DEFAULT_SIZE = 10;

	public ResizingArrayList() {
		theArray = new Object[DEFAULT_SIZE];
		numItems = 0;
	}

	public ResizingArrayList(Collection<? extends E> c) {
		this();
		Iterator<? extends E> iter = c.iterator();
		growArray(c.size());
		while (iter.hasNext()) {
			add(iter.next());
		}
	}

	private void growArray(int size) {
		int newSize = theArray.length;
		while (size > newSize) {
			newSize *= 2;
		}
		if (newSize > theArray.length) {
			theArray = Arrays.copyOf(theArray, newSize);
		}

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
		numItems = 0;
	}

	@Override
	public boolean add(E o) {
		growArray(numItems + 1);
		theArray[numItems] = o;
		numItems++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		int i = indexOf(o);
		if (i == -1)
			return false;
		for (int j = i + 1; j < numItems; j++) {
			theArray[j - 1] = theArray[j];
		}
		numItems--;
		shrinkArray();
		return true;
	}

	private void shrinkArray() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < numItems; i++) {
			if (theArray[i].equals(o))
				return true;
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<E> {

		private int currIndex = 0;

		@Override
		public boolean hasNext() {
			return currIndex < numItems;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return (E) theArray[currIndex++];
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int pos) {
		if (pos >= numItems)
			throw new IndexOutOfBoundsException();
		return (E) theArray[pos];
	}

	@SuppressWarnings("unchecked")
	@Override
	public E set(int pos, E o) {
		if (pos >= numItems)
			throw new IndexOutOfBoundsException();
		if (o == null)
			throw new NullPointerException();
		E replacedItem = (E) theArray[pos];
		theArray[pos] = o;
		return replacedItem;
	}

	@Override
	public void add(int pos, E o) {
		if (pos >= numItems)
			throw new IndexOutOfBoundsException();
		if (o == null)
			throw new NullPointerException();
		growArray(numItems + 1);
		for (int j = numItems; j > pos; j--) {
			theArray[j] = theArray[j - 1];
		}
		theArray[pos] = o;
		numItems++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int pos) {
		if (pos >= numItems)
			throw new IndexOutOfBoundsException();
		E removedItem = (E) theArray[pos];
		for (int j = pos + 1; j < numItems; j++) {
			theArray[j - 1] = theArray[j];
		}
		numItems--;
		return removedItem;
	}

	@Override
	public int indexOf(Object o) {
		int i = 0;
		while (i < numItems) {
			if (o == null && theArray[i] == null)
				break;
			if (theArray[i] != null && theArray[i].equals(o))
				break;
			i++;
		}
		if (numItems == i)
			return -1;
		return i;
	}

	@Override
	public List<E> subList(int from, int to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator() {
		return new ResizingArrayListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int startPos) {
		return new ResizingArrayListIterator(startPos);
	}

	private class ResizingArrayListIterator implements ListIterator<E> {

		private int currIndex;

		public ResizingArrayListIterator() {
			currIndex = 0;
		}

		public ResizingArrayListIterator(int startPos) {
			currIndex = startPos;
		}

		@Override
		public boolean hasNext() {
			return currIndex < numItems;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return (E) theArray[currIndex++];
		}

		@Override
		public boolean hasPrevious() {
			return currIndex > 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E previous() {
			if (!hasPrevious())
				throw new NoSuchElementException();
			return (E) theArray[--currIndex];
		}

		@Override
		public int nextIndex() {
			if (!hasNext())
				return numItems;
			return currIndex + 1;
		}

		@Override
		public int previousIndex() {
			if (!hasNext())
				return -1;
			return currIndex + 1;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(E e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
		}

	}

}
