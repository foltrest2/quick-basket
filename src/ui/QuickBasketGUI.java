package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.Player;
import model.QuickBasketManager;

public class QuickBasketGUI {

	private QuickBasketManager qbm;
	@FXML
	private BorderPane basePane;
	@FXML
	private ComboBox<String> consultOptions;
	@FXML
	private TextField consulttypetxt;
	@FXML
	private Label consulttypelabel;
	@FXML
	private TextField referencenumbertxt;
	@FXML
	private ComboBox<String> consulttype;
	@FXML
	private Button showResultbtn;
	@FXML
	private ComboBox<String> otherOperationsOp;
	@FXML
	private Label labelopearation;
	@FXML
	private TextField idToAddTxt;
	@FXML
	private Label idtoaddlabel;
	@FXML
	private TextField pointspergametxt;
	@FXML
	private TextField reboundspergametxt;
	@FXML
	private TextField assistspergametxt;
	@FXML
	private TextField robberiespergametxt;
	@FXML
	private TextField fullNametxt;
	@FXML
	private TextField AgeTxt;
	@FXML
	private TextField blockspergametxt;
	@FXML
	private TextField teamtxt;
	@FXML
	private TextField generalevaluationtxt;
	@FXML
	private Label fullnamelabel;
	@FXML
	private Label agelabel;
	@FXML
	private Label teamlabel;
	@FXML
	private Label pointspergamelabel;
	@FXML
	private Label assistspergamelabel;
	@FXML
	private Label reboundspergamelabel;
	@FXML
	private Label robberiesPergamelabel;
	@FXML
	private Label blockspergamelabel;
	@FXML
	private Label generalevaluationlabel;
	@FXML
	private Button addBtn;
	@FXML
	private Button deleteBtn;
	@FXML
	private Label idToDeleteTxt;
	@FXML
	private TextField idToDelete;
	@FXML
	private TableView<Player> playersTable;
	@FXML
	private TableColumn<Player, Integer> idC;
	@FXML
	private TableColumn<Player, String> fullnameC;
	@FXML
	private TableColumn<Player, Integer> AgeC;
	@FXML
	private TableColumn<Player, String> teamC;
	@FXML
	private TableColumn<Player, Double> pointspergameC;
	@FXML
	private TableColumn<Player, Double> reboundsC;
	@FXML
	private TableColumn<Player, Double> assistsC;
	@FXML
	private TableColumn<Player, Double> robberiesC;
	@FXML
	private TableColumn<Player, Double> blocksC;
	@FXML
	private TableColumn<Player, Double> generalEC;

	public QuickBasketGUI(QuickBasketManager q) {
		qbm = q;

	}

