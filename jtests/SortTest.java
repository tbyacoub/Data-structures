package jtests;

import org.junit.Assert;
import org.junit.Test;

import sorting.Sort;
// test
public class SortTest {

	private Integer[] sorted = { 0, 2, 5, 7, 7, 9, 9, 10, 12, 15, 35, 39, 48, 84 };

	@Test
	public void selectionTest() {
		Integer[] arr = { 15, 84, 39, 12, 0, 7, 9, 5, 2, 7, 9, 10, 48, 35 };
		Sort.selectionSort(arr);
		Assert.assertArrayEquals(sorted, arr);
	}

	@Test
	public void insertionSortTest() {
		Integer[] arr = { 15, 84, 39, 12, 0, 7, 9, 5, 2, 7, 9, 10, 48, 35 };
		Sort.insertionSort(arr);
		Assert.assertArrayEquals(sorted, arr);
	}

	@Test
	public void mergeSortTest() {
		Integer[] arr = { 15, 84, 39, 12, 0, 7, 9, 5, 2, 7, 9, 10, 48, 35 };
		Sort.mergesort(arr);
		Assert.assertArrayEquals(sorted, arr);
	}
	
	@Test
	public void quickSortTest(){
		Integer[] arr = { 15, 84, 39, 12, 0, 7, 9, 5, 2, 7, 9, 10, 48, 35 };
		Sort.quickSort(arr);
		Assert.assertArrayEquals(sorted, arr);
	}

}
