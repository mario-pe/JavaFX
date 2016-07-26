package garbage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddProduktController {
	
	Button btnExit;
	
	public void Exit(ActionEvent event){	
		btnExit.setOnAction(e ->{ System.exit(0); });
	}
	
	public void addProductScreen(ActionEvent event) throws IOException{
		Parent addProduktScreen = FXMLLoader.load(getClass().getResource("../garbage/AddProduct.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene addProduktScene = new Scene(addProduktScreen);
		stage.setTitle("Dodawanie Produktu");
		stage.setScene(addProduktScene);
		stage.show();
		
	}
	
}
