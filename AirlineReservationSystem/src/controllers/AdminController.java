package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;
import javafx.application.Platform;
import application.Main;
import dao.AdminProfileUpdateDao;
import dao.AdminHistoryDao;
import dao.UserProfileUpdateDao;
import dao.UserProfileViewDao;
import dao.FlightsSearchDao;
import dao.TicketBookDao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.collections.*;
import models.AdminProfileModel;
import models.UserProfileModel;
import models.FlightSearchModel;
import models.HistoryModel;
import models.TicketBookModel;
import models.LoginModel;

public class AdminController implements Initializable {

	@FXML
	private Pane pane1;
	@FXML
	private Pane pane2;
	@FXML
	private Pane pane3;
	@FXML
	private Pane pane4;
	@FXML
	private Pane pane5;
	@FXML
	private TextField txtFname;
	@FXML
	private TextField txtLname;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtPhone;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtCity;
	@FXML
	private TextField txtState;
	@FXML
	private TextField txtZipcode;
	@FXML
	private DatePicker txtDob;
	@FXML
	private TextField atxtFname;
	@FXML
	private TextField atxtLname;
	@FXML
	private TextField atxtEmail;
	@FXML
	private TextField atxtPhone;
	@FXML
	private TextField atxtAddress;
	@FXML
	private TextField atxtCity;
	@FXML
	private TextField atxtState;
	@FXML
	private TextField atxtZipcode;
	@FXML
	private DatePicker atxtDob;
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
	@FXML
	private DatePicker Date;
	@FXML
	private TableView<FlightSearchModel> tblFlights;
	@FXML
	private TableColumn<FlightSearchModel, String> fromId;
	@FXML
	private TableColumn<FlightSearchModel, String> toId;
	@FXML
	private TableColumn<FlightSearchModel, String> dateId;
	@FXML
	private TableColumn<FlightSearchModel, String> timeId;
	@FXML
	private TableColumn<FlightSearchModel, String> classId;
	@FXML
	private TableColumn<FlightSearchModel, String> priceId;
	@FXML
	private TableView<HistoryModel> tblHistory;
	@FXML
	private TableColumn<HistoryModel, String> LNameId;
	@FXML
	private TableColumn<HistoryModel, String> FromId;
	@FXML
	private TableColumn<HistoryModel, String> ToId;
	@FXML
	private TableColumn<HistoryModel, String> DateId;
	@FXML
	private TableColumn<HistoryModel, String> TimeId;
	@FXML
	private TableColumn<HistoryModel, String> ClassId;
	@FXML
	private TableColumn<HistoryModel, String> BookId;
	@FXML
	private Button bookBtn;
	@FXML
	private Button deleteBtn;
	@FXML
	private Button createBtn;

	private static String first_name;
	private static String last_name;
	private static String email;
	private static long phone;
	private static String F_FROM;
	private static String F_TO;
	private static String F_DATE;
	private static String F_TIME;
	private static String F_CLASS;
	private static String F_PRICE;
	private static String T_FROM;
	private static String T_TO;
	private static String T_DATE;
	private static String T_TIME;
	private static String T_CLASS;
	private static String T_BOOKID;
	private static String T_last_name;

	static UserProfileModel c = new UserProfileModel();
	static String user_name = c.gettxtUsername();

	final ObservableList<String> UserTypeL = FXCollections.observableArrayList("User", "Admin");
	final ObservableList<String> FromL = FXCollections.observableArrayList("Chicago", "New York", "Seattle", "Orlando",
			"Dallas", "California");
	final ObservableList<String> ToL = FXCollections.observableArrayList("Chicago", "New York", "Seattle", "Orlando",
			"Dallas", "California");
	final ObservableList<String> ClassL = FXCollections.observableArrayList("Economy", "Premium Economy", "Business",
			"First Class");

