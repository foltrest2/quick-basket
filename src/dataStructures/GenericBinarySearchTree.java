package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class GenericBinarySearchTree<K extends Comparable<K>,V> implements GenericBinarySearchTreeInterface<K, V>{

	private Node<K,V> root;

	/**
	 * Constructor of empty generic binary search tree
	 */
	public GenericBinarySearchTree(){
	}
	/**
	 * Constructor of generic binary search tree initializing the root with the given information
	 * @param k is the key
	 * @param v is the value
	 */
	public GenericBinarySearchTree(K k, V v) {
		root = new Node<K,V>(k,v);
	}
	/**
	 * This method insert a node into the tree
	 * @param key is the key
	 * @param val is the value
	 */
	public void put(K key, V val) {
		root = put(root, key, val); 
	}
	/**
	 * This method creates the links between the node to insert and the node when it is below
	 * @param x node to insert
	 * @param key key of the node
	 * @param val value of the node
	 * @return
	 */
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
	/**
	 * This method searches a node and returns its values
	 * @param key is the key of the node searched
	 */
	public List<V> search(K key) {	
		if(root != null) {
			Node<K,V> toLookAt = searchRecursive(key,root);
			return toLookAt.getValues();
		}
		else {
			return null;
		}	
	}
	/**
	 * This recursive method looks at all the  nodes till found node searched or not
	 * @param key key of the node searched
	 * @param currentNode actual node
	 * @return
	 */
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
	/**
	 * This method gets the node with the minimum value
	 * @return
	 */
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
    /**
     * This method gets the node with the maximum value
     * @return
     */
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
	/**
	 * This method iterates on the tree to find the information required from the nodes
	 * @param current actual node
	 * @param key key of the node given by condition
	 * @param list list to fill with the required elements
	 */
	private void getGreaterThan(Node<K,V> current, K key, List<V> list) {
		if (isnull(current)) {
			return;
		} else if (current.getKey().compareTo(key) > 0) {
			getGreaterThan(current.getLeft(), key, list);
			list.addAll(current.getValues());
			getGreaterThan(current.getRight(), key, list);
		} else {
			getGreaterThan(current.getRight(), key, list);
		}
	}
	/**
	 * Returns sorted list of keys lower than key.  Size of list
	 * will not exceed maxReturned
	 * @param key Key to search for
	 * @param maxReturned Maximum number of results to return
	 * @return List of keys lower than key.  List may not exceed maxReturned
	 */
	public List<V> getLowestThan(K key, Integer maxReturned) {
		List<V> list = new ArrayList<>();
		getLowestThan(root, key, list);
		return list.subList(0, Math.min(maxReturned, list.size()));
	}
	/**
	 * This method iterates on the tree to find the information required from the nodes
	 * @param current actual node
	 * @param key key of the node given by condition
	 * @param list list to fill with the required elements
	 */
	private void getLowestThan(Node<K,V> current, K key, List<V> list) {
		if (isnull(current)) {
			return;
		} else if (current.getKey().compareTo(key) < 0) {
			getLowestThan(current.getRight(), key, list);
			list.addAll(current.getValues());
			getLowestThan(current.getLeft(), key, list);
		} else {
			getLowestThan(current.getLeft(), key, list);
		}
	}
	/**
	 * This method runs the tree and gets all the information from it
	 */
	public String preOrder() {
		return preOrderRecursive(this.root).trim();
	}
	/**
	 * This method iterates through all the tree getting the information from all nodes
	 * @param root is the first node
	 * @return String with all the information
	 */
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
	private boolean isnull(Node<K,V> node){
		return node == null;		
	}
	public boolean treeIsEmpty() {	
		return root == null;
	}
    public void reset() {
    	root = null;
    }
}