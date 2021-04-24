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
<<<<<<< HEAD
//		System.out.println(qbm.searchUniqueParameter(1,1));
=======
		
>>>>>>> 6216e170d107b2bdd847ec2ae16afe7d92ddfe33
		//		System.out.println(qbm.checkImport());
		System.out.println(qbm.searchUniqueParameter(2,500,0));
//		System.out.println(qbm.searchUniqueParameter(6,1));
	}

}
