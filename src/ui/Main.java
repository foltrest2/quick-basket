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
		try {
			double start = System.nanoTime();    
			qbm.importData();
			double elapsedTime = System.nanoTime() - start;
			double elapsedTimeFinal = elapsedTime /1000000000;
			System.out.println(elapsedTimeFinal);
		} catch (IOException e) {	
			e.printStackTrace();
		}
		
		//		System.out.println(qbm.checkImport());
	}

}
