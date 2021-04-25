package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class GenericRedBlackTree<K extends Comparable<K>, V> implements GenericRedBlackTreeInterface<K,V>{

	private Node<K,V> root = null;

	/**
	 * Constructor of an empty red black tree
	 */
	public GenericRedBlackTree() {}

	/**
	 * This method rotates a node to the left considering all the cases
	 * @param x is the node to rotate
	 */
	private void leftRotate(Node<K,V> x){
		leftRotateFixup(x);
		Node<K,V> y;
		y = x.getRight();
		x.setRight(y.getLeft());
		if (!isnull(y.getLeft()))
			y.getLeft().setFather(x);
		y.setFather(x.getFather());
		if (isnull(x.getFather()))
			root = y;
		else if (x.getFather().getLeft() == x)
			x.getFather().setLeft(y);
		else
			x.getFather().setRight(y);
		y.setLeft(x);
		x.setFather(y);
	}
	/**
	 * This method correct the quantity of nodes in each subtree
	 * @param x is the node to correct
	 */
	private void leftRotateFixup(Node<K,V> x){
		if (isnull(x.getLeft()) && isnull(x.getRight().getLeft())){
			x.setNumLeft(0);
			x.setNumRight(0);
			x.getRight().setNumLeft(1);
		}
		else if (isnull(x.getLeft()) && !isnull(x.getRight().getLeft())){
			x.setNumLeft(0);
			x.setNumRight(1 + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight());
			x.getRight().setNumLeft(2 + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight());
		}
		else if (!isnull(x.getLeft()) && isnull(x.getRight().getLeft())){
			x.setNumRight(0);
			x.getRight().setNumLeft(2 + x.getLeft().getNumLeft() + x.getLeft().getNumRight());
		}
		else{
			x.setNumRight(1 + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight());
			x.getRight().setNumLeft(3 + x.getLeft().getNumLeft() + x.getLeft().getNumRight() + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight());
		}
	}
	/**
	 * This method rotates a node to the right considering all the cases
	 * @param x is the node to rotate
	 */
	private void rightRotate(Node<K,V> y){
		rightRotateFixup(y);
		Node<K,V> x = y.getLeft();
		y.setLeft(x.getRight());
		if (!isnull(x.getRight()))
			x.getRight().setFather(y);
		x.setFather(y.getFather());
		if (isnull(y.getFather()))
			root = x;
		else if (y.getFather().getRight() == y)
			y.getFather().setRight(x);
		else
			y.getFather().setLeft(x);
		x.setRight(y);
		y.setFather(x);
	}
	/**
	 * This method correct the quantity of nodes in each subtree
	 * @param x is the node to correct
	 */
	private void rightRotateFixup(Node<K,V> y){
		if (isnull(y.getRight()) && isnull(y.getLeft().getRight())){
			y.setNumRight(0);
			y.setNumLeft(0);
			y.getLeft().setNumRight(1);
		}
		else if (isnull(y.getRight()) && !isnull(y.getLeft().getRight())){
			y.setNumRight(0);
			y.setNumLeft(1 + y.getLeft().getRight().getNumRight() + y.getLeft().getRight().getNumLeft());
			y.getLeft().setNumRight(2 + y.getLeft().getRight().getNumRight() +	y.getLeft().getRight().getNumLeft());
		}
		else if (!isnull(y.getRight()) && isnull(y.getLeft().getRight())){
			y.setNumLeft(0);
			y.getLeft().setNumRight(2 + y.getRight().getNumRight() +y.getRight().getNumLeft());
		}
		else{
			y.setNumLeft(1 + y.getLeft().getRight().getNumRight() + y.getLeft().getRight().getNumLeft());
			y.getLeft().setNumRight(3 + y.getRight().getNumRight() + y.getRight().getNumLeft() + y.getLeft().getRight().getNumRight() 
					+ y.getLeft().getRight().getNumLeft());
		}
	}
	/**
	 * This method insert a node into the tree
	 * @param key is the key
	 * @param value is the value
	 */
	public void insert(K key, V value) {
		insert(new Node<K,V>(key, value), value);
	}
	/**
	 * This method creates the links between the node to insert and its respective father
	 * @param z node to insert
	 * @param value value of the node
	 * @return
	 */
	private void insert(Node<K,V> z, V value) {
		Node<K,V> y = null;
		Node<K,V> x = root;
		while (!isnull(x)){
			y = x;
			if (contains(z.getKey()) != null) {
				contains(z.getKey()).getValues().add(value);
				x = null;
				return;
			}
			else if (z.getKey().compareTo(x.getKey()) < 0){
				x.setNumLeft(x.getNumLeft()+1);
				x = x.getLeft();
			}
			else if(z.getKey().compareTo(x.getKey()) > 0){
				x.setNumRight(x.getNumRight()+1);
				x = x.getRight();
			}
		}
		z.setFather(y);
		if (isnull(y))
			root = z;
		else if (z.getKey().compareTo(y.getKey()) < 0)
			y.setLeft(z);
		else
			y.setRight(z);
		z.setLeft(null);
		z.setRight(null);
		z.setColor(Node.RED);
		insertFixup(z);
	}
	/**
	 * This method fixes the colors to conserve the properties of the tree
	 * @param z node added to fix colors of his parents
	 */
	private void insertFixup(Node<K,V> z){
		Node<K,V> y = null;
		if (z.getFather() != null) {
			while ((z.getFather() != null)&&(z.getFather().getColor() == Node.RED)){
				if (z.getFather().getFather() != null) {
					if (z.getFather() == z.getFather().getFather().getLeft()){
						y = z.getFather().getFather().getRight();
						if (y != null) {
							if (y.getColor() == Node.RED){
								z.getFather().setColor(Node.BLACK);
								y.setColor(Node.BLACK);
								z.getFather().getFather().setColor(Node.RED);
								z = z.getFather().getFather();
							}
						}
						else if (z == z.getFather().getRight()){
							z = z.getFather();
							leftRotate(z);
							z = z.getFather().getFather();
							rightRotate(z);
						}
						else{
							z.getFather().setColor(Node.BLACK);
							z.getFather().getFather().setColor(Node.RED);
							rightRotate(z.getFather().getFather());
						}
					}
					else{
						y = z.getFather().getFather().getLeft();
						if (y != null) {
							if (y.getColor() == Node.RED){
								z.getFather().setColor(Node.BLACK);
								y.setColor(Node.BLACK);
								z.getFather().getFather().setColor(Node.RED);
								z = z.getFather().getFather();
							}
						}
						else if (z == z.getFather().getLeft()){
							// rightRotate around z's father
							z = z.getFather();
							rightRotate(z);
							z = z.getFather().getFather();
							leftRotate(z);
						}
						else{
							z.getFather().setColor(Node.BLACK);
							z.getFather().getFather().setColor(Node.RED);
							leftRotate(z.getFather().getFather());
						}
					}
				}
				if (z.getFather() != null) {
					if (z.getFather().getColor() == Node.RED) {
						z.getFather().setColor(Node.BLACK);
					}
				}
			}
			root.setColor(Node.BLACK);
		}
	}
	/**
	 * This method searches a node and returns its values
	 * @param key is the key of the node searched
	 */
	public List<V> search(K key){
		Node<K,V> current = root;
		while (!isnull(current)){
			if (current.getKey().equals(key))
				return current.getValues();
			else if (current.getKey().compareTo(key) < 0)
				current = current.getRight();
			else
				current = current.getLeft();
		}
		return null;
	}
	/**
	 * This method searches a node and returns the node
	 * @param key is the key of the node searched
	 */
	private Node<K,V> contains(K key){
		Node<K,V> current = root;
		while (!isnull(current)){
			if (current.getKey().equals(key))
				return current;
			else if (current.getKey().compareTo(key) < 0)
				current = current.getRight();
			else
				current = current.getLeft();
		}
		return null;
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

	public int size(){
		return root.getNumLeft() + root.getNumRight() + 1;
	}

}