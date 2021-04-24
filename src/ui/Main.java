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
			qbm.importData();
		} catch (IOException e) {	
			e.printStackTrace();
		}
		System.out.println(qbm.checkImport());
	}

}
