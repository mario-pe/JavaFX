package ScenesAndControllers;

import java.io.IOException;
import java.util.ArrayList;

import Model.User;
import ProjectUtils.OperationsOnFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationScreenController {
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtSecondName;
	@FXML
	private TextField txtMail;
	@FXML
	private TextField txtLogin;
	@FXML
	private TextField txtPassword;
	@FXML
	private TextField txtAge;
	@FXML private Label lblName;
	@FXML private Label lblSecondName;
	@FXML private Label lblMail;
	@FXML private Label lblLogin;
	@FXML private Label lblPassword;
	@FXML private Label lblAge;
	
	public void registrationUser(ActionEvent event) throws IOException {

		boolean booleanName = false;
		boolean booleanSecondName = false;
		boolean booleanMail = false;
		boolean booleanLogin = false;
		boolean booleanPassword= false;
		boolean booleanAge = false;

		String name = "";
		String secondName = "";
		String mail = "";
		String login = "";
		String password = "";
		int age = 1;

		if (!txtName.getText().equals("")) {
			name = txtName.getText();
			booleanName = true;
		} else lblName.setText("musisz podac imie");
		if (!txtSecondName.getText().equals("")) {
			secondName = txtSecondName.getText();
			booleanSecondName = true;
		} else lblSecondName.setText("musisz podac nazwisko");
		if (!txtMail.getText().equals("")) {
			mail = txtMail.getText();
			booleanMail = true;
		} else lblMail.setText("musisz podac mail");
		if (!txtLogin.getText().equals("")) {
			login = txtLogin.getText();
			booleanLogin = true;
		} else lblLogin.setText("musisz podac login");

		if(!txtPassword.getText().equals("")){password = txtPassword.getText(); booleanPassword = true;}
		else lblPassword.setText("musisz podac hasło");

		try{
		if(!txtAge.getText().equals("")){age = Integer.parseInt(txtAge.getText()); booleanAge = true;}
			else lblAge.setText("musisz podac wiek");}
		catch(Exception e){
			lblAge.setText("zła wartosc");}

		if ((booleanName == true) && (booleanSecondName == true) && (booleanMail == true) && (booleanLogin == true) && (booleanPassword == true) && (booleanAge == true))
		{

			ArrayList<User>	usersList =  OperationsOnFile.readUsersListFromFile();
			usersList.add(User.createUserFromRegistration(name,secondName,mail,login,password,age));
			OperationsOnFile.writeUsersListToFileArray(usersList);
			System.out.println(User.createUserFromRegistration(name,secondName,mail,login,password,age));
			txtLogin.clear();
			txtAge.clear();
			txtSecondName.clear();
			txtPassword.clear();
			txtMail.clear();
			txtName.clear();

			lblAge.setText("");
			lblPassword.setText("");
			lblLogin.setText("");
			lblMail.setText("");
			lblSecondName.setText("");
			lblName.setText("");
		}
}
	public void exitFromRegistration(ActionEvent event) throws IOException{
		Parent loginScreen = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene logScene = new Scene(loginScreen);		
		stage.setScene(logScene);
		stage.setTitle("Logowanie");
		stage.show();
		
	}
	

}
