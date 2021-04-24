package dataStructures;

public interface GenericAVLTreeInterface<K extends Comparable<K>,V> {
	
	public void insert(K k, V v);
	public V search(K k);
	public V max();
	public V min();
	public V successor(K k);
	public V predecessor(K k);

}
