package dataStructures;

import java.util.ArrayList;

public class GenericAVLTree<K extends Comparable<K>,V> {

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
	public boolean contains(K key) {
		return contains(root, key);
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

	private boolean contains(Node<K,V> node, K key) {
		if (node == null) return false;
		int cmp = key.compareTo(node.getKey());

		if (cmp < 0) return contains(node.getLeft(), key );
		if (cmp > 0) return contains(node.getRight(), key);

		return true;
	}

	public void insert(K key, V value) {
			root = insert(root, key, value);
			nodeCount++;
	}

	private Node<K,V> insert(Node<K,V> node, K key, V value) {
		if (node == null) return new Node<K,V>(key,value);
		int cmp = key.compareTo(node.getKey());
		if (cmp < 0) {

			node.setLeft(insert(node.getLeft(), key, value));
		} else if(cmp > 0){
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

	public boolean validateBSTInvarient(Node<K,V> node) {
		if (node == null) return true;
		K key = node.getKey();
		boolean isValid = true;
		if (node.getLeft() != null) isValid = isValid && node.getLeft().getKey().compareTo(key) < 0;
		if (node.getRight() != null) isValid = isValid && node.getRight().getKey().compareTo(key) > 0;
		return isValid && validateBSTInvarient(node.getLeft()) && validateBSTInvarient(node.getRight());
	}
}
