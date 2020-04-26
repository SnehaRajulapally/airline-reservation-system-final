package controllers;

import java.time.LocalDate;
import java.util.Optional;

import application.Main;
import dao.CustomerDao;
import javafx.collections.FXCollections;
//import dao.CustomerDao;
//import dao.UpdateDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.collections.*; 
import models.Customer;
import models.User;


public class AdminController {

	@FXML
	private Pane pane1;
	@FXML
	private Pane pane2;
	@FXML
	private Pane pane3;
	@FXML
	private Pane pane4;
	@FXML
	private TextField txtLname;

	@FXML
	private TextField txtFname;

	@FXML
	private DatePicker txtDob;

	@FXML
	private TextField txtPhone;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtAddress;

	@FXML
	private TextField txtCity;

	@FXML
	private TextField txtState;

	@FXML
	private TextField txtZipcode;

	@FXML
	private TextField txtUsername;

	@FXML
	private TextField txtPassword;
	
	@FXML
	private ChoiceBox<String> UserType;
	@FXML
	private ChoiceBox<String> From;
	@FXML
	private ChoiceBox<String> To;
	@FXML
	private ChoiceBox<String> Class;
	

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
		System.out.println("Welcome Admin: " + user_name + "!");
	}

	public AdminController() {
	
	}
	
	

	public void bookticket() {
		pane4.setVisible(true);
		pane3.setVisible(false);
		pane2.setVisible(false);
		pane1.setVisible(false);
	}
	
	public void addadminuser() {
		pane4.setVisible(false);
		pane3.setVisible(false);
		pane2.setVisible(true);
		pane1.setVisible(false);
	}
 
	final ObservableList<String> UserTypeL = FXCollections.observableArrayList(
	        "User","Admin" );
	final ObservableList<String> FromL = FXCollections.observableArrayList(
			"Chicago", "New York", "Seattle", "Orlando", "Dallas", "California" );
	final ObservableList<String> ToL = FXCollections.observableArrayList(
			"Chicago", "New York", "Seattle", "Orlando", "Dallas", "California" );
	final ObservableList<String> ClassL = FXCollections.observableArrayList(
			"Economy","Premium Economy", "Business", "First Class" );
	@FXML
	private void initialize() {
		UserType.setValue("User");
		UserType.setItems(UserTypeL);
		From.setItems(FromL);
		From.setValue("Chicago");
		To.setItems(ToL);
		To.setValue("Orlando");
		Class.setItems(ClassL);
		Class.setValue("Economy");
	}
	
	public void viewprofile() {
		pane4.setVisible(false);
		pane3.setVisible(false);
		pane2.setVisible(false);
		pane1.setVisible(true);
		//UpdateDao U1 = new UpdateDao();
		//U1.FetchDetails();
	}

	public void viewhistory() {
		pane4.setVisible(false);
		pane3.setVisible(true);
		pane2.setVisible(false);
		pane1.setVisible(false);
	}

	public void book() {
		TextInputDialog dialog = new TextInputDialog("Enter payment details");
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

	
	public void create() {
		// Extract the data from the text fields of view
				String LNAME = this.txtLname.getText();
				if (LNAME == null || LNAME.trim().equals("")) {
					return;
				}
		
				String FNAME = this.txtFname.getText();
				if (FNAME == null || FNAME.trim().equals("")) {
					return;
				}
			
				LocalDate DOB = this.txtDob.getValue();
				if (DOB == null) {
					return;
				}
			
				String EMAIL = this.txtEmail.getText();
				if (EMAIL == null || EMAIL.trim().equals("")) {
					return;
				}
			
				String PHONE = this.txtPhone.getText();
				if (PHONE == null || PHONE.trim().equals("")) {
					return;
				}
	
				String ADDRESS = this.txtAddress.getText();
				if (ADDRESS == null || ADDRESS.trim().equals("")) {
					return;
				}
			
				String CITY = this.txtCity.getText();
				if (CITY == null || CITY.trim().equals("")) {
					return;
				}
	
				String STATE = this.txtState.getText();
				if (STATE == null || STATE.trim().equals("")) {
					return;
				}
		
				String ZIPCODE = this.txtZipcode.getText();
				if (ZIPCODE == null || ZIPCODE.trim().equals("")) {
					return;
				}
				
				String USERTYPE = (String) this.UserType.getValue();
				
			
				String USERNAME = this.txtUsername.getText();
				if (USERNAME == null || USERNAME.trim().equals("")) {
					return;
				}
			
				String PASSWORD = this.txtPassword.getText();
				if (PASSWORD == null || PASSWORD.trim().equals("")) {
					return;
				}
	
				
				// Create Customer Object
				Customer customer = new Customer();
				// Create User Object
				User user = new User();
	
				// Set the values from the view
				customer.settxtLname(LNAME);
				customer.settxtFname(FNAME);
				customer.settxtDob(DOB);
				customer.settxtEmail(EMAIL);
				customer.settxtPhone(Long.parseLong(PHONE));
				customer.settxtAddress(ADDRESS);
				customer.settxtCity(CITY);
				customer.settxtState(STATE);
				customer.settxtZipcode(ZIPCODE);
				user.settxtUsername(USERNAME);
				user.settxtPassword(PASSWORD);
                user.setUserType(USERTYPE);
				// Create data access instance for customer object
				CustomerDao C1 = new CustomerDao();
				C1.CreateDetails(customer);

				C1.CreateUser(user);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message to Admin");
				alert.setHeaderText("Success Message!");
				System.out.println("User Created Successfully!");
				alert.setContentText("User Created Successfully!");
				alert.showAndWait();
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

	public void search() {
	}


}