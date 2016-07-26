package garbage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuScreenController {
	@FXML
	Button btnExit;
	@FXML
	Label lblTop;
	
	
	
	public void Exit(ActionEvent event){	
		btnExit.setOnAction(e ->{ System.exit(0); });
	}
	public void addProductScreen(ActionEvent event) throws IOException{
		Parent addProduktScreen = FXMLLoader.load(getClass().getResource("../garbage/AddProduct.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene addProduktScene = new Scene(addProduktScreen);
		stage.setScene(addProduktScene);
		stage.setTitle("Dodawanie Produktu");
		
		stage.show();
		
	}
	

}
