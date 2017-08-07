package jtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import dataStructures.ResizingArrayList;

public class ResizingArrayListTest {

	private ResizingArrayList<Integer> arr;

	@Before
	public void init() {
		arr = new ResizingArrayList<>();
	}

	@Test
	public void testSize() {
		assertEquals(arr.size(), 0);
		assertEquals(arr.isEmpty(), true);
	}

	@Test
	public void testAddAndClear() {
		arr.add(40);
		assertEquals(arr.size(), 1);
		assertEquals(arr.isEmpty(), false);
		arr.clear();
		assertEquals(arr.size(), 0);
		assertEquals(arr.isEmpty(), true);
	}

	@Test
	public void testGet() {
		try {
			arr.get(0);
			fail("Did not catch an IndexOutOfBoundsException!");
		} catch (IndexOutOfBoundsException iob) {
		}

		arr.add(20);
		Integer expected = 20;
		try {
			assertEquals(expected, arr.get(0));
		} catch (IndexOutOfBoundsException iob) {
			fail("Integer 20 should have been returned, IndexOutOfBoundsException was thrown instead!");
		}

		arr.clear();
		try {
			arr.get(0);
			fail("Did not catch an IndexOutOfBoundsException!");
		} catch (IndexOutOfBoundsException iob) {
		}
	}
	
	@Test
	public void testContains(){
		assertEquals(false, arr.contains(20));
		arr.add(20);
		arr.add(50);
		arr.add(100);
		assertEquals(3, arr.size());
		assertEquals(false, arr.isEmpty());
		assertEquals(true, arr.contains(50));
		assertEquals(false, arr.contains(5));
	}
	
	@Test
	public void testIterator(){
		Integer a = 20;
		Integer b = 50;
		Integer c = 100;
		arr.add(20);
		arr.add(50);
		arr.add(100);
		Iterator<Integer> iter = arr.iterator();
		int count = 0;
		while (iter.hasNext()){
			if (count == 0) {
				assertEquals(a, iter.next());
			} else if (count == 1) {
				assertEquals(b, iter.next());
			} else {
				assertEquals(c, iter.next());
			}
			count++;
		}
		assertEquals(arr.size(), count);
	}

}
