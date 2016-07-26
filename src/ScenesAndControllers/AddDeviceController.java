package ScenesAndControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Device;
import Model.Item;
import ProjectUtils.OperationsOnFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static sun.audio.AudioDevice.device;

public class AddDeviceController implements Initializable {

	@FXML private Button btnExit;
	@FXML public ComboBox<String> cmbAddProuct;
	@FXML private TextField txtName;
	@FXML private TextField txtPrice;
	@FXML private TextField txtWeight;
	@FXML private TextField txtWarranty;
	@FXML private TextField txtStore;

	@FXML private Label lblName;
	@FXML private Label lblPrice;
	@FXML private Label lblWeight;
	@FXML private Label lblWarranty;
	@FXML private Label lblStore;


	public void createDevice(ActionEvent event) throws IOException {

		boolean booleanName = false;
		boolean booleanPrice = false;
		boolean booleanWeight = false;
		boolean booleanWarranty = false;
		boolean booleanStore = false;

		String name = "";
		float price = 0;
		float weight = 0;
		int warranty = 0;
		int store = 0;

		if (!txtName.getText().equals("")) {
			name = txtName.getText();
			booleanName = true;
		} else lblName.setText("pole obowiązkowe");


		try{
		if (!txtPrice.getText().equals("")) {
			price = Float.parseFloat(txtPrice.getText());
			booleanPrice = true;
		} else lblPrice.setText("pole obowiązkowe");}
		catch(Exception e){
			lblPrice.setText("zła wartosc");}

		try{
		if (!txtWeight.getText().equals("")) {
			weight = Float.parseFloat(txtWeight.getText());
			booleanWeight = true;
		} else lblWeight.setText("pole obowiązkowe");}
		catch(Exception e){
			lblWeight.setText("zła wartosc");}

		try{
		if (!txtWarranty.getText().equals("")) {
			warranty = Integer.parseInt(txtWarranty.getText());
			booleanWarranty = true;
		} else lblWarranty.setText("pole obowiązkowe");}
			catch(Exception e){
				lblWarranty.setText("zła wartosc");}

		try{
		if (!txtStore.getText().equals("")) {
			store = Integer.parseInt(txtStore.getText());
			booleanStore = true;
		} else lblStore.setText("pole obowiązkowe");}
		catch(Exception e){
			lblStore.setText("zła wartosc");}

		if ((booleanName == true) && (booleanPrice == true) && (booleanWeight == true) && (booleanWarranty == true) && (booleanStore == true))

		{
			ArrayList<Item> itemArrayList = OperationsOnFile.readItemListFromFile();
			itemArrayList.add(Device.creatDevice(name, price, weight, warranty, store));
			OperationsOnFile.writeItemListToFileArray(itemArrayList);


			txtName.clear();
			txtPrice.clear();
			txtWeight.clear();
			txtWarranty.clear();
			txtStore.clear();

			lblName.setText("");
			lblPrice.setText("");
			lblWeight.setText("");
			lblWarranty.setText("");
			lblStore.setText("");

//		String name = txtName.getText();
//		float price = Float.parseFloat(txtPrice.getText());
//		float weight = Float.parseFloat(txtWeight.getText());
//		int warranty = Integer.parseInt(txtWarranty.getText());
//		int store = Integer.parseInt(txtStore.getText());
//			ArrayList<Item> itemArrayList = OperationsOnFile.readItemListFromFile();
//		itemArrayList.add(Device.creatDevice(name,price,weight,warranty,store));
//		OperationsOnFile.writeItemListToFileArray(itemArrayList);
//		txtName.clear();
//		txtPrice.clear();
//		txtWeight.clear();
//		txtWarranty.clear();
//		txtStore.clear();

		}
	}

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
	public void itemsView(ActionEvent event) {
		try {
			Parent SearchScreen = FXMLLoader.load(getClass().getResource("ItemsSearchScreen.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene SearchScene = new Scene(SearchScreen);
			stage.setScene(SearchScene);
			stage.setTitle("Dodawanie ksiazki");
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
