package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dataStructures.*;

public class QuickBasketManager {

	private GenericBinarySearchTree<Double,Player> BSTPointsPerGame;
	private GenericAVLTree<Double,Player> AVLReboundsPerGame;
	private GenericAVLTree<Double,Player> AVLAssistPerGame;
	private GenericAVLTree<Double,Player> AVLRobberiesPerGame;
	private GenericAVLTree<Double,Player> AVLBlocksPerGame;
	private GenericRedBlackTree<Double,Player> RedBlackGeneralEvaluation;
	private List<Player> playersList;
	private final static String SEPARATOR = ",";
	public final static String SAVE_PATH_FILE = "data/FBAdatav2.csv";

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
		List<Player> found = new ArrayList<>();
		switch (option) {
		case 0:
			//Seaarch by pointsPerGame BST tree
			switch (searchGreaterThan) {
			case 0:
				found = BSTPointsPerGame.getLowestThan(key, playersList.size());
				break;
			case 1:
				found = BSTPointsPerGame.getGreaterThan(key, playersList.size());
				break;
			case 2:
				found = BSTPointsPerGame.search(key);
				break;
			default:
				found = null;
				break;
			}
			break;

		case 1:
			//Search by ReboundsPerGame AVL tree
			switch (searchGreaterThan) {
			case 0:
				found = AVLReboundsPerGame.getLowestThan(key, playersList.size());
				break;
			case 1:
				found = AVLReboundsPerGame.getGreaterThan(key, playersList.size());
				break;
			case 2:
				found = AVLReboundsPerGame.search(key);
				break;
			default:
				found = null;
				break;
			}

			break;
		case 2:
			//Search by AssistPerGame AVL tree
			switch (searchGreaterThan) {
			case 0:
				found = AVLAssistPerGame.getLowestThan(key, playersList.size());
				break;
			case 1:
				found = AVLAssistPerGame.getGreaterThan(key, playersList.size());
				break;
			case 2:
				found = AVLAssistPerGame.search(key);
				break;
			default:
				found = null;
				break;
			}

			break;
		case 3:
			//Search by RobberiesPerGame AVL tree
			switch (searchGreaterThan) {
			case 0:
				found = AVLRobberiesPerGame.getLowestThan(key, playersList.size());
				break;
			case 1:
				found = AVLRobberiesPerGame.getGreaterThan(key, playersList.size());
				break;
			case 2:
				found = AVLRobberiesPerGame.search(key);
				break;
			default:
				found = null;
				break;
			}

			break;
		case 4:
			//Search by BlocksPerGame AVL tree
			switch (searchGreaterThan) {
			case 0:
				found = AVLBlocksPerGame.getLowestThan(key, playersList.size());
				break;
			case 1:
				found = AVLBlocksPerGame.getGreaterThan(key, playersList.size());
				break;
			case 2:
				found = AVLBlocksPerGame.search(key);
				break;
			default:
				found = null;
				break;
			}

			break;

		case 5:
			//Search by generalEvaluation blackRed Tree
			switch (searchGreaterThan) {
			case 0:				
				found = RedBlackGeneralEvaluation.getLowestThan(key, playersList.size());
				break;
			case 1:
				found = RedBlackGeneralEvaluation.getGreaterThan(key, playersList.size());
				break;
			case 2:
				found = RedBlackGeneralEvaluation.search(key);
				break;
			default:
				found = null;
			}

		case 6:
			//Search by player age on lineal list
			switch (searchGreaterThan) {
			case 0:
				for (int i = 0; i < playersList.size(); i++) {
					if(playersList.get(i).getAge() < key) {
						found.add(playersList.get(i));
					}
				}
				break;
			case 1:
				for (int i = 0; i < playersList.size(); i++) {
					if(playersList.get(i).getAge() > key) {
						found.add(playersList.get(i));
					}
				}
				break;
			case 2:
				for (int i = 0; i < playersList.size(); i++) {
					if(playersList.get(i).getAge() == key) {
						found.add(playersList.get(i));
					}
				}
				break;
			default:
				found = null;
			}	
			break;
		default:
			found = null;
			break;
		}
		return found;
	}

	public void importData() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(SAVE_PATH_FILE));
		br.readLine();
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			int id = Integer.parseInt(parts[0]);
			String fullName = parts[1];
			int age = Integer.parseInt(parts[2]);
			String team = parts[3];
			double pointsPerGame = Double.parseDouble(parts[4]);
			double reboundsPerGame = Double.parseDouble(parts[5]);
			double assistsPerGame = Double.parseDouble(parts[6]);
			double robberiesPerGame = Double.parseDouble(parts[7]);
			double blocksPerGame = Double.parseDouble(parts[8]);
			double generalEvaluation = Double.parseDouble(parts[9]);
			Player toAdd = new Player(id, fullName, age, team, pointsPerGame, reboundsPerGame, assistsPerGame, robberiesPerGame, blocksPerGame, generalEvaluation);
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

	public void reset() {
		BSTPointsPerGame = null;
		AVLReboundsPerGame = null;
		AVLAssistPerGame  = null;
		AVLRobberiesPerGame = null;
		AVLBlocksPerGame = null;
		playersList.clear();
		RedBlackGeneralEvaluation = null;
	}
	
	public String addNewPlayer(int id, String fullName, int age, String team, double pointsPerGame, double reboundsPerGame,
			double assistsPerGame, double robberiesPerGame, double blocksPerGame, double generalEvaluation) {
		String info = "";
		if(!binarySearchPlayer(id)) {
			Player toAdd = new Player(id, fullName, age, team, pointsPerGame, reboundsPerGame, assistsPerGame, robberiesPerGame, blocksPerGame, generalEvaluation);
			playersList.add(toAdd);
			info += "Player added!";
		} else {
			info += "Player already exist!";
		}	
		return info;		
	}
	public boolean binarySearchPlayer(int id) {
		boolean found = false;
		int start = 0;
		int end = playersList.size()-1;
		while (!found && start <= end) {
			int middle = (start + end)/2;
			if (playersList.get(middle).getId() == id) {
				found = true;
			} else if((id - playersList.get(middle).getId()) < 1){
				end = middle -1;
			} else {
				start = middle +1;
			}			
		}
		return found;
	}
	
	public String checkImport() {
//				return BSTPointsPerGame.preOrder();
//				return AVLReboundsPerGame.preOrder();
		return RedBlackGeneralEvaluation.preOrder();

	}
}