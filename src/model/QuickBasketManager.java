package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dataStructures.*;

public class QuickBasketManager {

	private GenericBinarySearchTree<Double,Player> BSTPointsPerGame;
	private GenericAVLTree<Double,Player> AVLReboundsPerGame;
	private GenericAVLTree<Double,Player> AVLAssistPerGame;
	private GenericAVLTree<Double,Player> AVLRobberiesPerGame;
	private GenericAVLTree<Double,Player> AVLBlocksPerGame;
	private GenericRedBlackTree<Double,Player> RedBlackGeneralEvaluation;
	private ArrayList<Player> playersList;
	private final static String SEPARATOR = ",";
	public final static String SAVE_PATH_FILE = "data/FBAdata.csv";

	public QuickBasketManager() {
		BSTPointsPerGame = new GenericBinarySearchTree<>();
		AVLReboundsPerGame = new GenericAVLTree<>();
		AVLAssistPerGame  = new GenericAVLTree<>();
		AVLRobberiesPerGame = new GenericAVLTree<>();
		AVLBlocksPerGame = new GenericAVLTree<>();
		playersList = new ArrayList<>();
		RedBlackGeneralEvaluation = new GenericRedBlackTree<>();
	}


	public List<Player> searchUniqueParameter(int option, double key, int searchGreaterThan){
		ArrayList<Player> found = new ArrayList<>();
		List<Player> found2 = new ArrayList<>();
		switch (option) {
		case 1:
			//Search by pointsPerGame
			found = BSTPointsPerGame.search(key);
			break;

		case 2:
			//Search by reboundsPerGame
			found = AVLReboundsPerGame.search(key);
			break;

		case 3:
			found = AVLAssistPerGame.search(key);
			break;
		case 4:
			found = AVLRobberiesPerGame.search(key);
			break;
		case 5:
			found = AVLBlocksPerGame.search(key);
			break;

		case 6:
			switch (searchGreaterThan) {
			case 0:
				//Search by generalEvaluation blackRed Tree
				
				

				break;
			case 1:
				//Search by generalEvaluation blackRed Tree
				found2 = RedBlackGeneralEvaluation.getGreaterThan(key,new Integer(100000000));
				

				break;
			case 2:
				//Search by generalEvaluation blackRed Tree

				

				break;
			default:
			}

		case 7:
			switch (searchGreaterThan) {
			case 0:
				//Search by generalEvaluation player list lineal
				for (int i = 0; i < playersList.size(); i++) {
					if(playersList.get(i).getAge() <= key) {
						found.add(playersList.get(i));
					}
				}

				break;
			case 1:
				//Search by generalEvaluation player list lineal
				for (int i = 0; i < playersList.size(); i++) {
					if(playersList.get(i).getAge() >= key) {
						found.add(playersList.get(i));
					}
				}

				break;
			case 2:
				//Search by generalEvaluation player list lineal
				for (int i = 0; i < playersList.size(); i++) {
					if(playersList.get(i).getAge() == key) {
						found.add(playersList.get(i));
					}
				}

				break;
			default:
			}	
			
			break;
		default:
			break;
		}
		return found2;
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
			BSTPointsPerGame.put(pointsPerGame, toAdd);
			AVLReboundsPerGame.insert(reboundsPerGame, toAdd);
			AVLAssistPerGame.insert(assistsPerGame, toAdd);
			AVLRobberiesPerGame.insert(robberiesPerGame, toAdd);
			AVLBlocksPerGame.insert(blocksPerGame, toAdd);
			RedBlackGeneralEvaluation.insert(generalEvaluation, toAdd);
			playersList.add(toAdd);
			line = br.readLine();
		}
		br.close();
	}

	public String checkImport() {
		//		return firstxd.preOrder();
		//		return firstAVL.preOrder();
		return RedBlackGeneralEvaluation.preOrder();

	}
}
