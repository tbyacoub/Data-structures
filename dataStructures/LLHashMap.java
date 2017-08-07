package dataStructures;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class LLHashMap<K, V> implements Map<K, V> {

	public class Entry {

		private K Key;
		private V value;
		private boolean deleted;

		public Entry(K key, V value) {
			this.Key = key;
			this.value = value;
		}
	}

	private class Node {

		private Node next;
		private Entry data;

		private Node(Node next, Entry data) {
			this.next = next;
			this.data = data;
		}
	}

	private int numItems;

	private Object[] map;

	private static int DEFAULT = 10;

	private static double reHashFactor = 0.75;

	public LLHashMap() {
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
		Node node = find(key);
		return (node == null) ? false:true;
	}
	
	private Node find(Object key) {
		int hc = key.hashCode() % map.length;
		Node cur = (Node) map[hc];
		while (cur != null) {
			if (key.equals(cur.data.Key)) return cur;
			cur = cur.next;
		}
		return null;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		return null;
	}

	@Override
	public V put(K key, V value) {
		if (containsKey(key)) throw new IllegalStateException();
		int hc = key.hashCode() % map.length;
		if (((double)(numItems+1))/map.length >= reHashFactor) {
			Object[] newMap = new Object[map.length*2];
			reHash(map, newMap);
			map = newMap;
		}
		addToTable(new Entry(key, value), hc, map);
		numItems++;
		return null;
	}

	private void reHash(Object[] oldMap, Object[] newMap) {
		for (int i = 0; i < oldMap.length; i++){
			Node cur = (Node) oldMap[i];
			while (cur != null) {
				addToTable(cur.data, cur.data.Key.hashCode() % newMap.length, newMap);
				cur = cur.next;
			}
		}
	}

	private void addToTable(Entry data, int i, Object[] newMap) {
		Node toAdd = new Node((Node) newMap[i], data);
		newMap[i] = toAdd;
	}

	@Override
	public V remove(Object key) {
		if (!containsKey(key)) return null;
		int hc = key.hashCode() % map.length;
		V retVal = null;
		Node cur = (Node) map[hc];
		Node pre = null;
		while (cur != null) {
			if (key.equals(cur.data.Key)) break;
			pre = cur;
			cur = cur.next;
		}
		retVal = cur.data.value;
		if (pre == null) {
			map[hc] = cur.next;
		} else {
			pre.next = cur.next;
		}
		return retVal;
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
