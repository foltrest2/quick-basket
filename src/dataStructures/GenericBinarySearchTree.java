package dataStructures;

import java.util.ArrayList;

public class GenericBinarySearchTree<K extends Comparable<K>,V> {

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
	
	public ArrayList<V> search(K key) {	
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
}