	public void initialize(URL location, ResourceBundle resources) {
		pane5.setVisible(false);
		UserType.setValue("User");
		UserType.setItems(UserTypeL);
		From.setItems(FromL);
		From.setValue("Chicago");
		To.setItems(ToL);
		To.setValue("Orlando");
		Class.setItems(ClassL);
		Class.setValue("Economy");
		bookBtn.setDisable(true);
		// System.out.println(user_name);
		// Code for tblFlights table view
		fromId.setCellValueFactory(new PropertyValueFactory<FlightSearchModel, String>("fromId"));
		toId.setCellValueFactory(new PropertyValueFactory<FlightSearchModel, String>("toId"));
		dateId.setCellValueFactory(new PropertyValueFactory<FlightSearchModel, String>("dateId"));
		timeId.setCellValueFactory(new PropertyValueFactory<FlightSearchModel, String>("timeId"));
		classId.setCellValueFactory(new PropertyValueFactory<FlightSearchModel, String>("classId"));
		priceId.setCellValueFactory(new PropertyValueFactory<FlightSearchModel, String>("priceId"));

		// auto adjust width of columns depending on their content
		tblFlights.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(tblFlights));

		tblFlights.setVisible(false); // set invisible initially

		// Code for tblHistory table view
		LNameId.setCellValueFactory(new PropertyValueFactory<HistoryModel, String>("LNameId"));
		FromId.setCellValueFactory(new PropertyValueFactory<HistoryModel, String>("FromId"));
		ToId.setCellValueFactory(new PropertyValueFactory<HistoryModel, String>("ToId"));
		DateId.setCellValueFactory(new PropertyValueFactory<HistoryModel, String>("DateId"));
		TimeId.setCellValueFactory(new PropertyValueFactory<HistoryModel, String>("TimeId"));
		ClassId.setCellValueFactory(new PropertyValueFactory<HistoryModel, String>("ClassId"));
		BookId.setCellValueFactory(new PropertyValueFactory<HistoryModel, String>("BookId"));

