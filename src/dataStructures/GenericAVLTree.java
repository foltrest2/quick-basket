package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class GenericAVLTree<K extends Comparable<K>,V> implements GenericAVLTreeInterface<K, V>{

	private int nodeCount = 0;

	private Node<K,V> root;
	/**
	 * This is the constructor of a generic AVL tree with null root
	 */
	public GenericAVLTree(){}
	/**
	 * This is the constructor of a generic AVL tree initializing the root with the given information
	 * @param k key
	 * @param v value
	 */
	public GenericAVLTree(K k, V v) {
		root = new Node<K,V>(k,v);
	}
	/**
	 * This method searches a node and returns its values
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
	 * This method inserts the node inside the tree
	 */
	public void insert(K key, V value) {
		root = insert(root, key, value);
	}
	/**
	 * This method creates the links between the node to insert and its respective father
	 * @param node node to insert
	 * @param key key of the node
	 * @param value value of the node
	 * @return
	 */
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
	/**
	 * This method actualizes the balance factor of one node
	 * @param node node to actualizes
	 */
	private void update(Node<K,V> node) {
		int leftNodeHeight = (node.getLeft() == null) ? -1 : node.getLeft().getHeight();
		int rightNodeHeight = (node.getRight() == null) ? -1 : node.getRight().getHeight();
		node.setHeight(1 + Math.max(leftNodeHeight, rightNodeHeight));
		node.setBalancef( rightNodeHeight - leftNodeHeight);
	}
	/**
	 * This method analyzes all the cases for balance the tree 
	 * @param node node to balance
	 * @return
	 */
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
	/**
	 * This method rotates the tree when we have the case of a left line  
	 * @param node node to rotate
	 * @return
	 */
	private Node<K,V> leftLeftCase(Node<K,V> node) {
		return rightRotation(node);
	}
	/**
	 * This method rotates the tree when we have the case of a left triangle 
	 * @param node node to rotate
	 * @return
	 */
	private Node<K,V> leftRightCase(Node<K,V> node) {
		node.setLeft(leftRotation(node.getLeft()));
		return leftLeftCase(node);
	}
	/**
	 * This method rotates the tree when we have the case of a right line
	 * @param node
	 * @return
	 */
	private Node<K,V> rightRightCase(Node<K,V> node) {
		return leftRotation(node);
	}
	/**
	 * This method rotates the tree when we have the case of a right triangle
	 * @param node
	 * @return
	 */
	private Node<K,V> rightLeftCase(Node<K,V> node) {
		node.setRight(rightRotation(node.getRight()));
		return rightRightCase(node);
	}
	/**
	 * This method makes a left rotation of one node
	 * @param node node to rotate
	 * @return
	 */
	private Node<K,V> leftRotation(Node<K,V> node) {
		Node<K,V> newParent = node.getRight();
		node.setRight(newParent.getLeft());
		newParent.setLeft(node);
		update(node);
		update(newParent);
		return newParent;
	}
	/**
	 * This method makes the right rotation of one node
	 * @param node node to rotate
	 * @return
	 */
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
	public Node<K,V> getRoot(){
		return root;
	}
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

}
