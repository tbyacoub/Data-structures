package dataStructures;

import interfaces.BSTInterface;

public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {

	private Node root;
	private boolean removed;

	private class Node {
		private Node lc;
		private Node rc;
		private T data;

		private Node(Node lc, Node rc, T data) {
			this.lc = lc;
			this.rc = rc;
			this.data = data;
		}
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int size() {
		return recSize(root);
	}

	private int recSize(Node cur) {
		if (cur == null)
			return 1;
		return recSize(cur.lc) + recSize(cur.rc);
	}

	@Override
	public boolean contains(T element) {
		return find(element, root);
	}

	private boolean find(T element, Node cur) {
		if (cur == null) {
			return false;
		} else if (element.compareTo(cur.data) <= 0) {
			return find(element, cur.lc);
		} else {
			return find(element, cur.rc);
		}
	}

	@Override
	public boolean remove(T element) {
		removed = false;
		root = remove(element, root);
		return removed;
	}

	private Node remove(T element, Node cur) {
		if (cur == null){
			cur = null;
		} else if (element.compareTo(cur.data) < 0) {
			cur.lc = remove(element, cur.lc);
		} else if (element.compareTo(cur.data) > 0) {
			cur.rc = remove(element, cur.rc);
		} else {
			cur = removeNode(cur);
			removed = true;
		}
		return cur;
	}

	private Node removeNode(Node cur) {
		if (cur.lc == null) {
			return cur.rc;
		} else if (cur.rc == null) {
			return cur.lc;
		} else {
			T data = getPredData(cur.lc);
			cur.data = data;
			cur.lc = remove(data, cur.lc);
		}
		return cur;
	}

	private T getPredData(Node lc) {
		Node cur = lc;		
		while (cur.rc != null) {
			cur = cur.rc;
		}
		return cur.data;
	}

	@Override
	public T get(T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(T element) {
		root = addRec(element, root);
	}

	private Node addRec(T element, Node cur) {
		if (cur == null) {
			cur = new Node(null, null, element);
		} else if (element.compareTo(cur.data) <= 0) {
			cur.lc = addRec(element, cur.lc);
		} else {
			cur.rc = addRec(element, cur.rc);
		}
		return cur;
	}

	@Override
	public int reset(int orderType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T getNext(int orderType) {
		// TODO Auto-generated method stub
		return null;
	}

}
