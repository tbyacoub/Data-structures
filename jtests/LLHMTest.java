package jtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dataStructures.LLHashMap;

public class LLHMTest {

	private LLHashMap<Integer, String> map;
	
	@Before
	public void init(){
		map = new LLHashMap<>();
	}
	
	@Test
	public void test1(){
		assertEquals(0, map.size());
		map.put(10, "ten");
		assertTrue(map.containsKey(10));
		assertTrue(!map.containsKey(4));
		map.remove(10);
		assertTrue(!map.containsKey(10));
	}
}
