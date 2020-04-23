package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	public static Stage stage; 
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/HomeView.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,800,600);
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Illinois Tech Airways Home Page");
			System.out.println("Launched Illinois Tech Airways Home Screen");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
		    System.out.println("Error in inflating view: " + e);
			e.printStackTrace();	
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
