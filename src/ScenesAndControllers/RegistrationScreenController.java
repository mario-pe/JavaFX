package ScenesAndControllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			lblName.setText("");
			name = txtName.getText();
			booleanName = true;
		} else lblName.setText("pole obowiązkowe");

		if (!txtSecondName.getText().equals("")) {
			lblSecondName.setText("");
			secondName = txtSecondName.getText();
			booleanSecondName = true;
		} else lblSecondName.setText("pole obowiązkowe");




		if (!txtMail.getText().equals("")) {
			mail = txtMail.getText();
			booleanMail = true;
		} else lblMail.setText("pole obowiązkowe");

		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mail);
		booleanMail = matcher.matches();
		if(booleanMail == false) lblMail.setText("zły format");
		else {booleanMail = true; lblMail.setText("");}


		if (!txtLogin.getText().equals("")) {
			lblLogin.setText("");
			login = txtLogin.getText();
			booleanLogin = true;
		} else lblLogin.setText("pole obowiązkowe");

		if(!txtPassword.getText().equals("")){password = txtPassword.getText(); booleanPassword = true;lblPassword.setText("");}
		else lblPassword.setText("pole obowiązkowe");

		try{
		if(!txtAge.getText().equals("")){age = Integer.parseInt(txtAge.getText());
			 booleanAge = true;}
			else lblAge.setText("pole obowiązkowe");
			if(age < 18) {lblAge.setText("jestes za młody"); booleanAge = false;}
		          else{ booleanAge = true;
			lblAge.setText("");}
		}

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