	public void loadMainMenu() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
		fxmlLoader.setController(this);
		Parent mainMenu = fxmlLoader.load();
		basePane.setCenter(mainMenu);
		setNotVisible();
		initializechoiceboxconsulttype();
		initializechoiboxconsultoptions();
		initializechoiceboxotheroperationstype();

	}

	@FXML
	void toChooseCounsult(ActionEvent event) {

		referencenumbertxt.setVisible(true);
		consulttypelabel.setVisible(true);
		consulttype.setVisible(true);
		showResultbtn.setVisible(true);
		labelopearation.setVisible(false);
		otherOperationsOp.setVisible(false);
		pointspergametxt.setVisible(false);
		assistspergametxt.setVisible(false);
		robberiespergametxt.setVisible(false);
		reboundspergametxt.setVisible(false);
		idToAddTxt.setVisible(false);
		fullNametxt.setVisible(false);
		AgeTxt.setVisible(false);
		blockspergametxt.setVisible(false);
		teamtxt.setVisible(false);
		generalevaluationtxt.setVisible(false);
		fullnamelabel.setVisible(false);
		agelabel.setVisible(false);
		teamlabel.setVisible(false);
		pointspergamelabel.setVisible(false);
		assistspergamelabel.setVisible(false);
		reboundspergamelabel.setVisible(false);
		robberiesPergamelabel.setVisible(false);
		blockspergamelabel.setVisible(false);
		generalevaluationlabel.setVisible(false);
		idtoaddlabel.setVisible(false);
		addBtn.setVisible(false);
		deleteBtn.setVisible(false);
		idToDeleteTxt.setVisible(false);
		idToDelete.setVisible(false);
		consultOptions.setVisible(true);

	}

	public void setNotVisible() {

		consultOptions.setVisible(false);
		consulttypelabel.setVisible(false);
		showResultbtn.setVisible(false);
		idToAddTxt.setVisible(false);
		pointspergametxt.setVisible(false);
		assistspergametxt.setVisible(false);
		robberiespergametxt.setVisible(false);
		reboundspergametxt.setVisible(false);
		fullNametxt.setVisible(false);
		AgeTxt.setVisible(false);
		blockspergametxt.setVisible(false);
		teamtxt.setVisible(false);
		generalevaluationtxt.setVisible(false);
		fullnamelabel.setVisible(false);
		agelabel.setVisible(false);
		teamlabel.setVisible(false);
		pointspergamelabel.setVisible(false);
		assistspergamelabel.setVisible(false);
		reboundspergamelabel.setVisible(false);
		robberiesPergamelabel.setVisible(false);
		blockspergamelabel.setVisible(false);
		generalevaluationlabel.setVisible(false);
		idtoaddlabel.setVisible(false);
		addBtn.setVisible(false);
		deleteBtn.setVisible(false);
		idToDeleteTxt.setVisible(false);
		idToDelete.setVisible(false);
		referencenumbertxt.setVisible(false);
		consulttype.setVisible(false);
		labelopearation.setVisible(false);
		otherOperationsOp.setVisible(false);

	}

	public List<Player> consultOperation() {

		double k = Double.parseDouble(referencenumbertxt.getText());
		double start = System.nanoTime();
		List<Player> returned = qbm.searchUniqueParameter(consultOptions.getSelectionModel().getSelectedIndex(),k, consulttype.getSelectionModel().getSelectedIndex());
		double elapsedTime = System.nanoTime() - start;
		double elapsedTimeFinal = elapsedTime /1000000000;
		Alert alert= new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Consult Time");
		alert.setContentText("Consult time was " +elapsedTimeFinal+ " seconds");
		alert.showAndWait();

		return returned;

	}

	public void initializechoiboxconsultoptions() {
		ArrayList<String> op = new ArrayList<>();
		op.add("Points per game");
		op.add("Rebounds per game");
		op.add("Assists per game");
		op.add("Robberies per game");
		op.add("Blocks per game");
		op.add("General Evaluation");
		op.add("Age");
		ObservableList<String> options = FXCollections.observableList(op);
		consultOptions.setItems(options);	
	}


	public void initializechoiceboxconsulttype() {	
		ArrayList<String> op = new ArrayList<>();
		op.add("Lower than");
		op.add("Greater than");
		op.add("Equals to");
		ObservableList<String> options = FXCollections.observableList(op);
		consulttype.setItems(options);	

	}

	public void loadPlayerScreen() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showPlayers.fxml"));
		fxmlLoader.setController(this);
		Parent pscreen = fxmlLoader.load();
		initializeplayersTable();
		basePane.setCenter(pscreen);

	}

	@FXML
	void showResult1(ActionEvent event) throws IOException {

		loadPlayerScreen();

	}

	public void initializeplayersTable() {

		ObservableList <Player> oblist;
		oblist = FXCollections.observableList(consultOperation());
		playersTable.setItems(oblist);
		idC.setCellValueFactory(new PropertyValueFactory<Player,Integer>("id"));
		fullnameC.setCellValueFactory(new PropertyValueFactory<Player,String>("fullName"));
		AgeC.setCellValueFactory(new PropertyValueFactory<Player,Integer>("age"));
		teamC.setCellValueFactory(new PropertyValueFactory<Player,String>("team"));
		pointspergameC.setCellValueFactory(new PropertyValueFactory<Player,Double>("pointsPerGame"));
		reboundsC.setCellValueFactory(new PropertyValueFactory<Player,Double>("reboundsPerGame"));
		assistsC.setCellValueFactory(new PropertyValueFactory<Player,Double>("assistsPerGame"));
		robberiesC.setCellValueFactory(new PropertyValueFactory<Player,Double>("robberiesPerGame"));
		blocksC.setCellValueFactory(new PropertyValueFactory<Player,Double>("blocksPerGame"));
		generalEC.setCellValueFactory(new PropertyValueFactory<Player,Double>("generalEvaluation"));

	}

	@FXML
	void toChooseOtherOperations(ActionEvent event) {
		referencenumbertxt.setVisible(false);
		consultOptions.setVisible(false);
		consulttypelabel.setVisible(false);
		consulttype.setVisible(false);
		showResultbtn.setVisible(false);
		labelopearation.setVisible(true);
		otherOperationsOp.setVisible(true);

	}

	public void initializechoiceboxotheroperationstype() {	
		ArrayList<String> op = new ArrayList<>();
		op.add("Add player option");
		op.add("Delete player option");
		ObservableList<String> options = FXCollections.observableList(op);
		otherOperationsOp.setItems(options);	

	}	
	@FXML
	void checkOtherOp(ActionEvent event) {

		showOtherOperationsMenu();

	}

	public void showOtherOperationsMenu() {

		if(otherOperationsOp.getSelectionModel().getSelectedIndex() == 0) {

			idToAddTxt.setVisible(true);
			pointspergametxt.setVisible(true);
			assistspergametxt.setVisible(true);
			robberiespergametxt.setVisible(true);
			reboundspergametxt.setVisible(true);
			fullNametxt.setVisible(true);
			AgeTxt.setVisible(true);
			blockspergametxt.setVisible(true);
			teamtxt.setVisible(true);
			generalevaluationtxt.setVisible(true);
			fullnamelabel.setVisible(true);
			agelabel.setVisible(true);
			teamlabel.setVisible(true);
			pointspergamelabel.setVisible(true);
			assistspergamelabel.setVisible(true);
			reboundspergamelabel.setVisible(true);
			robberiesPergamelabel.setVisible(true);
			blockspergamelabel.setVisible(true);
			generalevaluationlabel.setVisible(true);
			idtoaddlabel.setVisible(true);
			addBtn.setVisible(true);
			deleteBtn.setVisible(false);
			idToDeleteTxt.setVisible(false);
			idToDelete.setVisible(false);
		}else if(otherOperationsOp.getSelectionModel().getSelectedIndex() == 1) {

			deleteBtn.setVisible(true);
			idToDeleteTxt.setVisible(true);
			idToDelete.setVisible(true);
			idToAddTxt.setVisible(false);
			pointspergametxt.setVisible(false);
			assistspergametxt.setVisible(false);
			robberiespergametxt.setVisible(false);
			reboundspergametxt.setVisible(false);
			fullNametxt.setVisible(false);
			AgeTxt.setVisible(false);
			blockspergametxt.setVisible(false);
			teamtxt.setVisible(false);
			generalevaluationtxt.setVisible(false);
			fullnamelabel.setVisible(false);
			agelabel.setVisible(false);
			teamlabel.setVisible(false);
			pointspergamelabel.setVisible(false);
			assistspergamelabel.setVisible(false);
			reboundspergamelabel.setVisible(false);
			robberiesPergamelabel.setVisible(false);
			blockspergamelabel.setVisible(false);
			generalevaluationlabel.setVisible(false);
			idtoaddlabel.setVisible(false);
			addBtn.setVisible(false);

		}

	}

	@FXML
	void addPlayer(ActionEvent event) {

		int id = Integer.parseInt(idToAddTxt.getText());
		Double pointspg = Double.parseDouble(pointspergametxt.getText());
		Double assistspg = Double.parseDouble(assistspergametxt.getText());
		Double robberiespg =  Double.parseDouble(robberiespergametxt.getText());
		Double reboundspg = Double.parseDouble(reboundspergametxt.getText());
		Double blockspg = Double.parseDouble(blockspergametxt.getText());
		String fullname = fullNametxt.getText();
		int age = Integer.parseInt(AgeTxt.getText());
		String pteam = teamtxt.getText();
		Double generalevaluation = Double.parseDouble(generalevaluationtxt.getText());
		String info = "";

		try {
			info = qbm.addNewPlayer(id, fullname, age, pteam, pointspg, reboundspg, assistspg, robberiespg, blockspg, generalevaluation);
		} catch (IOException e) {

			e.printStackTrace();

		}
		if(info.equalsIgnoreCase("Player already exist!")) {

			showAlertWhenIdAlreadyExists();
		}else {

			showAlertWhenPlayerIsAdded();
		}

		clearTextAdd();	
	}

	@FXML
	void returnP(ActionEvent event) throws IOException {

		loadMainMenu();

	}

	@FXML
	void toDeletePlayer(ActionEvent event) {

		int idToD = Integer.parseInt(idToDelete.getText());
		String q = "";

		try {
			q = qbm.deletePlayer(idToD);
		} catch (IOException e) {

			e.printStackTrace();
		}

		if(q.equalsIgnoreCase("Player does not exist!")) {

			showAlertWhenPlayerDoesntExist();
		}else {

			showAlertWhenPlayerIsDeleted();
		}

		clearTextDelete();
	}

	public void showAlertWhenIdAlreadyExists() {

		Alert alert= new Alert(AlertType.ERROR);
		alert.setHeaderText("INVALID ID ERROR");
		alert.setContentText("That id is already used by another player");
		alert.showAndWait();

	}

	public void showAlertWhenPlayerDoesntExist() {

		Alert alert= new Alert(AlertType.ERROR);
		alert.setHeaderText("PLAYER DOESN´T EXIST ERROR");
		alert.setContentText("The player you are trying to delete doesn´t exist in the database");
		alert.showAndWait();			
	}
	public void showAlertWhenPlayerIsAdded() {

		Alert alert= new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("PLAYER ADDED");
		alert.setContentText("Player was added succesfully");
		alert.showAndWait();			
	}
	public void showAlertWhenPlayerIsDeleted() {

		Alert alert= new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("PLAYER DELETED");
		alert.setContentText("Player was deleted succesfully");
		alert.showAndWait();			
	}

	public void clearTextAdd() {

		idToAddTxt.clear();
		pointspergametxt.clear();
		assistspergametxt.clear();
		robberiespergametxt.clear();
		reboundspergametxt.clear();
		fullNametxt.clear();
		AgeTxt.clear();
		blockspergametxt.clear();
		teamtxt.clear();
		generalevaluationtxt.clear();

	}

	public void clearTextDelete() {

		idToDelete.clear();

	}
}
