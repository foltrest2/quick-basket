package dataStructures;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class GenericAVLTreeTest {

	GenericAVLTree<String, Integer> tesstAVL;

	public void setupScenary1() {
		tesstAVL = new GenericAVLTree<String, Integer>("A",1);
		tesstAVL.insert("B", 2);
		tesstAVL.insert("C", 3);
		
	}
	public void setupScenary2() {
		tesstAVL = new GenericAVLTree<String, Integer>("D",7);
		tesstAVL.insert("C", 5);
		tesstAVL.insert("A", 6);
		
	}
	
	public void setupScenary3() {
		tesstAVL = new GenericAVLTree<String, Integer>("F", 10);
		tesstAVL.insert("C", 9);
		tesstAVL.insert("D", 13);
	
	}
	
	public void setupScenary4() {
	
		tesstAVL = new GenericAVLTree<String, Integer>("A", 6);
		tesstAVL.insert("E", 12);
		tesstAVL.insert("B", 2);	
	}
	@Test
	public void InsertTest() {
		setupScenary1();
		assertEquals(new Integer(1), tesstAVL.search("A").get(0), "Test failed");
		assertEquals(new Integer(2), tesstAVL.getRoot().getValues().get(0), "Test failed");
		assertEquals(new Integer(3), tesstAVL.getRoot().getRight().getValues().get(0), "Test failed");
		assertEquals("A", tesstAVL.getRoot().getLeft().getKey(), "Test failed");		
	}
	
	@Test
	public void InsertTest2() {
		
		setupScenary2();
		assertEquals(new Integer(7), tesstAVL.search("D").get(0), "Test failed");
		assertEquals(new Integer(5), tesstAVL.getRoot().getValues().get(0), "Test failed");
		assertEquals(new Integer(7), tesstAVL.getRoot().getRight().getValues().get(0), "Test failed");
		assertEquals("A", tesstAVL.getRoot().getLeft().getKey(), "Test failed");
		
	}
	@Test
	public void InsertTest3() {
		
		setupScenary3();
		assertEquals(new Integer(13), tesstAVL.search("D").get(0), "Test failed");
		assertEquals(new Integer(13), tesstAVL.getRoot().getValues().get(0), "Test failed");
		assertEquals(new Integer(10), tesstAVL.getRoot().getRight().getValues().get(0), "Test failed");
		assertEquals("C", tesstAVL.getRoot().getLeft().getKey(), "Test failed");
		
	}
	@Test
	public void InsertTest4() {
		
		setupScenary4();
		assertEquals(new Integer(6), tesstAVL.search("A").get(0), "Test failed");
		assertEquals(new Integer(2), tesstAVL.getRoot().getValues().get(0), "Test failed");
		assertEquals(new Integer(12), tesstAVL.getRoot().getRight().getValues().get(0), "Test failed");
		assertEquals("A", tesstAVL.getRoot().getLeft().getKey(), "Test failed");
	
	}
	
}
