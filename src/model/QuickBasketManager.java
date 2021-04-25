package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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

	/**
	 * This is the QuickBasketManager constructor
	 */
	public QuickBasketManager() {
		BSTPointsPerGame = new GenericBinarySearchTree<>();
		AVLReboundsPerGame = new GenericAVLTree<>();
		AVLAssistPerGame  = new GenericAVLTree<>();
		AVLRobberiesPerGame = new GenericAVLTree<>();
		AVLBlocksPerGame = new GenericAVLTree<>();
		playersList = new ArrayList<>();
		RedBlackGeneralEvaluation = new GenericRedBlackTree<>();
	}
	/**
	 * This method searches a list of 
	 * @param option is the statistical item
	 * @param key it's the key of the node searched
	 * @param searchGreaterThan case of searching, among lower, higher and equals.
	 * @return a list of player whose comply what we looking for
	 */
	public List<Player> searchUniqueParameter(int option, double key, int searchGreaterThan){
		List<Player> found = new ArrayList<>();
		switch (option) {
		case 1:
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

		case 2:
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
		case 3:
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
		case 4:
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
		case 5:
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

		case 6:
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

		case 7:
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
	/**
	 * This method imports the data from the csv file and fill the trees with it
	 * @throws IOException
	 */
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
	/**
	 * This method exports the data of all the players as a csv file
	 * @throws FileNotFoundException
	 */
	public void exportData() throws FileNotFoundException  {
		PrintWriter pw = new PrintWriter(SAVE_PATH_FILE);
		pw.println("id"+SEPARATOR+"FullName"+SEPARATOR+"age"+SEPARATOR+"team"+SEPARATOR+"pointsPerGame"+SEPARATOR+"reboundsPerGame"+SEPARATOR+"assistsPerGame"+SEPARATOR+"robberiesPerGame"+SEPARATOR+"blocksPerGame"+SEPARATOR+"generalEvaluation");
		for (int i = 0; i < playersList.size(); i++)  {
			pw.println(playersList.get(i).getId()+SEPARATOR+playersList.get(i).getFullName()+SEPARATOR+playersList.get(i).getAge()+SEPARATOR+playersList.get(i).getTeam()+SEPARATOR+playersList.get(i).getPointsPerGame()+SEPARATOR+playersList.get(i).getReboundsPerGame()+SEPARATOR+playersList.get(i).getAssistsPerGame()+SEPARATOR+playersList.get(i).getRobberiesPerGame()+SEPARATOR+playersList.get(i).getBlocksPerGame()+SEPARATOR+playersList.get(i).getGeneralEvaluation());
		}
		pw.close();
	}
	/**
	 * This method deletes the data from all the trees
	 */
	public void reset() {
		BSTPointsPerGame.reset();
		AVLReboundsPerGame.reset();
		AVLAssistPerGame.reset();
		AVLRobberiesPerGame.reset();
		AVLBlocksPerGame.reset();
		playersList.clear();
		RedBlackGeneralEvaluation.reset();
	}
	/**
	 * This method adds a new player 
	 * @param id id of the player
	 * @param fullName full name of the player
	 * @param age age of the player
	 * @param team team of the player
	 * @param pointsPerGame points per game of the player
	 * @param reboundsPerGame rebounds per game of the player
	 * @param assistsPerGame assists per game of the player
	 * @param robberiesPerGame robberies per game of the player 
	 * @param blocksPerGame blocks per game of the player
	 * @param generalEvaluation general evaluation of the player
	 * @return a String saying if was added or not
	 * @throws IOException
	 */
	public String addNewPlayer(int id, String fullName, int age, String team, double pointsPerGame, double reboundsPerGame,
			double assistsPerGame, double robberiesPerGame, double blocksPerGame, double generalEvaluation) throws IOException {
		String info = "";
		if(!binarySearchPlayer(id)) {
			Player toAdd = new Player(id, fullName, age, team, pointsPerGame, reboundsPerGame, assistsPerGame, robberiesPerGame, blocksPerGame, generalEvaluation);
			playersList.add(toAdd);
			info += "Player added!";
			Collections.sort(playersList);
			exportData();
			reset();
			importData();
		} else {
			info += "Player already exist!";
		}	
		return info;		
	}
	/**
	 * This method deletes a player by his id
	 * @param id id of the player
	 * @return a String saying if was deleted or not
	 * @throws IOException
	 */
	public String deletePlayer(int id) throws IOException {
		int indexFound = 0;
		String info = "";
		if((indexFound = binarySearchPlayer2(id)) != -1) {
			playersList.remove(indexFound);
			info += "Player with id: "+id+" was deleted!";
			Collections.sort(playersList);
			exportData();
			reset();
			importData();
		} else {
			info += "Player does not exist!";
		}
		return info;
	}
	/**
	 * This method searches a player by his id
	 * @param id id of the player
	 * @return a boolean true if the player is in it, false if he is not
	 */
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

	/**
	 * This method searches a player by his id
	 * @param id id of the player
	 * @return a int that is the position of the player in the arraylist
	 */
	public int binarySearchPlayer2(int id) {
		boolean found = false;
		int index = 0;
		int start = 0;
		int end = playersList.size()-1;
		while (!found && start <= end) {
			int middle = (start + end)/2;
			if (playersList.get(middle).getId() == id) {
				found = true;
				index = middle;
			} else if((id - playersList.get(middle).getId()) < 1){
				end = middle -1;
			} else {
				start = middle +1;
			}			
		}
		if(found) {
			return index;
		} else {
			return -1;
		}
	}
	public List<Player> getPlayersListJunitTest() {
		return playersList;
	}
}