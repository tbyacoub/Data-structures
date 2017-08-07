package jtests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dataStructures.MyHeap;

public class MyHeapTest {
	
	private MyHeap<Integer> heap;
	
	@Before
	public void init(){
		heap = new MyHeap<>();
	}
	
	@Test
	public void initialTest(){
		assertEquals(0, heap.size());
		heap.add(50);
		assertEquals(1, heap.size());
		heap.add(30);
		heap.add(70);
		heap.add(40);
		heap.add(60);
		heap.add(100);
		heap.add(50);
		assertEquals("[30, 40, 50, 50, 60, 100, 70]", heap.toString());
		assertEquals(30, (int) heap.remove());
		assertEquals("[40, 50, 50, 60, 70, 100]", heap.toString());
	}
	
	@Test
	public void sortTest(){
		heap.add(30);
		heap.add(70);
		heap.add(40);
		heap.add(60);
		heap.add(100);
		heap.add(50);
		heap.add(50);
		assertEquals("[30, 40, 50, 50, 60, 70, 100]", heap.sort().toString());
	}

}
