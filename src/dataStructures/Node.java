package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class Node<K extends Comparable<K>,V> {

    public static final int BLACK = 0;
    public static final int RED = 1;
    
	private Node<K,V> father;
	private Node<K,V> left;
	private Node<K,V> right;
	private K key;
	private int numLeft;
	private int numRight;
	private int color;
    private int heigth;
	private int balancef;
	private List<V> values;

	/**
	 * Constructor of Node
	 * @param key is the key
	 * @param value is the value
	 */
	public Node(K key, V value) {
		this.key = key;
		numLeft = 0;
		numRight = 0;
		values = new ArrayList<V>();
		values.add(value);
	}

	public void addElement(V newValue){
		values.add(newValue);
	}
	
	public List<V> getValues() {
		return values;
	}
	
	public Node<K, V> getFather() {
		return father;
	}
	
	public void setValues(List<V> values) {
		this.values = values;
	}

	public void setFather(Node<K, V> father) {
		this.father = father;
	}

	public Node<K, V> getLeft() {
		return left;
	}

	public void setLeft(Node<K, V> left) {
		this.left = left;
	}

	public Node<K, V> getRight() {
		return right;
	}

	public void setRight(Node<K, V> right) {
		this.right = right;
	}
	
	public int getNumLeft() {
		return numLeft;
	}

	public void setNumLeft(int numLeft) {
		this.numLeft = numLeft;
	}

	public int getNumRight() {
		return numRight;
	}

	public void setNumRight(int numRight) {
		this.numRight = numRight;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}
	public int getHeight() {
		return heigth;
		
	}
	public void setHeight(int h) {
        heigth = h;
	}

	public int getBalancef() {
		return balancef;
	}

	public void setBalancef(int balancef) {
		this.balancef = balancef;
	}
	
}
