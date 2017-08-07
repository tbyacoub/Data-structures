package dataStructures;

import java.util.LinkedList;
import java.util.Queue;

import interfaces.BSTInterface;

public class BinarySearchTree<T extends Comparable<? super T>> implements BSTInterface<T> {

	private Node root;

	private FoundInfo found;

	private Queue<T> inOrderQueue;
	private Queue<T> preOrderQueue;
	private Queue<T> postOrderQueue;

	public BinarySearchTree() {
		root = null;
	}

	private class Node {
		private T info;
		private Node left;
		private Node right;

		private Node(T info) {
			this.info = info;
			left = null;
			right = null;
		}
	}

	@Override
	public boolean isEmpty() {
		return (root == null);
	}

	@Override
	public int size() {
		/**
		 * int count = 0; if (!isEmpty()) { Stack<Node> s = new Stack<>();
		 * s.push(root); while (!s.isEmpty()) { Node cur = s.pop(); count++; if
		 * (cur.left != null) { s.push(cur.left); } if (cur.right != null) {
		 * s.push(cur.right); } } } return count;
		 * 
		 */
		return size(root);
	}

	private int size(Node cur) {
		if (cur == null) {
			return 0;
		}
		return size(cur.left) + size(cur.right);
	}

	private void find(T element, Node cur) {
		if (cur == null) {
			found.isFound = false;
			found.node = null;
		} else if (element.compareTo(cur.info) < 0) {
			find(element, cur.left);
		} else if (element.compareTo(cur.info) > 0) {
			find(element, cur.right);
		} else {
			found.isFound = true;
			found.node = cur;
		}
	}

	private class FoundInfo {
		private Node node;
		private boolean isFound;
	}

	@Override
	public boolean contains(T element) {
		find(element, root);
		return found.isFound;
	}

	@Override
	public boolean remove(T element) {
		remove(element, root);
		return false;
	}

	private Node remove(T element, Node cur) {
		if (cur == null) {
			found.isFound = false;
			found.node = null;
		} else if (element.compareTo(cur.info) < 0) {
			cur.left = remove(element, cur.left);
		} else if (element.compareTo(cur.info) > 0) {
			cur.right = remove(element, cur.right);
		} else {
			cur = removeNode(cur);
			found.isFound = true;
			found.node = cur;
		}
		return cur;
	}

	private Node removeNode(Node cur) {
		T data;
		if (cur.left == null) {
			return cur.right;
		} else if (cur.right == null) {
			return cur.left;
		} else {
			data = getPredecessor(cur.left);
			cur.info = data;
			cur.left = remove(data, cur.left);
			return cur;
		}
	}

	private T getPredecessor(Node left) {
		while (left.right != null) {
			left = left.right;
		}
		return left.info;
	}

	@Override
	public T get(T element) {
		find(element, root);
		return found.node.info;
	}

	@Override
	public void add(T element) {
		root = add(element, root);
	}

	public Node add(T element, Node cur) {
		if (cur == null) {
			cur = new Node(element);
		} else if (element.compareTo(cur.info) <= 0) {
			cur.left = add(element, cur.left);
		} else {
			cur.right = add(element, cur.right);
		}
		return cur;
	}

	@Override
	public int reset(int orderType) {
		int numNodes = size();
		if (orderType == INORDER) {
			inOrderQueue = new LinkedList<>();
			inOrder(root);
		} else if (orderType == PREORDER) {
			preOrderQueue = new LinkedList<>();
			preOrder(root);
		} else if (orderType == POSTORDER) {
			postOrderQueue = new LinkedList<>();
			postOrder(root);
		}
		return numNodes;
	}

	private void postOrder(Node cur) {
		if (cur != null) {
			postOrder(cur.left);
			postOrder(cur.right);
			postOrderQueue.add(cur.info);
		}
	}

	private void preOrder(Node cur) {
		if (cur != null) {
			preOrderQueue.add(cur.info);
			preOrder(cur.left);
			preOrder(cur.right);
		}
	}

	private void inOrder(Node cur) {
		if (cur != null) {
			inOrder(cur.left);
			inOrderQueue.add(cur.info);
			inOrder(cur.right);
		}
	}

	@Override
	public T getNext(int orderType) {
		if (orderType == INORDER) {
			return inOrderQueue.poll();
		} else if (orderType == PREORDER) {
			return preOrderQueue.poll();
		} else if (orderType == POSTORDER) {
			return postOrderQueue.poll();
		} else {
			return null;
		}
	}

}
