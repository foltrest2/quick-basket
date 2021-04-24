package dataStructures;

import java.util.ArrayList;
import java.util.List;

public class GenericRedBlackTree<K extends Comparable<K>, V> {

	// Root initialized to null.
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

		// Case 1: Only x, x.getRight() and x.getRight().getRight() always are not null.
		if (isnull(x.getLeft()) && isnull(x.getRight().getLeft())){
			x.setNumLeft(0);
			x.setNumRight(0);
			x.getRight().setNumLeft(1);
		}

		// Case 2: x.getRight().getLeft() also exists in addition to Case 1
		else if (isnull(x.getLeft()) && !isnull(x.getRight().getLeft())){
			x.setNumLeft(0);
			x.setNumRight(1 + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight());
			x.getRight().setNumLeft(2 + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight());
		}

		// Case 3: x.getLeft() also exists in addition to Case 1
		else if (!isnull(x.getLeft()) && isnull(x.getRight().getLeft())){
			x.setNumRight(0);
			x.getRight().setNumLeft(2 + x.getLeft().getNumLeft() + x.getLeft().getNumRight());

		}
		// Case 4: x.getLeft() and x.getRight().getLeft() both exist in addtion to Case 1
		else{
			x.setNumRight(1 + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight());
			x.getRight().setNumLeft(3 + x.getLeft().getNumLeft() + x.getLeft().getNumRight() + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight());
		}

	}// end leftRotateFixup(Node x)

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

		// While we haven't reached a the end of the tree keep
		// tryint to figure out where z should go
		while (!isnull(x)){
			y = x;

			// if z.getKey() is < than the current key, go left
			if (z.getKey().compareTo(x.getKey()) < 0){

				// Update x.getNumLeft() as z is < than x
				x.setNumLeft(x.getNumLeft()+1);
				x = x.getLeft();
			}

			// else z.getKey() >= x.getKey() so go right.
			else if(z.getKey().compareTo(x.getKey()) > 0){

				// Update x.numGreater as z is => x
				x.setNumRight(x.getNumRight()+1);
				x = x.getRight();
			}
			else {
				x.getValues().add(value);
				x = null;
				return;
			}
		}
		// y will hold z's father
		z.setFather(y);

		// Depending on the value of y.getKey(), put z as the left or
		// right child of y
		if (isnull(y))
			root = z;
		else if (z.getKey().compareTo(y.getKey()) < 0)
			y.setLeft(z);
		else
			y.setRight(z);

		// Initialize z's children to null and z's color to red
		z.setLeft(null);
		z.setRight(null);
		z.setColor(Node.RED);

		// Call insertFixup(z)
		insertFixup(z);

	}

	private void insertFixup(Node<K,V> z){

		Node<K,V> y = null;
		// While there is a violation of the RedBlackTree properties..
		if (z.getFather() != null) {
			while ((z.getFather() != null)&&(z.getFather().getColor() == Node.RED)){
				// If z's father is the the left child of it's father.
				if (z.getFather().getFather() != null) {
					if (z.getFather() == z.getFather().getFather().getLeft()){

						// Initialize y to z 's cousin
						y = z.getFather().getFather().getRight();

						// Case 1: if y is red...recolor
						if (y != null) {
							if (y.getColor() == Node.RED){
								z.getFather().setColor(Node.BLACK);
								y.setColor(Node.BLACK);
								z.getFather().getFather().setColor(Node.RED);
								z = z.getFather().getFather();
							}
						}
						// Case 2: if y is black & z is a right child
						else if (z == z.getFather().getRight()){

							// leftRotaet around z's father
							z = z.getFather();
							leftRotate(z);
<<<<<<< HEAD
							z = z.getFather().getFather();
=======
							z = z.father.father;
>>>>>>> b7c5767fbcdc02eebb0f16602dc26413a68c883a
							rightRotate(z);
						}

						// Case 3: else y is black & z is a left child
						else{
							// recolor and rotate round z's grandpa
							z.getFather().setColor(Node.BLACK);
							z.getFather().getFather().setColor(Node.RED);
							rightRotate(z.getFather().getFather());
						}
					}
					// If z's father is the right child of it's father.
					else{
						// Initialize y to z's cousin
						y = z.getFather().getFather().getLeft();

						// Case 1: if y is red...recolor
						if (y != null) {
							if (y.getColor() == Node.RED){
								z.getFather().setColor(Node.BLACK);
								y.setColor(Node.BLACK);
								z.getFather().getFather().setColor(Node.RED);
								z = z.getFather().getFather();
							}
						}
						// Case 2: if y is black and z is a left child
						else if (z == z.getFather().getLeft()){
							// rightRotate around z's father
							z = z.getFather();
							rightRotate(z);
<<<<<<< HEAD
							z = z.getFather().getFather();
=======
							z = z.father.father;
>>>>>>> b7c5767fbcdc02eebb0f16602dc26413a68c883a
							leftRotate(z);
						}
						// Case 3: if y  is black and z is a right child
						else{
							// recolor and rotate around z's grandpa
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


			// Color root black at all times
			root.setColor(Node.BLACK);
		}
	}// end insertFixup(Node z)

	public Node<K,V> treeMinimum(Node<K,V> node){
		while (!isnull(node.getLeft()))
			node = node.getLeft();
		return node;
	}

	public Node<K,V> treeSuccessor(Node<K,V> x){

		// if x.getLeft() is not null, call treeMinimum(x.getRight()) and
		// return it's value
		if (!isnull(x.getLeft()) )
			return treeMinimum(x.getRight());

		Node<K,V> y = x.getFather();

		// while x is it's father's right child...
		while (!isnull(y) && x == y.getRight()){
			// Keep moving up in the tree
			x = y;
			y = y.getFather();
		}
		// Return successor
		return y;
	}// end treeMinimum(Node x)

	// @param: key, the key whose node we want to search for
	// @return: returns a node with the key, key, if not found, returns null
	// Searches for a node with key k and returns the first such node, if no
	// such node is found returns null
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
<<<<<<< HEAD
		} else if (node.getKey().compareTo(key) > 0) {
			getGreaterThan(node.getLeft(), key, list);
			list.add(node.getKey());
			getGreaterThan(node.getRight(), key, list);
=======
		} else if (node.key.compareTo(key) > 0) {
			getGreaterThan(node.left, key, list);
			list.addAll(node.values);
			getGreaterThan(node.right, key, list);
>>>>>>> b7c5767fbcdc02eebb0f16602dc26413a68c883a
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
		} else if (node.key.compareTo(key) < 0) {
			getLowestThan(node.right, key, list);
			list.addAll(node.values);
			getLowestThan(node.left, key, list);
		} else {
			getLowestThan(node.left, key, list);
		}
	}

	public int findNumSmaller(Node<K,V> node, K key){
		if (isnull(node)) return 0;
		else if (key.compareTo(node.getKey()) <= 0)
			return findNumSmaller(node.getLeft(),key);
		else
			return 1+ node.getNumLeft() + findNumSmaller(node.getRight(),key);
	}

	private boolean isnull(Node<K,V> node){
		return node == null;		
	}

	public int size(){
		return root.getNumLeft() + root.getNumRight() + 1;
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