package dataStructures;

public interface GenericBinarySearchTreeInterface<K extends Comparable<K>,V> {

	public void insertion(K k, V v);
	public V search(K k);
	public V delete(K k);
	public V max();
	public V min();
	public V successor(K k);
	public V predecessor(K k);
	
}
