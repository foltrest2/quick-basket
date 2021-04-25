package dataStructures;

import java.util.List;

public interface GenericAVLTreeInterface<K,V> {
	
	public void insert(K k, V v);
	public List<V> search(K k);
	public List<V> getGreaterThan(K key, Integer maxReturned);
	public List<V> getLowestThan(K key, Integer maxReturned);
	
}
