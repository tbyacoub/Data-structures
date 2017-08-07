package jtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dataStructures.ArrayHashMap;

/**
 * ArrayHashMapTest
 * @author tbyacoub
 *
 */
public class AHMTest {
	
	private ArrayHashMap<String, String> map;
	
	@Before
	public void init(){
		map = new ArrayHashMap<>();
	}
	
	@Test
	public void initTest(){
		assertEquals(0, map.size());
		map.put("1", "one");
		assertEquals(1, map.size());
		assertTrue(map.containsKey("1"));
		assertTrue(!map.containsKey(2));
		assertEquals("one", map.get("1"));
	}

}
