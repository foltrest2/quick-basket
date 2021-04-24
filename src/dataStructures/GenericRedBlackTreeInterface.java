package dataStructures;

import java.util.List;

public interface GenericRedBlackTreeInterface<K,V> {

	public void insert(K key, V value);
	public List<V> search(K key);
	public List<V> getGreaterThan(K key, Integer maxReturned);
	public List<V> getLowestThan(K key, Integer maxReturned);
	public int size();
	public String preOrder();
	
}
