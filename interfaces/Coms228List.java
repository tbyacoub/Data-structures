package interfaces;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public interface Coms228List<E> {

	public int size();
	
	public boolean isEmpty();
	
	public void clear();
	
	public boolean add(E o);
	
	public boolean remove(Object o);
	
	public boolean contains(Object o);
	
	public Iterator<E> iterator();
	
	public E get(int pos);
	
	public E set(int pos, E o);
	
	public void add(int pos, E o);
	
	public E remove(int pos);
	
	public int indexOf(Object o);
	
	public List<E> subList(int from, int to);
	
	public ListIterator<E> listIterator();
	
	public ListIterator<E> listIterator(int startPos);
}
