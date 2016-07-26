package ScenesAndControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MenuScreenController implements Initializable {
//	@FXML Button btnItemsSearch;
	@FXML private Button btnExit;
	@FXML private Label lblTop;
//	public ObservableList<Item> itemObservableList = FXCollections.observableArrayList(operationsOnFile.readItemListFromFile());
	@FXML
	public ComboBox<String> cmbAddProuct;




	@Override public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> productTypes = FXCollections.observableArrayList("urzadzenie", "ksiazka");
		cmbAddProuct.setItems(productTypes);
	}
	public void Exit(ActionEvent event) {
		btnExit.setOnAction(e -> {
			System.exit(0);
		});
	}
	public void productChooser(ActionEvent event) {

		if (cmbAddProuct.getValue().equals("urzadzenie")) {
			
			Parent addProduktScreen;
			try {
				addProduktScreen = FXMLLoader.load(getClass().getResource("AddDevice.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				Scene addProduktScene = new Scene(addProduktScreen);
				stage.setScene(addProduktScene);
				stage.setTitle("Dodawanie Produktu");
				stage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (cmbAddProuct.getValue().equals("ksiazka")) {
			
			Parent addProduktScreen;
			try {
				addProduktScreen = FXMLLoader.load(getClass().getResource("AddBook.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				Scene addProduktScene = new Scene(addProduktScreen);
				stage.setScene(addProduktScene);
				stage.setTitle("Dodawanie ksiazki");

				stage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else
			System.out.println("bla bla");
	}
	public void itemsView(ActionEvent event){
			try {
				Parent SearchScreen = FXMLLoader.load(getClass().getResource("ItemsSearchScreen.fxml"));
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				Scene SearchScene = new Scene(SearchScreen);
				stage.setScene(SearchScene);
				stage.setTitle("Edycja produktów");
//
				stage.show();

			} catch (IOException e1) {
				e1.printStackTrace();
			}

	}
	public void newOrderScreen(ActionEvent event) throws IOException {
		Parent addProduktScreen = FXMLLoader.load(getClass().getResource("NewOrder.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene addProduktScene = new Scene(addProduktScreen);
		stage.setScene(addProduktScene);
		stage.setTitle("Nowe zamówienie");
		stage.show();
	}
	public void editOrderScreen(ActionEvent event) throws IOException {
		Parent orderEditScreen = FXMLLoader.load(getClass().getResource("OrderEdit.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene orderEditScene = new Scene(orderEditScreen);
		stage.setScene(orderEditScene);
		stage.setTitle("Edycja Zamowienia");
		stage.show();
	}

}
