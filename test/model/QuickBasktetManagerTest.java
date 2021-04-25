package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class QuickBasktetManagerTest {

	private QuickBasketManager qbm;

	public void setupScenary1() {
		qbm = new QuickBasketManager();
		try {
			qbm.importData();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			qbm.addNewPlayer(200005, "Julian", 19, "Colombia", 123, 456, 789, 124, 125, 95);
			qbm.addNewPlayer(200003, "Alejandro", 18, "Colombia", 123, 456, 789, 124, 125, 98);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addAndSearchAndDeleteTest1() {
		setupScenary1();
		assertTrue(qbm.binarySearchPlayer(200005));
		assertTrue(qbm.binarySearchPlayer(200003));

		try {
			qbm.deletePlayer(200005);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertFalse(qbm.binarySearchPlayer(200005));
		try {
			qbm.deletePlayer(200003);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertFalse(qbm.binarySearchPlayer(200003));
	}
	
	@Test
	public void resetTest1() {
		setupScenary1();
		qbm.reset();
		assertEquals(0, qbm.getPlayersListJunitTest().size(), "Test failed");
	}
}
