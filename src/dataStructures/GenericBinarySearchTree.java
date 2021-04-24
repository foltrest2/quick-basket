package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class GenericBinarySearchTree<K extends Comparable<K>,V> implements GenericBinarySearchTreeInterface<K, V>{

	private Node<K,V> root;

	public GenericBinarySearchTree(){
	}

	public GenericBinarySearchTree(K k, V v) {
		root = new Node<K,V>(k,v);
	}

	public void put(K key, V val) {
		root = put(root, key, val); 
	}

	private Node<K,V> put(Node<K,V> x, K key, V val) {
		if (x == null) return new Node<K,V>(key, val);
		int cmp = key.compareTo(x.getKey());
		if (cmp < 0) 
			x.setLeft(put(x.getLeft(), key, val));
		else if (cmp > 0) 
			x.setRight(put(x.getRight(), key, val));
		else if (cmp == 0)
			x.getValues().add(val);
		return x;
	}
	
	public List<V> search(K key) {	
		if(root != null) {
			Node<K,V> toLookAt = searchRecursive(key,root);
			return toLookAt.getValues();
		}
		else {
			return null;
		}	
	}

	private Node<K,V> searchRecursive(K key, Node<K,V> currentNode){	
		if(key.compareTo(currentNode.getKey()) == 0) {
			return currentNode;
		}
		else if(key.compareTo(currentNode.getKey()) >  0) {
			return searchRecursive(key, currentNode.getRight());
		}
		else {
			return searchRecursive(key, currentNode.getLeft());
		}		
	}

	public boolean treeIsEmpty() {	
		return root == null;
	}
	
    public Node<K,V> min() {
    	if (root == null) {
    		return null;
    	}
    	Node<K,V> min = root;
        while (min.getLeft() != null) {
        	min = min.getLeft();
        }
        return min;
    }

	public Node<K,V> max() {
    	if (root == null) {
    		return null;
    	}
    	Node<K,V> max = root;
        while (max.getRight() != null) {
        	max = max.getRight();
        }
        return max;
    }
    
	/**
	 * Returns sorted list of keys greater than key.  Size of list
	 * will not exceed maxReturned
	 * @param key Key to search for
	 * @param maxReturned Maximum number of results to return
	 * @return List of keys greater than key.  List may not exceed maxReturned
	 */
	public List<V> getGreaterThan(K key, Integer maxReturned) {
		List<V> list = new ArrayList<>();
		getGreaterThan(root, key, list);
		return list.subList(0, Math.min(maxReturned, list.size()));
	}

	private void getGreaterThan(Node<K,V> node, K key, List<V> list) {
		if (isnull(node)) {
			return;
		} else if (node.getKey().compareTo(key) > 0) {
			getGreaterThan(node.getLeft(), key, list);
			list.addAll(node.getValues());
			getGreaterThan(node.getRight(), key, list);
		} else {
			getGreaterThan(node.getRight(), key, list);
		}
	}
	
	public List<V> getLowestThan(K key, Integer maxReturned) {
		List<V> list = new ArrayList<>();
		getLowestThan(root, key, list);
		return list.subList(0, Math.min(maxReturned, list.size()));
	}

	private void getLowestThan(Node<K,V> node, K key, List<V> list) {
		if (isnull(node)) {
			return;
		} else if (node.getKey().compareTo(key) < 0) {
			getLowestThan(node.getRight(), key, list);
			list.addAll(node.getValues());
			getLowestThan(node.getLeft(), key, list);
		} else {
			getLowestThan(node.getLeft(), key, list);
		}
	}
	
	private boolean isnull(Node<K,V> node){
		return node == null;		
	}
	
    public String preOrder() {
        return preOrderRecursive(this.root).trim();
    }
    
    private String preOrderRecursive(Node<K, V> root) {
    	if (root == null) {
    		return "";
    	}
    	String s = "";
    	s += " " + root.getValues().toString();
    	s += preOrderRecursive(root.getLeft());
    	s += preOrderRecursive(root.getRight());
    	return s;	
    }
    
    public void reset() {
    	root = null;
    }
}