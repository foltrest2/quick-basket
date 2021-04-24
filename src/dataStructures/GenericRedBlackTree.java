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

		// Case 1: Only x, x.getRight() and x.getRight().right always are not null.
		if (isnull(x.getLeft()) && isnull(x.getRight().left)){
			x.numLeft = 0;
			x.numRight = 0;
			x.getRight().numLeft = 1;
		}

		// Case 2: x.getRight().left also exists in addition to Case 1
		else if (isnull(x.getLeft()) && !isnull(x.getRight().left)){
			x.numLeft = 0;
			x.numRight = 1 + x.getRight().left.numLeft +
					x.getRight().left.numRight;
			x.getRight().numLeft = 2 + x.getRight().left.numLeft +
					x.getRight().left.numRight;
		}

		// Case 3: x.getLeft() also exists in addition to Case 1
		else if (!isnull(x.getLeft()) && isnull(x.getRight().left)){
			x.numRight = 0;
			x.getRight().numLeft = 2 + x.getLeft().numLeft + x.getLeft().numRight;

		}
		// Case 4: x.getLeft() and x.getRight().left both exist in addtion to Case 1
		else{
			x.numRight = 1 + x.getRight().left.numLeft +
					x.getRight().left.numRight;
			x.getRight().numLeft = 3 + x.getLeft().numLeft + x.getLeft().numRight +
					x.getRight().left.numLeft + x.getRight().left.numRight;
		}

	}// end leftRotateFixup(Node x)

	private void rightRotate(Node<K,V> y){
		rightRotateFixup(y);
		Node<K,V> x = y.left;
		y.left = x.getRight();

		if (!isnull(x.getRight()))
			x.getRight().father = y;
		x.father = y.father;

		if (isnull(y.father))
			root = x;

		else if (y.father.right == y)
			y.father.right = x;

		else
			y.father.left = x;
		x.right = y;

		y.father = x;

	}

	private void rightRotateFixup(Node<K,V> y){

		if (isnull(y.right) && isnull(y.left.right)){
			y.numRight = 0;
			y.numLeft = 0;
			y.left.numRight = 1;
		}

		else if (isnull(y.right) && !isnull(y.left.right)){
			y.numRight = 0;
			y.numLeft = 1 + y.left.right.numRight +
					y.left.right.numLeft;
			y.left.numRight = 2 + y.left.right.numRight +
					y.left.right.numLeft;
		}

		else if (!isnull(y.right) && isnull(y.left.right)){
			y.numLeft = 0;
			y.left.numRight = 2 + y.right.numRight +y.right.numLeft;
		}

		else{
			y.numLeft = 1 + y.left.right.numRight +
					y.left.right.numLeft;
			y.left.numRight = 3 + y.right.numRight +
					y.right.numLeft +
					y.left.right.numRight + y.left.right.numLeft;
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

			// if z.key is < than the current key, go left
			if (z.key.compareTo(x.key) < 0){

				// Update x.numLeft as z is < than x
				x.numLeft++;
				x = x.getLeft();
			}

			// else z.key >= x.key so go right.
			else if(z.key.compareTo(x.key) > 0){

				// Update x.numGreater as z is => x
				x.numRight++;
				x = x.getRight();
			}
			else {
				x.values.add(value);
				x = null;
				return;
			}
		}
		// y will hold z's father
		z.father = y;

		// Depending on the value of y.key, put z as the left or
		// right child of y
		if (isnull(y))
			root = z;
		else if (z.key.compareTo(y.key) < 0)
			y.left = z;
		else
			y.right = z;

		// Initialize z's children to null and z's color to red
		z.left = null;
		z.right = null;
		z.color = Node.RED;

		// Call insertFixup(z)
		insertFixup(z);

	}

	private void insertFixup(Node<K,V> z){

		Node<K,V> y = null;
		// While there is a violation of the RedBlackTree properties..
		if (z.father != null) {
			while ((z.father != null)&&(z.father.color == Node.RED)){
				// If z's father is the the left child of it's father.
				if (z.father.father != null) {
					if (z.father == z.father.father.left){

						// Initialize y to z 's cousin
						y = z.father.father.right;

						// Case 1: if y is red...recolor
						if (y != null) {
							if (y.color == Node.RED){
								z.father.color = Node.BLACK;
								y.color = Node.BLACK;
								z.father.father.color = Node.RED;
								z = z.father.father;
							}
						}
						// Case 2: if y is black & z is a right child
						else if (z == z.father.right){

							// leftRotaet around z's father
							z = z.father;
							leftRotate(z);
							z = z.father.father;
							rightRotate(z);
						}

						// Case 3: else y is black & z is a left child
						else{
							// recolor and rotate round z's grandpa
							z.father.color = Node.BLACK;
							z.father.father.color = Node.RED;
							rightRotate(z.father.father);
						}
					}
					// If z's father is the right child of it's father.
					else{
						// Initialize y to z's cousin
						y = z.father.father.left;

						// Case 1: if y is red...recolor
						if (y != null) {
							if (y.color == Node.RED){
								z.father.color = Node.BLACK;
								y.color = Node.BLACK;
								z.father.father.color = Node.RED;
								z = z.father.father;
							}
						}
						// Case 2: if y is black and z is a left child
						else if (z == z.father.left){
							// rightRotate around z's father
							z = z.father;
							rightRotate(z);
							z = z.father.father;
							leftRotate(z);
						}
						// Case 3: if y  is black and z is a right child
						else{
							// recolor and rotate around z's grandpa
							z.father.color = Node.BLACK;
							z.father.father.color = Node.RED;
							leftRotate(z.father.father);
						}
					}
				}
				if (z.father != null) {
					if (z.father.color == Node.RED) {
						z.father.color = Node.BLACK;
					}
				}

			}


			// Color root black at all times
			root.color = Node.BLACK;
		}
	}// end insertFixup(Node z)

	public Node<K,V> treeMinimum(Node<K,V> node){
		while (!isnull(node.left))
			node = node.left;
		return node;
	}

	public Node<K,V> treeSuccessor(Node<K,V> x){

		// if x.getLeft() is not null, call treeMinimum(x.getRight()) and
		// return it's value
		if (!isnull(x.getLeft()) )
			return treeMinimum(x.getRight());

		Node<K,V> y = x.getFather();

		// while x is it's father's right child...
		while (!isnull(y) && x == y.right){
			// Keep moving up in the tree
			x = y;
			y = y.father;
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
			if (current.key.equals(key))
				return current.values;
			else if (current.key.compareTo(key) < 0)
				current = current.right;
			else
				current = current.left;
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
		} else if (node.key.compareTo(key) > 0) {
			getGreaterThan(node.left, key, list);
			list.addAll(node.values);
			getGreaterThan(node.right, key, list);
		} else {
			getGreaterThan(node.right, key, list);
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
		else if (key.compareTo(node.key) <= 0)
			return findNumSmaller(node.left,key);
		else
			return 1+ node.numLeft + findNumSmaller(node.right,key);
	}

	private boolean isnull(Node<K,V> node){
		return node == null;		
	}

	public int size(){
		return root.numLeft + root.numRight + 1;
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