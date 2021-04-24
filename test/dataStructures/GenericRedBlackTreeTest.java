package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GenericRedBlackTreeTest {
	
	GenericRedBlackTree<String, Integer> testTree;

    public void setupScenary1() {
        testTree = new GenericRedBlackTree<String, Integer>();
        testTree.insert("A", 1);
        testTree.insert("B", 2);
        testTree.insert("C", 3);
        testTree.insert("D", 4);
        testTree.insert("B", 5);
        testTree.insert("B", 6);
    }
    public void setupScenary2() {
        testTree = new GenericRedBlackTree<String, Integer>();
        testTree.insert("C", 1);
        testTree.insert("A", 2);
        testTree.insert("B", 3);
        testTree.insert("D", 4);
        testTree.insert("E", 5);
        testTree.insert("E", 6);
    }
    public void setupScenary3() {
    	testTree = new GenericRedBlackTree<String, Integer>();
    	testTree.insert("D", 1);
        testTree.insert("C", 2);
        testTree.insert("B", 3);
        testTree.insert("A", 4);
        testTree.insert("C", 5);
        testTree.insert("B", 6);
    }
    public void setupScenary4() {
    	testTree = new GenericRedBlackTree<String, Integer>();
    	testTree.insert("C", 1);
        testTree.insert("E", 2);
        testTree.insert("D", 3);
        testTree.insert("A", 4);
        testTree.insert("B", 5);
        testTree.insert("E", 6);
    }

    @Test
    public void InsertTest() {
        setupScenary1();
        assertEquals(new Integer(1), testTree.search("A").get(0), "Test failed");
        assertEquals(new Integer(2), testTree.search("B").get(0), "Test failed");
        assertEquals(new Integer(3), testTree.search("C").get(0), "Test failed");
        assertEquals(new Integer(4), testTree.search("D").get(0), "Test failed");  
        assertEquals(new Integer(5), testTree.search("B").get(1), "Test failed");
        assertEquals(new Integer(6), testTree.search("B").get(2), "Test failed");
    }
    @Test
    public void InsertTest2() {
        setupScenary2();
        assertEquals(new Integer(1), testTree.search("C").get(0), "Test failed");
        assertEquals(new Integer(2), testTree.search("A").get(0), "Test failed");
        assertEquals(new Integer(3), testTree.search("B").get(0), "Test failed");
        assertEquals(new Integer(4), testTree.search("D").get(0), "Test failed");
        assertEquals(new Integer(5), testTree.search("E").get(0), "Test failed");
        assertEquals(new Integer(6), testTree.search("E").get(1), "Test failed");
    }
    @Test
    public void InsertTest3() {
        setupScenary3();
        assertEquals(new Integer(1), testTree.search("D").get(0), "Test failed");
        assertEquals(new Integer(2), testTree.search("C").get(0), "Test failed");
        assertEquals(new Integer(3), testTree.search("B").get(0), "Test failed");
        assertEquals(new Integer(4), testTree.search("A").get(0), "Test failed");
        assertEquals(new Integer(5), testTree.search("C").get(1), "Test failed");
        assertEquals(new Integer(6), testTree.search("B").get(1), "Test failed");
    }	
    @Test
    public void InsertTest4() {
        setupScenary4();
        assertEquals(new Integer(1), testTree.search("C").get(0), "Test failed");
        assertEquals(new Integer(2), testTree.search("E").get(0), "Test failed");
        assertEquals(new Integer(3), testTree.search("D").get(0), "Test failed");
        assertEquals(new Integer(4), testTree.search("A").get(0), "Test failed");
        assertEquals(new Integer(5), testTree.search("B").get(0), "Test failed");
        assertEquals(new Integer(6), testTree.search("E").get(1), "Test failed");
        assertEquals("E", testTree.max().getKey(), "Test failed");
    }
}
