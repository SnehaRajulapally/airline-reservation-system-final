package controllers;

//import java.awt.TextField;
import java.util.Optional;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import dao.UpdateDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class UserController {

	@FXML
	private Pane pane1;
	@FXML
	private Pane pane2;
	@FXML
	private Pane pane3;
	
	@FXML
	private ChoiceBox<String> From;
	@FXML
	private ChoiceBox<String> To;
	@FXML
	private ChoiceBox<String> Class;

	/*
	 * private TextField txtFname; private TextField txtLname; private DatePicker
	 * txtDob; private TextField txtEmail; private TextField txtPhone; private
	 * TextField txtAdress; private TextField txtCity; private TextField txtState;
	 * private TextField txtZipcode;
	 */
	// DBConnect conn = null;
	// Statement stmt = null;
	static String user_name;

	public void logout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Thank you Message");
		alert.setHeaderText("Illinois Tech Airways");
		alert.setContentText("Thank you for choosing us! Please visit again!");
		alert.showAndWait();
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/HomeView.fxml"));
			Scene scene = new Scene(root, 800, 600);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Illinois Tech Airways Home Page");
			System.out.println("Launched Illinois Tech Airways Home Screen");
			Main.stage.show();
		} catch (Exception e) {
			System.out.println("Error in inflating view: " + e.getMessage());
		}
	}

	public static void setUsername(String username) {
		user_name = username;
		System.out.println("Welcome User: " + user_name + "!");
	}

	public UserController() {

	}
	
	public void viewprofile() {
		pane3.setVisible(false);
		pane2.setVisible(false);
		pane1.setVisible(true);
		//UpdateDao U1 = new UpdateDao();
		//U1.FetchDetails();
	}

	public void viewhistory() {
		pane3.setVisible(false);
		pane2.setVisible(true);
		pane1.setVisible(false);
	}

	public void bookticket() {
		pane3.setVisible(true);
		pane2.setVisible(false);
		pane1.setVisible(false);
	}
	
	final ObservableList<String> FromL = FXCollections.observableArrayList(
			"Chicago", "New York", "Seattle", "Orlando", "Dallas", "California" );
	final ObservableList<String> ToL = FXCollections.observableArrayList(
			"Chicago", "New York", "Seattle", "Orlando", "Dallas", "California" );
	final ObservableList<String> ClassL = FXCollections.observableArrayList(
			"Economy","Premium Economy", "Business", "First Class" );
	@FXML
	private void initialize() {
		From.setItems(FromL);
		From.setValue("Chicago");
		To.setItems(ToL);
		To.setValue("Orlando");
		Class.setItems(ClassL);
		Class.setValue("Economy");
	}
	
	public void search() {
	}

	public void update() {
		// Create data access instance for customer object
		//UpdateDao U2 = new UpdateDao();
		//U2.UpdateDetails();

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		System.out.println("Profile updated successfully!");
		alert.setHeaderText("Success message");
		alert.setContentText("Profile updated successfully!");
		alert.showAndWait();
	}

	public void book() {
		TextInputDialog dialog = new TextInputDialog("16 digit number");
		dialog.setTitle("Payment Details");
		dialog.setHeaderText("Debit/Credit Card");
		dialog.setContentText("Please enter your card number");

		Optional<String> cardno = dialog.showAndWait();
		if (cardno.isPresent()) {
			String cardnumber = cardno.get();
			System.out.println("Card number entered: " + cardnumber);
		}
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/TicketView.fxml"));
			System.out.println("Launched TicketDeatils Screen");
			Scene scene = new Scene(root, 800, 600);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Ticket Details");
			Main.stage.show();
		} catch (Exception e) {
			System.out.println("Error in inflating view: " + e.getMessage());
		}

	}

}
