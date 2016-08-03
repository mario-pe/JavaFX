package ScenesAndControllers;

import Model.Book;
import Model.Item;
import ProjectUtils.OperationsOnFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.control.cell.TextFieldTableCell.forTableColumn;

/**
 * Created by domowy on 2016-07-19.
 */
public class ItemsSearchController implements Initializable{
    @FXML private TextField txtSearchName;
    @FXML private TextField txtPriceMin;
    @FXML private TextField txtPriceMax;
    @FXML private TextField txtWeightMin;
    @FXML private TextField txtWeightMax;
    @FXML private Button btnSave;
    @FXML private Button btnItemsSearch;
    @FXML private Button btnExit;
    @FXML public ComboBox<String> cmbAddProuct;
    @FXML private TableView<Item> table;
    @FXML private TableColumn<Item , String> name;
    @FXML private TableColumn<Book, String> author;
    @FXML private TableColumn<Item, Float> price;
    @FXML private TableColumn<Item , Float> weight;
    @FXML private TableColumn<Item , Integer> quantity;


    @Override public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);
        ObservableList<String> productTypes = FXCollections.observableArrayList("urzadzenie", "ksiazka");
        cmbAddProuct.setItems(productTypes);

        ObservableList<Item> obList = null;
        try {
            obList = tableListView();
        } catch (IOException e) {
            e.printStackTrace();
        }

        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());

        name.setOnEditCommit(
               new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Item, String> t) {
                        ((Item) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                    }
                });

        author.setCellValueFactory(new PropertyValueFactory<Book, String >("author"));
        author.setCellFactory(TextFieldTableCell.forTableColumn());

        author.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Book, String> t) {
                        ((Book) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAuthor(t.getNewValue());
                    }
                });

        price.setCellValueFactory(new PropertyValueFactory<Item, Float>("price"));
        price.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        price.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Item, Float>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Item, Float> t) {
                        ((Item) t.getTableView().getItems().get(t.getTablePosition().getRow())).
                                setPrice(t.getNewValue());
                    }
                });

        weight.setCellValueFactory(new PropertyValueFactory<Item,Float>("weight"));
        weight.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

        weight.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Item, Float>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Item, Float> t) {
                        ((Item) t.getTableView().getItems().get(t.getTablePosition().getRow())).setWeight(t.getNewValue());
                    }
                });
        quantity.setCellValueFactory(new PropertyValueFactory<Item,Integer>("quantity"));
        quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        quantity.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Item, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Item, Integer> t) {
                        ((Item) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQuantity(t.getNewValue());
                    }
                });

        table.setItems(obList);



        btnSave.setOnAction(e-> {
            ArrayList<Item> tableList =new ArrayList<>(table.getItems()) ;
            OperationsOnFile.writeItemListToFileArray(tableList);});

    }
    public  ObservableList<Item> tableListView() throws IOException {
        ArrayList<Item> i = OperationsOnFile.readItemListFromFile();
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList(i);
        return itemObservableList;
    }
    public void search(ActionEvent event) throws IOException {
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList(tableListView());
        ObservableList<Item> listSearch = FXCollections.observableArrayList();

        for (Item item : itemObservableList)
            if (txtSearchName.getText().equals("") || txtSearchName.getText().equals(item.getName()))
                if ( txtPriceMin.getText().equals("") || Float.parseFloat(txtPriceMin.getText()) <= item.getPrice())   //min price
                    if (txtPriceMax.getText().equals("") || Float.parseFloat(txtPriceMax.getText()) >= item.getPrice())   // max price
                        if (txtWeightMin.getText().equals("") || Float.parseFloat(txtWeightMin.getText()) <= item.getWeight())    //min price
                            if ( txtWeightMax.getText().equals("") || Float.parseFloat(txtWeightMax.getText()) >= item.getWeight()){
                                listSearch.add(item);
                                table.setItems(listSearch);
                            }
    }
    public void delete(ActionEvent event){
        ObservableList<Item>  selectedProdukt, allProduckt;
        allProduckt = table.getItems();
        selectedProdukt = table.getSelectionModel().getSelectedItems();
        selectedProdukt.forEach(allProduckt :: remove);

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
