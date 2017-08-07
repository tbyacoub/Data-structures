package sorting;

import java.util.Arrays;

public class Sort {

	public static <E extends Comparable<? super E>> void selectionSort(E[] arr) {
		for (int i = 0; i < arr.length; i++) {
			E min = arr[i];
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j].compareTo(min) < 0) {
					min = arr[j];
					minIndex = j;
				}
			}
			swap(arr, i, minIndex);
		}
	}

	public static <E extends Comparable<? super E>> void insertionSort(E[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int compare = i;
			for (int j = compare - 1; j >= 0; j--) {
				if (arr[compare].compareTo(arr[j]) < 0) {
					swap(arr, compare, j);
					compare--;
				}
			}
		}
	}

	public static <E extends Comparable<? super E>> void mergesort(E[] arr) {
		mergeRec(arr);
	}

	private static <E extends Comparable<? super E>> void mergeRec(E[] arr) {
		if (arr.length == 1)
			return;

		E[] left = Arrays.copyOfRange(arr, 0, (arr.length) / 2);
		E[] right = Arrays.copyOfRange(arr, (arr.length) / 2, arr.length);
		mergeRec(left);
		mergeRec(right);
		merge(left, right, arr);
	}

	private static <E extends Comparable<? super E>> void merge(E[] left, E[] right, E[] arr) {
		int leftIndex = 0;
		int rightIndex = 0;
		int j = 0;
		while (leftIndex < left.length && rightIndex < right.length) {
			if (left[leftIndex].compareTo(right[rightIndex]) < 0) {
				arr[j] = left[leftIndex];
				leftIndex++;
			} else {
				arr[j] = right[rightIndex];
				rightIndex++;
			}
			j++;
		}
		while (leftIndex < left.length) {
			arr[j++] = left[leftIndex++];
		}
		while (rightIndex < right.length) {
			arr[j++] = right[rightIndex++];
		}

	}

	public static <E extends Comparable<? super E>> void quickSort(E[] arr) {
		quickSortRec(arr, 0, arr.length - 1);
	}

	private static <E extends Comparable<? super E>> void quickSortRec(E[] arr, int start, int end) {
		if (start >= end)
			return;
		int iPivot = (end + start) / 2;
		E pivot = arr[iPivot];
		swap(arr, iPivot, start);
		int RS = start + 1;
		int LS = end;
		while (RS <= LS) {
			while (RS <= end && pivot.compareTo(arr[RS]) > 0) {
				RS++;
			}
			while (LS >= start && pivot.compareTo(arr[LS]) < 0) {
				LS--;
			}
			if (RS <= LS) {
				swap(arr, LS, RS);
				RS++;
				LS--;
			}
		}
		swap(arr, start, LS);
		quickSortRec(arr, start, LS - 1);
		quickSortRec(arr, RS, end);
	}

	private static <E> void swap(E[] arr, int i, int minIndex) {
		E temp = arr[i];
		arr[i] = arr[minIndex];
		arr[minIndex] = temp;
	}

}
