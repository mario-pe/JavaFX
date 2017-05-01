package ScenesAndControllers;

import Model.Element;
import Order.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by domowy on 2016-08-08.
 */
public class OrderDetails implements Initializable {
    private Order order;
    @FXML private Button btnExit;
    @FXML public ComboBox<String> cmbAddProuct;
    @FXML private Label lblShow;
    @FXML private Button btnShow;


    @FXML
    private TableView<Element> table;
    @FXML private TableColumn<Element, String> name;
    @FXML private TableColumn<Element, Float> price;
    @FXML private TableColumn<Element, Float> weight;
    @FXML private TableColumn<Element, Float> cost;
    @FXML private TableColumn<Element, Float> load;
    @FXML private TableColumn<Element, Integer> amount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        name.setCellValueFactory(new PropertyValueFactory<Element,String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Element,Float>("price"));
        weight.setCellValueFactory(new PropertyValueFactory<Element,Float>("weight"));
        cost.setCellValueFactory(new PropertyValueFactory<Element,Float>("cost"));
        load.setCellValueFactory(new PropertyValueFactory<Element,Float>("load"));
        amount.setCellValueFactory(new PropertyValueFactory<Element,Integer>("amount"));


    }
    public OrderDetails(){
        this.order=null;
    }

    public  void setOrder(Order order){
        this.order = order;
        ObservableList<Element> elementObservableList = FXCollections.observableArrayList(order.getPosition());
        table.setItems(elementObservableList);

    }

    public void orderDetails(Order order) {

        ObservableList<Element> elementObservableList = FXCollections.observableArrayList(order.getPosition());

        name.setCellValueFactory(new PropertyValueFactory<Element,String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Element,Float>("price"));
        weight.setCellValueFactory(new PropertyValueFactory<Element,Float>("weight"));
        cost.setCellValueFactory(new PropertyValueFactory<Element,Float>("cost"));
        load.setCellValueFactory(new PropertyValueFactory<Element,Float>("load"));
        amount.setCellValueFactory(new PropertyValueFactory<Element,Integer>("amount"));
        table.setItems(elementObservableList);


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
                stage.setTitle("Dodawanie urządzenia");
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
    public void ItemSearchScreen(ActionEvent event){
        try {
            Parent SearchScreen = FXMLLoader.load(getClass().getResource("ItemsSearchScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene SearchScene = new Scene(SearchScreen);
            stage.setScene(SearchScene);
            stage.setTitle("Edycja produktów");
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
