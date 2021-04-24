package dataStructures;

import java.util.ArrayList;

public class Node<K extends Comparable<K>,V> {
	
    public static final int BLACK = 0;
    public static final int RED = 1;
    
	Node<K,V> father;
	Node<K,V> left;
	Node<K,V> right;
	K key;
    public int numLeft;
    public int numRight;
    public int color;
    private int heigth;
	private int balancef;
	ArrayList<V> values;


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
	
	public ArrayList<V> getValues() {
		return values;
	}
	
	public Node<K, V> getFather() {
		return father;
	}
	
	public void setValues(ArrayList<V> values) {
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
