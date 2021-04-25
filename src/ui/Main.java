package ui;

import java.io.IOException;

import model.QuickBasketManager;

public class Main {

	private static QuickBasketManager qbm;

	public Main() {

	}

	public static void start() {
		qbm = new QuickBasketManager();
	}

	public static void main(String[] args) {
		Main.start();
		double start = System.nanoTime();
		try {

			qbm.importData();
		} catch (IOException e) {	
			e.printStackTrace();
		}
//		System.out.println(qbm.checkImport());
		
		//Checking for BST
//		System.out.println(qbm.searchUniqueParameter(1,2,0));
//		System.out.println(qbm.searchUniqueParameter(1,149,1));
//		System.out.println(qbm.searchUniqueParameter(1,150,2));
		//Checking for AVL
//		System.out.println(qbm.searchUniqueParameter(2,502,0));
//		System.out.println(qbm.searchUniqueParameter(2,500,1));
//		System.out.println(qbm.searchUniqueParameter(2,500,2));
		//Checking for redBlack
//		System.out.println(qbm.searchUniqueParameter(6,2,0));
		System.out.println(qbm.binarySearchPlayer(200000));
		try {
			System.out.println(qbm.addNewPlayer(200002, "Tuma2", 19, "what?", 123, 456, 789, 124, 125, 126));		
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(qbm.binarySearchPlayer(200002));
		System.out.println(qbm.searchUniqueParameter(1,123,2));
		
		try {
			System.out.println(qbm.deletePlayer(200002));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(qbm.binarySearchPlayer(200002));
		System.out.println(qbm.searchUniqueParameter(1,123,2));
		double elapsedTime = System.nanoTime() - start;
		double elapsedTimeFinal = elapsedTime /1000000000;
		System.out.println(elapsedTimeFinal);
	}

}