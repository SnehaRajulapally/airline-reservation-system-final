package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import models.LoginModel;


public class LoginController {
	
	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Label lblError;
	
	private LoginModel model;
    public LoginController() { model = new LoginModel(); }
    
  
	public void login() {

		lblError.setText("");
		String username = this.txtUsername.getText();
		String password = this.txtPassword.getText();

		if (username == null || username.trim().equals("")) {
			lblError.setText("Error: Invalid Username!");
			txtUsername.setText("");
			txtPassword.setText("");
			return;
		}
		if (password == null || password.trim().equals("")) {
			lblError.setText("Error: Invalid Password!");
			txtUsername.setText("");
			txtPassword.setText("");
			return;
		}
		if (username == null || username.trim().equals("") && (password == null || password.trim().equals(""))) {
			lblError.setText("Error: Spaces are not allowed. Please Re-enter!");
			txtUsername.setText("");
			txtPassword.setText("");
			return;
		}
				checkCredentials(username, password);
	}
	
	  public void cancel()
	    {
			lblError.setText("");
			txtUsername.setText("");
			txtPassword.setText("");
	    }

				public void checkCredentials(String username, String password) {
					Boolean isValid = model.getCredentials(username, password);
					if (!isValid) {
						lblError.setText("Error: Invalid User!");
						return;
					}
					try {
						AnchorPane root;
					 	AdminController.setUsername(username);
						UserController.setUsername(username);
						if (model.isAdmin() && isValid) {
			            root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/AdminView.fxml"));
							Main.stage.setTitle("Admin View");
			  			} else {
			            root = (AnchorPane)FXMLLoader.load(getClass().getResource("/views/UserView.fxml"));
						Main.stage.setTitle("User View");
						}
						Scene scene = new Scene(root);
						Main.stage.setScene(scene);
					} catch (Exception e) {
						System.out.println("Error in inflating view: " + e);
					}

				}
	}
	

