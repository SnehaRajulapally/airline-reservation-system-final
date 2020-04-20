package controllers;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class UserController {

	static String user_name;

	public void logout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Thank you Message");
		alert.setHeaderText("Illinois Tech Airways");
		alert.setContentText("Thank you for choosing us!Please visit again!");
		alert.showAndWait();
		 try {
				FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/LoginView.fxml"));
				AnchorPane root = (AnchorPane) loader.load();
				Scene scene = new Scene(root,800,600);
		        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		   Main.stage.setScene(scene);
		   Main.stage.setTitle("Illinois Tech Airways Login Page");
		   Main.stage.show();
		  } catch (Exception e) {
		  System.out.println("Error in inflating view: " + e.getMessage());
		  }
	}

	public static void setUsername(String username) {
		user_name = username;
		System.out.println("Welcome " + user_name);
	}

	public UserController() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Welcome Message");
		alert.setHeaderText("Illinois Tech Airways");
		alert.setContentText("Welcome User: " + user_name+"!");
		alert.showAndWait();
	}
}
