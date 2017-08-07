package jtests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dataStructures.SingleLinkedList;

public class SingleLinkedListTest {

	SingleLinkedList<String> ll;

	@Before
	public void init() {
		ll = new SingleLinkedList<>();
	}

	@Test
	public void test(){
		String i1 = "hello";
		String i2 = "bye";
		String i3 = "salam";
		String i4 = "nehaw";
		String i5 = "shalom";
		String i6 = "breviat";
		Assert.assertEquals(0, ll.size());
		Assert.assertTrue(ll.isEmpty());
		ll.add(i1);
		Assert.assertEquals(1, ll.size());
		Assert.assertTrue(!ll.isEmpty());
		Assert.assertTrue(ll.contains(i1));
		Assert.assertTrue(!ll.contains(i2));
		ll.remove(i1);
		Assert.assertEquals(0, ll.size());
		Assert.assertTrue(ll.isEmpty());
		Assert.assertTrue(!ll.contains(i1));
		ll.add(i1);
		ll.add(i2);
		ll.add(i3);
		ll.add(i4);
		Assert.assertTrue(ll.contains(i1));
		Assert.assertTrue(ll.contains(i2));
		Assert.assertTrue(ll.contains(i3));
		Assert.assertTrue(ll.contains(i4));
		Assert.assertEquals(4, ll.size());
		Assert.assertTrue(!ll.isEmpty());
		Assert.assertEquals(i3, ll.get(1));
		ll.set(0, i5);
		Assert.assertEquals(i5, ll.get(0));
		ll.add(1, i6);
		Assert.assertEquals(i6, ll.get(1));
		Assert.assertTrue(ll.contains(i1));
		Assert.assertTrue(ll.contains(i2));
		Assert.assertTrue(ll.contains(i6));
		Assert.assertTrue(ll.contains(i5));
		Assert.assertEquals(5, ll.size());
		ll.remove(0);
		Assert.assertTrue(!ll.contains(i5));
		Assert.assertEquals(4, ll.size());
		System.out.println(ll);
		Assert.assertEquals(3, ll.indexOf(i1));
	}

}
