package dataStructures;

import java.util.ArrayList;

public class Node<K extends Comparable<K>,V> {

	private Node<K,V> father;
	private Node<K,V> left;
	private Node<K,V> right;
	private K key;
	private ArrayList<V> values;

	public Node(K key, V value) {
		this.key = key;
		values = new ArrayList<V>();
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

}
