package ScenesAndControllers;

import Model.User;
import ProjectUtils.Login;
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


import java.io.IOException;
import java.util.ArrayList;

public class LoginFormController {
    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPass;

    public void Login (ActionEvent event) throws IOException {

        ArrayList<User> usersList =  OperationsOnFile.readUsersListFromFile();

        if(Login.entry(txtUserName.getText(), txtPass.getText(),usersList) == true){

            Parent MenuScreen = FXMLLoader.load(getClass().getResource("MenuScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene MenuScene = new Scene(MenuScreen);
            stage.setScene(MenuScene);
            stage.setTitle("Menu główne");
            stage.show();
        }
        else{
            lblStatus.setText("Błąd logowania");}
    }
    public void ToRegistration(ActionEvent event) throws IOException{
        Parent RegistrationScreen = FXMLLoader.load(getClass().getResource("RegistrationScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene RegistrationScene = new Scene(RegistrationScreen);
        stage.setScene(RegistrationScene);
        stage.show();
    }

}
