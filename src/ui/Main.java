package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.QuickBasketManager;

public class Main extends Application {

	private QuickBasketGUI guiItem;
	private QuickBasketManager qb;

	public Main() {
		qb = new QuickBasketManager();
		guiItem = new QuickBasketGUI(qb);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("basePane.fxml"));
		fxmlLoader.setController(guiItem);
		Parent root = fxmlLoader.load();
		guiItem.loadMainMenu();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Quick Basket");
		primaryStage.show();
	}
}