		// auto adjust width of columns depending on their content
		tblHistory.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(tblHistory));
		tblHistory.setVisible(false); // set invisible initially
	}

	public void customResize(TableView<?> view) {

		AtomicLong width = new AtomicLong();
		view.getColumns().forEach(col -> {
			width.addAndGet((long) col.getWidth());
		});
		double tableWidth = view.getWidth();

		if (tableWidth > width.get()) {
			view.getColumns().forEach(col -> {
				col.setPrefWidth(col.getWidth() + ((tableWidth - width.get()) / view.getColumns().size()));
			});
		}
	}

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
		pane5.setVisible(false);
	}

	public void addadminuser() {
		pane4.setVisible(false);
		pane3.setVisible(false);
		pane2.setVisible(true);
		pane1.setVisible(false);
		pane5.setVisible(false);
		createBtn.setDisable(false);
		txtUsername.clear();
		txtPassword.clear();
		txtLname.clear();
		txtFname.clear();
		txtAddress.clear();
		txtPhone.clear();
		txtEmail.clear();
		txtState.clear();
		txtCity.clear();
		txtZipcode.clear();
		txtDob.setValue(null);
		// UserType.clearSelection(null);
	}

	public void viewprofile() {
		pane4.setVisible(false);
		pane3.setVisible(false);
		pane2.setVisible(false);
		pane1.setVisible(true);
		pane5.setVisible(false);
		System.out.println(user_name);

		// Create a DAO instance of the mode

		AdminProfileUpdateDao AdminDao = new AdminProfileUpdateDao();
		ArrayList<AdminProfileModel> arrayList = AdminDao.getCustomer(user_name);

		for (AdminProfileModel admin : arrayList) {
			System.out.println("setting the values");
			atxtLname.setText(admin.getatxtLname());
			atxtFname.setText(admin.getatxtFname());
			atxtDob.setValue(admin.getatxtDob());
			atxtEmail.setText(admin.getatxtEmail());
			atxtPhone.setText(Long.toString(admin.getatxtPhone()));
			atxtAddress.setText(admin.getatxtAddress());
			atxtCity.setText(admin.getatxtCity());
			atxtState.setText(admin.getatxtState());
			atxtZipcode.setText(admin.getatxtZipcode());
		}
	}

	public void viewhistory() {
		pane4.setVisible(false);
		pane3.setVisible(true);
		pane2.setVisible(false);
		pane1.setVisible(false);
		pane5.setVisible(false);
		deleteBtn.setDisable(true);

		// Create data access instance for History data access object
		AdminHistoryDao AH1 = new AdminHistoryDao();

		try {
			tblHistory.getItems().setAll(AH1.getHistory());
			if (tblHistory.getItems().isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Alert Message");
				alert.setHeaderText("History Info");
				alert.setContentText("No Ticket History");
				alert.showAndWait();
			} else
				tblHistory.setVisible(true);

		} catch (Exception e) {
			System.out.println("Error while printing ticket history " + e.getMessage());
		}
	}

	@FXML
	public void click2(MouseEvent event) {
		if (event.getClickCount() == 2) // Checking double click
		{
			deleteBtn.setDisable(false);
			System.out.println("Deleting selected record");
			T_last_name = tblHistory.getSelectionModel().getSelectedItem().getLNameId();
			T_FROM = tblHistory.getSelectionModel().getSelectedItem().getFromId();
			T_TO = tblHistory.getSelectionModel().getSelectedItem().getToId();
			T_DATE = tblHistory.getSelectionModel().getSelectedItem().getDateId();
			T_TIME = tblHistory.getSelectionModel().getSelectedItem().getTimeId();
			T_CLASS = tblHistory.getSelectionModel().getSelectedItem().getClassId();
			T_BOOKID = tblHistory.getSelectionModel().getSelectedItem().getBookId();

		}
	}

	public void delete() {
		pane4.setVisible(false);
		pane3.setVisible(true);
		pane2.setVisible(false);
		pane1.setVisible(false);
		pane5.setVisible(false);
		deleteBtn.setDisable(false);

		// Create data access instance to delete ticket
		TicketBookDao T = new TicketBookDao();

		try {
			System.out.println(T_last_name);
			System.out.println(T_FROM);
			System.out.println(T_TO);
			System.out.println(T_DATE);
			System.out.println(T_TIME);
			System.out.println(T_CLASS);
			System.out.println(T_BOOKID);

			T.deleteTicket(T_last_name, T_FROM, T_TO, T_DATE, T_TIME, T_CLASS, T_BOOKID);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert Message");
			alert.setHeaderText("Delete Info");
			alert.setContentText("Ticket has been deleted Successfully!!");
			alert.showAndWait();
			viewhistory();
		} catch (Exception e) {
			System.out.println("Error while deleting the ticket" + e.getMessage());
		}

	}

	public void book() {
		try {
			TextInputDialog dialog = new TextInputDialog("Enter numbers");
			dialog.setTitle("Payment Details");
			dialog.setHeaderText("Debit/Credit Card");
			dialog.setContentText("Please enter your card number");
			Optional<String> cardno = dialog.showAndWait();
			String card = cardno.get();
			if (cardno.isPresent() && !cardno.get().isEmpty() && (card.matches("\\d*"))) {
				System.out.println("Card number entered: " + card);
				TicketBookDao T1 = new TicketBookDao();

				try {
					ArrayList<TicketBookModel> arrayList = T1.getCustomer(user_name);

					for (TicketBookModel ticket : arrayList) {

						last_name = ticket.getLast_name();
						first_name = ticket.getFirst_name();
						email = ticket.getEmail();
						phone = Long.parseLong(ticket.getPhone());

					}

					System.out.println(last_name);
					System.out.println(first_name);
					System.out.println(email);
					System.out.println(phone);
					System.out.println(F_FROM);
					System.out.println(F_TO);
					System.out.println(F_DATE);
					System.out.println(F_TIME);
					System.out.println(F_CLASS);
					System.out.println(F_PRICE);

					T1.BookTicket(user_name, last_name, first_name, email, phone, F_FROM, F_TO, F_DATE, F_TIME, F_CLASS,
							F_PRICE);

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					System.out.println("Ticket Booked successfully!");
					alert.setHeaderText("Success message");
					alert.setContentText("Ticket Booked successfully!");
					alert.showAndWait();

				} catch (Exception e) {
					System.out.println("Not able to book the ticket" + e.getMessage());
				}

				try {
					AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/TicketView.fxml"));
					System.out.println("Launched Ticket Details Screen");
					Scene scene = new Scene(root, 800, 600);
					Main.stage.setScene(scene);
					Main.stage.setTitle("Ticket Details");
					Main.stage.show();
					TicketController.setUsername(user_name);
				} catch (Exception e) {
					System.out.println("Error in inflating view: " + e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Error in booking ticket: " + e.getMessage());
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
		System.out.println(CITY);
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
		UserProfileModel customer = new UserProfileModel();
		// Create User Object
		LoginModel user = new LoginModel();

		// Set the values from the view
		customer.settxtUsername(USERNAME);
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
		UserProfileUpdateDao C1 = new UserProfileUpdateDao();
		C1.CreateDetails(customer);
		C1.CreateUser(user);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message to Admin");
		alert.setHeaderText("Success Message!");
		System.out.println("User Created Successfully!");
		alert.setContentText("User Created Successfully!");
		alert.showAndWait();
		createBtn.setDisable(true);
	}

	public void update() {
		// Extract the data from text fields

		// Validate the data
		String LNAME = this.atxtLname.getText();
		if (LNAME == null || LNAME.trim().equals("")) {
			return;
		}

		String FNAME = this.atxtFname.getText();
		if (FNAME == null || FNAME.trim().equals("")) {
			return;
		}

		LocalDate DOB = this.atxtDob.getValue();
		if (DOB == null) {
			return;
		}

		String EMAIL = this.atxtEmail.getText();
		if (EMAIL == null || EMAIL.trim().equals("")) {
			return;
		}

		String PHONE = this.atxtPhone.getText();
		String ADDRESS = this.atxtAddress.getText();
		String CITY = this.atxtCity.getText();
		String STATE = this.atxtState.getText();
		String ZIPCODE = this.atxtZipcode.getText();

		// Create a Customer object to set the values
		UserProfileModel customer = new UserProfileModel();

		customer.settxtLname(LNAME);
		customer.settxtFname(FNAME);
		customer.settxtDob(DOB);
		customer.settxtEmail(EMAIL);
		customer.settxtPhone(Long.parseLong(PHONE));
		customer.settxtAddress(ADDRESS);
		customer.settxtCity(CITY);
		customer.settxtState(STATE);
		customer.settxtZipcode(ZIPCODE);

		// Create data access instance for customerView object
		UserProfileViewDao c1 = new UserProfileViewDao();
		c1.update(user_name, customer);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		System.out.println("Profile updated successfully!");
		alert.setHeaderText("Success message");
		alert.setContentText("Profile updated successfully!");
		alert.showAndWait();
	}

	public void search() {
		pane5.setVisible(true);
		bookBtn.setDisable(true);

		String F_FROM = this.From.getValue();
		String F_TO = this.To.getValue();
		String F_DATE = this.Date.getValue().toString();
		String F_CLASS = this.Class.getValue();

		// Create data access instance for Flights object
		FlightsSearchDao F1 = new FlightsSearchDao();

		try {
			tblFlights.getItems().setAll(F1.getFlights(F_FROM, F_TO, F_DATE, F_CLASS));
			if (tblFlights.getItems().isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Alert Message");
				alert.setHeaderText("Flights Info");
				alert.setContentText("No flights available for the search");
				alert.showAndWait();
			} else
				tblFlights.setVisible(true);

		} catch (Exception e) {
			System.out.println("No flights available for the search" + e.getMessage());
		}

	}

	@FXML
	public void click1(MouseEvent event) {
		if (event.getClickCount() == 2) // Checking double click
		{
			bookBtn.setDisable(false);
			System.out.println("Fetching data from the view");
			F_FROM = tblFlights.getSelectionModel().getSelectedItem().getFromId();
			F_TO = tblFlights.getSelectionModel().getSelectedItem().getToId();
			F_DATE = tblFlights.getSelectionModel().getSelectedItem().getDateId();
			F_TIME = tblFlights.getSelectionModel().getSelectedItem().getTimeId();
			F_CLASS = tblFlights.getSelectionModel().getSelectedItem().getClassId();
			F_PRICE = tblFlights.getSelectionModel().getSelectedItem().getPriceId();

		}
	}

}