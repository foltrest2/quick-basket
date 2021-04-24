package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import dataStructures.*;

public class QuickBasketManager {


	private GenericBinarySearchTree<Double,Player> firstxd;
	private GenericAVLTree<Double,Player> firstAVL;
	private GenericAVLTree<Double,Player> secondAVL;
	private GenericAVLTree<Double,Player> thirdAVL;
	private GenericAVLTree<Double,Player> fourthAVL;
	private final static String SEPARATOR = ",";
	public final static String SAVE_PATH_FILE = "data/FBAdata.csv";



	public QuickBasketManager() {
		firstxd = new GenericBinarySearchTree<>();
		firstAVL = new GenericAVLTree<>();
		secondAVL  = new GenericAVLTree<>();
		thirdAVL = new GenericAVLTree<>();
		fourthAVL = new GenericAVLTree<>();
	}


	public void importData() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(SAVE_PATH_FILE));
		br.readLine();
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			String fullName = parts[0];
			int age = Integer.parseInt(parts[1]);
			String team = parts[2];
			double pointsPerGame = Double.parseDouble(parts[3]);
			double reboundsPerGame = Double.parseDouble(parts[4]);
			double assistsPerGame = Double.parseDouble(parts[5]);
			double robberiesPerGame = Double.parseDouble(parts[6]);
			double blocksPerGame = Double.parseDouble(parts[7]);
			double generalEvaluation = Double.parseDouble(parts[8]);
			Player toAdd = new Player(fullName, age, team, pointsPerGame, reboundsPerGame, assistsPerGame, robberiesPerGame, blocksPerGame, generalEvaluation);
			firstxd.put(pointsPerGame, toAdd);
			firstAVL.insert(reboundsPerGame, toAdd);
			secondAVL.insert(assistsPerGame, toAdd);
			thirdAVL.insert(robberiesPerGame, toAdd);
			fourthAVL.insert(blocksPerGame, toAdd);
			line = br.readLine();
		}
		br.close();
	}
	
	public String checkImport() {
		return firstxd.preOrder();
	}
}
