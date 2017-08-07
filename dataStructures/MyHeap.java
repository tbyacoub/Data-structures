package dataStructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class MyHeap<E extends Comparable<? super E>> {

	private ArrayList<E> heap;

	public MyHeap() {
		heap = new ArrayList<>();
	}

	public int size() {
		return heap.size();
	}

	public E top() {
		return heap.get(0);
	}

	public boolean add(E o) {
		heap.add(o);
		perculateUp(heap.size() - 1);
		return true;
	}

	private void perculateUp(int index) {
		for (int i = index; index > 0 && heap.get(i).compareTo(parent(i)) < 0; i--) {
			swap(i, (i - 1) / 2);
		}
	}

	private void swap(int i, int j) {
		E temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	private E parent(int i) {
		return heap.get((i - 1) / 2);
	}

	public E remove() {
		E retVal = top();
		swap(0, heap.size() - 1);
		heap.remove(heap.size() - 1);
		perculateDown(0);
		return retVal;
	}

	private void perculateDown(int index) {
		int incr = 0;
		for (int i = index; i <= heap.size() - 2; i = i + incr) {
			if (i == heap.size() - 2 && heap.get(i).compareTo(leftChild(i)) > 0) {
				swap(i, i + 1);
				incr = 1;
			} else if (i < heap.size() - 2 && !isMin(i)) {
				if (leftChild(i).compareTo(rightChild(i)) > 0) {
					swap(i, i + 2);
					incr = 2;
				} else {
					swap(i, i + 1);
					incr = 1;
				}
			} else {
				break;
			}
		}
	}

	private boolean isMin(int i) {
		if (heap.get(i).compareTo(leftChild(i)) > 0)
			return false;
		if (heap.get(i).compareTo(rightChild(i)) > 0)
			return false;
		return true;
	}

	private E rightChild(int i) {
		return heap.get(i + 2);
	}

	private E leftChild(int i) {
		return heap.get(i + 1);
	}

	public String toString() {
		return heap.toString();
	}
	
	public Collection<E> sort(){
		Collection<E> sorted = new LinkedList<>();
		while (!heap.isEmpty()) {
			sorted.add(remove());
		}
		return sorted;
	}

}
