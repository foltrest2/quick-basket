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

		//		System.out.println(qbm.searchUniqueParameter(1,1));
		System.out.println(qbm.checkImport());
		//		System.out.println(qbm.searchUniqueParameter(2,500,0));
		//		System.out.println(qbm.searchUniqueParameter(6,1));
		//		System.out.println(qbm.searchUniqueParameter(6,18,1));
		double elapsedTime = System.nanoTime() - start;
		double elapsedTimeFinal = elapsedTime /1000000000;
		System.out.println(elapsedTimeFinal);
	}

}
