package jtests;

import org.junit.Before;
import org.junit.Test;

import dataStructures.BinarySearchTree;

public class BSTTest {

	private BinarySearchTree<Integer> bst;

	@Before
	public void init() {
		bst = new BinarySearchTree<>();
	}

	@Test
	public void initialTest() {
		System.out.println(11%10);		
	}

}
