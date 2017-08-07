package dataStructures;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K, V> {

	private Object[] table;

	private int numItems;

	private static double rehashLoad = 0.75;

	private final static int DEFAULT = 10;

	public class Entry {
		public K key;
		public V value;
		public boolean deleted;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			deleted = false;
		}

	}

	public MyHashMap() {
		this(DEFAULT);
	}

	public MyHashMap(int capacity) {
		this(capacity, rehashLoad);
	}

	public MyHashMap(int capacity, double loadFactor) {
		table = new Object[capacity];
		rehashLoad = loadFactor;
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
		Entry cur = find(key);
		if (cur != null) {
			return true;
		}
		return false;
	}

	private int incrementIndex(int index) {
		return (index + 1) % table.length;
	}

	@Override
	public boolean containsValue(Object value) {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				if (value.equals(entryAtIndex(i).value))
					return true;
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		Entry cur = find(key);
		if (cur != null) {
			return cur.value;
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		if (containsKey(key))
			throw new IllegalStateException("No duplicates allowed!");
		if (((double) (numItems + 1)) / table.length >= rehashLoad) {
			Object[] newTable = new Object[(table.length) * 2];
			rehash(newTable, table);
			table = newTable;
		}
		int hc = key.hashCode() % table.length;
		V retVal = (entryAtIndex(hc) == null) ? null : entryAtIndex(hc).value;
		Entry newEntry = new Entry(key, value);
		addToTable(newEntry, hc, table);
		numItems++;
		return retVal;
	}

	private void rehash(Object[] newTable, Object[] curTable) {
		for (int i = 0; i < table.length; i++) {
			if (curTable[i] != null && !entryAtIndex(i).deleted) {
				int index = entryAtIndex(i).key.hashCode() % newTable.length;
				addToTable(entryAtIndex(i), index, newTable);
			}
		}
	}

	private void addToTable(Entry entry, int index, Object[] table) {
		table[index] = new Entry(entry.key, entry.value);
	}

	@SuppressWarnings("unchecked")
	private Entry entryAtIndex(int index) {
		return (Entry) table[index];
	}

	@Override
	public V remove(Object key) {
		Entry toRemove = find(key);
		if (toRemove != null) {
			toRemove.deleted = true;
			numItems--;
			return toRemove.value;
		}
		return null;
	}

	private Entry find(Object key) {
		int hc = key.hashCode() % table.length;
		Entry cur = entryAtIndex(hc);
		while (cur != null) {
			if (cur.key.equals(key))
				return cur;
			cur = entryAtIndex(incrementIndex(hc));
		}
		return cur;
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
		Set<K> retSet = new HashSet<>();
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				retSet.add(entryAtIndex(i).key);
			}
		}
		return retSet;
	}

	@Override
	public Collection<V> values() {
		Collection<V> retColl = new LinkedList<>();
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				retColl.add(entryAtIndex(i).value);
			}
		}
		return retColl;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
