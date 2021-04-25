package dataStructures;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

public class GenericAVLTreeTest {

	GenericAVLTree<String, Integer> testTree;

	public void setupScenary1() {
		testTree = new GenericAVLTree<String, Integer>("A",1);
		testTree.insert("B", 2);
		testTree.insert("C", 3);

	}
	public void setupScenary2() {
		testTree = new GenericAVLTree<String, Integer>("D",7);
		testTree.insert("C", 5);
		testTree.insert("A", 6);
	}
	public void setupScenary3() {
		testTree = new GenericAVLTree<String, Integer>("F", 10);
		testTree.insert("C", 9);
		testTree.insert("D", 13);
	}
	public void setupScenary4() {

		testTree = new GenericAVLTree<String, Integer>("A", 6);
		testTree.insert("E", 12);
		testTree.insert("B", 2);	
	}
	public void setupScenary5() {

		testTree = new GenericAVLTree<String, Integer>("A", 6);
		testTree.insert("E", 12);
		testTree.insert("B", 2);
		testTree.insert("B", 3);	
		testTree.insert("B", 8);
		testTree.insert("E", 13);
		testTree.insert("E", 14);
		testTree.insert("E", 15);
		testTree.insert("E", 16);		
	}
	
	public void setupScenary6() {
		testTree = new GenericAVLTree<String, Integer>();
		testTree.insert("A", 1);
		testTree.insert("A", 2);
		testTree.insert("A", 3);
		testTree.insert("B", 4);
		testTree.insert("B", 5);
		testTree.insert("B", 6);
		testTree.insert("C", 7);
		testTree.insert("C", 8);
		testTree.insert("C", 9);
	}
	@Test
	public void InsertTest() {
		setupScenary1();
		assertEquals(new Integer(1), testTree.search("A").get(0), "Test failed");
		assertEquals(new Integer(2), testTree.getRoot().getValues().get(0), "Test failed");
		assertEquals(new Integer(3), testTree.getRoot().getRight().getValues().get(0), "Test failed");
		assertEquals("A", testTree.getRoot().getLeft().getKey(), "Test failed");		
	}
	@Test
	public void InsertTest2() {

		setupScenary2();
		assertEquals(new Integer(7), testTree.search("D").get(0), "Test failed");
		assertEquals(new Integer(5), testTree.getRoot().getValues().get(0), "Test failed");
		assertEquals(new Integer(7), testTree.getRoot().getRight().getValues().get(0), "Test failed");
		assertEquals("A", testTree.getRoot().getLeft().getKey(), "Test failed");

	}
	@Test
	public void InsertTest3() {

		setupScenary3();
		assertEquals(new Integer(13), testTree.search("D").get(0), "Test failed");
		assertEquals(new Integer(13), testTree.getRoot().getValues().get(0), "Test failed");
		assertEquals(new Integer(10), testTree.getRoot().getRight().getValues().get(0), "Test failed");
		assertEquals("C", testTree.getRoot().getLeft().getKey(), "Test failed");

	}
	@Test
	public void InsertTest4() {

		setupScenary4();
		assertEquals(new Integer(6), testTree.search("A").get(0), "Test failed");
		assertEquals(new Integer(2), testTree.getRoot().getValues().get(0), "Test failed");
		assertEquals(new Integer(12), testTree.getRoot().getRight().getValues().get(0), "Test failed");
		assertEquals("A", testTree.getRoot().getLeft().getKey(), "Test failed");

	}
	@Test
	public void SearchTest() {

		setupScenary5();
		assertEquals(new Integer(2), testTree.getRoot().getValues().get(0), "Test failed");
		assertEquals(new Integer(3), testTree.getRoot().getValues().get(1), "Test failed");
		assertEquals(new Integer(8),testTree.getRoot().getValues().get(2), "Test failed");
		assertEquals(new Integer(12), testTree.search("E").get(0), "Test failed");
		assertEquals(new Integer(13), testTree.search("E").get(1), "Test failed");
		assertEquals(new Integer(14),testTree.search("E").get(2), "Test failed");
			
	}
	
	@Test
	public void getGreaterThanTest() {
		setupScenary6();
		List<Integer> test = testTree.getGreaterThan("A", 6);
		assertEquals(6, test.size(), "Test failed");
		List<Integer> test2 = testTree.getGreaterThan("A", 7);
		assertEquals(6, test2.size(), "Test failed");
		List<Integer> test3 = testTree.getGreaterThan("A", 5);
		assertEquals(5, test3.size(), "Test failed");
		List<Integer> test4 = testTree.getGreaterThan("B", 3);
		assertEquals(3, test4.size(), "Test failed");
	}
	
	@Test
	public void getLowerThanTest() {
		setupScenary6();
		List<Integer> test = testTree.getLowestThan("C", 6);
		assertEquals(6, test.size(), "Test failed");
		List<Integer> test2 = testTree.getLowestThan("C", 7);
		assertEquals(6, test2.size(), "Test failed");
		List<Integer> test3 = testTree.getLowestThan("C", 5);
		assertEquals(5, test3.size(), "Test failed");
		List<Integer> test4 = testTree.getLowestThan("B", 3);
		assertEquals(3, test4.size(), "Test failed");
	}

}
