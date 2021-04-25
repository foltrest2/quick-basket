package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class GenericRedBlackTree<K extends Comparable<K>, V> implements GenericRedBlackTreeInterface<K,V>{

	private Node<K,V> root = null;

	public GenericRedBlackTree() {}

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

	public void insert(K key, V value) {
		insert(new Node<K,V>(key, value), value);
	}

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
    
    public void reset() {
    	root = null;
    }
}