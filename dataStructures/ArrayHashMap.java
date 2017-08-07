package dataStructures;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ArrayHashMap<K, V> implements Map<K, V> {

	public class Entry {

		private K Key;
		private V value;
		private boolean deleted;

		public Entry(K key, V value) {
			this.Key = key;
			this.value = value;
		}
	}

	// private class Node{
	//
	// private Node next;
	// private Entry data;
	//
	// private Node(Node next, Entry data){
	// this.next = next;
	// this.data = data;
	// }
	// }

	private int numItems;

	private Object[] map;

	private static int DEFAULT = 10;

	private static double reHashFactor = 0.75;

	public ArrayHashMap() {
		map = new Object[DEFAULT];
		numItems = 0;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		int hc = key.hashCode() % map.length;
		if (entryAtIndex(hc) != null && entryAtIndex(hc).Key.equals(key))
			return !entryAtIndex(hc).deleted;
		int index = increment(map, hc);
		while (map[index] != null && hc != index) {
			if (entryAtIndex(index).Key.equals(key))
				return !entryAtIndex(index).deleted;
			index = increment(map, index);
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		if (!containsKey(key))
			return null;
		int hc = key.hashCode() % map.length;
		if (entryAtIndex(hc).equals(key))
			return entryAtIndex(hc).value;
		int index = increment(map, hc);
		while (map[index] != null && hc != index) {
			if (entryAtIndex(index).Key.equals(key))
				return entryAtIndex(index).value;
			index = increment(map, index);
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		if (containsKey(key))
			throw new IllegalStateException();
		if ((numItems * 1.0) / map.length > reHashFactor)
			reHash();
		int hc = key.hashCode() % map.length;
		Entry newEntry = new Entry(key, value);
		numItems++;
		return addToTable(map, hc, newEntry);
	}

	@SuppressWarnings("unchecked")
	private void reHash() {
		Object[] newTable = new Object[map.length * 2];
		for (Object o : map) {
			Entry reHashEntry = (Entry) o;
			int hc = reHashEntry.Key.hashCode() % newTable.length;
			addToTable(newTable, hc, reHashEntry);
		}
		map = newTable;
	}

	private V addToTable(Object[] table, int index, ArrayHashMap<K, V>.Entry entry) {
		V retVal = (table[index] == null || entryAtIndex(index).deleted) ? null : entryAtIndex(index).value;
		int start = index;
		while (map[start] != null && !entryAtIndex(start).deleted) {
			start = increment(table, start);
		}
		map[start] = entry;
		return retVal;
	}

	private int increment(Object[] table, int start) {
		return (start + 1) % table.length;
	}

	@SuppressWarnings("unchecked")
	private Entry entryAtIndex(int index) {
		return (Entry) map[index];
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
