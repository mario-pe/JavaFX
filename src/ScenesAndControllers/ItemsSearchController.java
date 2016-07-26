package ScenesAndControllers;

import Model.Item;
import ProjectUtils.OperationsOnFile;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by domowy on 2016-07-19.
 */
public class ItemsSearchController implements Initializable{
    @FXML private TextField txtSearchName;
    @FXML private TextField txtPriceMin;
    @FXML private TextField txtPriceMax;
    @FXML private TextField txtWeightMin;
    @FXML private TextField txtWeightMax;


    @FXML private Button btnItemsSearch;
    @FXML private Button btnExit;
    @FXML public ComboBox<String> cmbAddProuct;
    @FXML private TableView<Item> table;
    @FXML private TableColumn<Item , String> name;
    @FXML private TableColumn<Item , Float> price;
    @FXML private TableColumn<Item , Float> weight;
    @FXML private TableColumn<Item , SimpleIntegerProperty> store;


    @Override public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> productTypes = FXCollections.observableArrayList("urzadzenie", "ksiazka");
        cmbAddProuct.setItems(productTypes);


        ObservableList<Item> obList = null;
        try {
            obList = tableListView();
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        for(Item i: obList)
//            System.out.println(i);

        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Item,Float>("price"));
        weight.setCellValueFactory(new PropertyValueFactory<Item,Float>("weight"));
        store.setCellValueFactory(new PropertyValueFactory<Item,SimpleIntegerProperty>("store"));
        table.setItems(obList);


    }
    public  ObservableList<Item> tableListView() throws IOException {
        ArrayList<Item> i = OperationsOnFile.readItemListFromFile();
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList(i);
        return itemObservableList;
    }
    public void search(ActionEvent event) throws IOException {
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList(tableListView());
        ObservableList<Item> listSearch = FXCollections.observableArrayList();
        for(Item item: itemObservableList)

            if(txtSearchName.getText().equals(item.getName())) {
                listSearch.add(item);
                table.setItems(listSearch);
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
    public void Exit(ActionEvent event) {
        btnExit.setOnAction(e -> {
            System.exit(0);
        });
    }
    public void itemsView(ActionEvent event){
            try {
                Parent addProduktScreen = FXMLLoader.load(getClass().getResource("ItemsSearchScreen.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene addProduktScene = new Scene(addProduktScreen);
                stage.setScene(addProduktScene);
                stage.setTitle("Dodawanie ksiazki");
                stage.show();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
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
