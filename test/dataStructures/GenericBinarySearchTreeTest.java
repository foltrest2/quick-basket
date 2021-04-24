package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GenericBinarySearchTreeTest {

    GenericBinarySearchTree<String, Integer> testTree;

    public void setupScenary1() {
        testTree = new GenericBinarySearchTree<String, Integer>();
        testTree.put("A", 1);
        testTree.put("B", 2);
        testTree.put("C", 3);
        testTree.put("D", 4);
        testTree.put("B", 5);
        testTree.put("B", 6);
    }
    public void setupScenary2() {
        testTree = new GenericBinarySearchTree<String, Integer>();
        testTree.put("C", 1);
        testTree.put("A", 2);
        testTree.put("B", 3);
        testTree.put("D", 4);
        testTree.put("E", 5);
        testTree.put("E", 6);
    }
    public void setupScenary3() {
        testTree = new GenericBinarySearchTree<String, Integer>();
        testTree.put("G", 1);
        testTree.put("D", 2);
        testTree.put("E", 3);
        testTree.put("T", 4);
        testTree.put("U", 5);
        testTree.put("S", 6);
        testTree.put("C", 7);
        testTree.put("A", 8);
        testTree.put("B", 9);
    }

    @Test
    public void InsertTest() {
        setupScenary1();
        assertEquals(new Integer(1), testTree.search("A").get(0), "Test failed");
        assertEquals(new Integer(2), testTree.search("B").get(0), "Test failed");
        assertEquals(new Integer(5), testTree.search("B").get(1), "Test failed");
        assertEquals(new Integer(6), testTree.search("B").get(2), "Test failed");
    }
    
    @Test
    public void InsertTest2() {
        setupScenary2();
        assertEquals(new Integer(2), testTree.search("A").get(0), "Test failed");
        assertEquals(new Integer(1), testTree.search("C").get(0), "Test failed");
        assertEquals(new Integer(4), testTree.search("D").get(0), "Test failed");
        assertEquals(new Integer(6), testTree.search("E").get(1), "Test failed");
    }
    
    @Test
    public void MaxMinTest() {
        setupScenary3();
        assertEquals("A", testTree.min().getKey(), "Test failed");
        assertEquals("U", testTree.max().getKey(), "Test failed");
    }

}