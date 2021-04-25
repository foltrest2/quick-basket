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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
	private ChoiceBox<?> otherOperationsOp;
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
	private TableView<?> playersTable;
	@FXML
	private TableColumn<?, ?> idC;
	@FXML
	private TableColumn<?, ?> fullnameC;
	@FXML
	private TableColumn<?, ?> AgeC;
	@FXML
	private TableColumn<?, ?> teamC;
	@FXML
	private TableColumn<?, ?> pointspergameC;
	@FXML
	private TableColumn<?, ?> reboundsC;
	@FXML
	private TableColumn<?, ?> assistsC;
	@FXML
	private TableColumn<?, ?> robberiesC;
	@FXML
	private TableColumn<?, ?> blocksC;
	@FXML
	private TableColumn<?, ?> generalEC;


	public QuickBasketGUI(QuickBasketManager q) {
		qbm = q;

	}

	public void loadMainMenu() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
		fxmlLoader.setController(this);
		Parent mainMenu = fxmlLoader.load();
		basePane.setCenter(mainMenu);
		setNotVisible();


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

	public void consultOperation() {

		double k = Double.parseDouble(referencenumbertxt.getText());
		List<Player> players = qbm.searchUniqueParameter(consultOptions.getSelectionModel().getSelectedIndex(),k, consulttype.getSelectionModel().getSelectedIndex());


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
		consulttype.setItems(options);	
	}


	public void initializechoiceboxconsulttype() {	
		ArrayList<String> op = new ArrayList<>();
		op.add("=");
		op.add("<");
		op.add(">");
		ObservableList<String> options = FXCollections.observableList(op);
		consulttype.setItems(options);	

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

	@FXML
	void addPlayer(ActionEvent event) {

	}

	@FXML
	void showResult1(ActionEvent event) {

	}
	@FXML
	void returnP(ActionEvent event) {

	}


	@FXML
	void toChooseConsultType(MouseEvent event) {

	}

	@FXML
	void toChooseOperationType(MouseEvent event) {

	}


	@FXML
	void toDeletePlayer(ActionEvent event) {

	}


}
