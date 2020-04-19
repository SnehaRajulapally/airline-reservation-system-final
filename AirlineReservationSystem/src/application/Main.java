package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Load the UI elements
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
			AnchorPane root =  (AnchorPane) loader.load();
			Scene scene = new Scene(root,600,400);
			primaryStage.setTitle("Illinois Tech Airways");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
