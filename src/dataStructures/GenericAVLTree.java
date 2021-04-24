package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class GenericAVLTree<K extends Comparable<K>,V> implements GenericAVLTreeInterface<K, V>{

	private Node<K,V> root;
	public GenericAVLTree(){
	}

	public GenericAVLTree(K k, V v) {
		root = new Node<K,V>(k,v);
	}
	public Node<K,V> getRoot(){

		return root;
	}
	private int nodeCount = 0;
	public int height() {
		if (root == null) return 0;
		return root.getHeight();
	}
	public int size() {
		return nodeCount;
	}

	public boolean isEmpty() {
		return size() == 0;
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

	public void insert(K key, V value) {
			root = insert(root, key, value);
	}
	
	private Node<K,V> insert(Node<K,V> node, K key, V value) {
		if (node == null) {
			return new Node<K,V>(key, value);	
		}
		int cmp = key.compareTo(node.getKey());
		if (cmp < 0) {
			node.setLeft(insert(node.getLeft(), key, value));
		}
		else if (cmp > 0) {
			node.setRight(insert(node.getRight(), key, value));
		}else if (cmp == 0){
			node.getValues().add(value);
		}

		update(node);
		return balance(node);
	}
	
	private void update(Node<K,V> node) {
		int leftNodeHeight = (node.getLeft() == null) ? -1 : node.getLeft().getHeight();
		int rightNodeHeight = (node.getRight() == null) ? -1 : node.getRight().getHeight();
		node.setHeight(1 + Math.max(leftNodeHeight, rightNodeHeight));
		node.setBalancef( rightNodeHeight - leftNodeHeight);
	}
	
	private Node<K,V> balance(Node<K,V> node) {
		if (node.getBalancef() == -2) {
			if (node.getLeft().getBalancef() <= 0) {
				return leftLeftCase(node);
			} else {
				return leftRightCase(node);
			}

		} else if (node.getBalancef() == +2) {

			if (node.getRight().getBalancef() >= 0) {
				return rightRightCase(node);
			} else {
				return rightLeftCase(node);
			}
		}

		return node;
	}

	private Node<K,V> leftLeftCase(Node<K,V> node) {
		return rightRotation(node);
	}

	private Node<K,V> leftRightCase(Node<K,V> node) {
		node.setLeft(leftRotation(node.getLeft()));
		return leftLeftCase(node);
	}

	private Node<K,V> rightRightCase(Node<K,V> node) {
		return leftRotation(node);
	}

	private Node<K,V> rightLeftCase(Node<K,V> node) {

		node.setRight(rightRotation(node.getRight()));
		return rightRightCase(node);
	}

	private Node<K,V> leftRotation(Node<K,V> node) {
		Node<K,V> newParent = node.getRight();
		node.setRight(newParent.getLeft());
		newParent.setLeft(node);
		update(node);
		update(newParent);
		return newParent;
	}

	private Node<K,V> rightRotation(Node<K,V> node) {
		Node<K,V> newParent = node.getLeft();
		node.setLeft(newParent.getRight());
		newParent.setRight(node);
		update(node);
		update(newParent);
		return newParent;
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
}
