package ScenesAndControllers;

import Model.Element;
import Model.Item;
import Order.Order;
import ProjectUtils.GeneratorId;
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

import static java.lang.Long.parseLong;

/**
 * Created by domowy on 2016-07-20.
 */
public class NewOrderController implements Initializable {
    @FXML private TextField txtSearchName;
    @FXML private TextField txtPriceMin;
    @FXML private TextField txtPriceMax;
    @FXML private TextField txtWeightMin;
    @FXML private TextField txtWeightMax;


    @FXML private TableView<Item> table;
    @FXML private TableColumn<Item , String> name;
    @FXML private TableColumn<Item , Float> price;
    @FXML private TableColumn<Item , Float> weight;
    @FXML private TableColumn<Item , Integer> store;
    @FXML private TableView<Element> tableOrder;
    @FXML private TableColumn<Element , Item> nameOrder;
    @FXML private TableColumn<Element , Float> priceOrder;
    @FXML private TableColumn<Element , Float> weightOrder;
    @FXML private TableColumn<Element , Integer> amountOrder;


    @FXML private Label lblOrderId;
    @FXML private TextField txtAmount;
    @FXML private Label lblValue;

    @FXML private Button btnSaveOrder;
//    @FXML private Button btnItemsSearch;
    @FXML private Button btnExit;

    @FXML public ComboBox<String> cmbAddProuct;
    @Override public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> productTypes = FXCollections.observableArrayList("urzadzenie", "ksiazka");
        cmbAddProuct.setItems(productTypes);
        txtAmount.setText("1");





        ArrayList<Item> i = null;
        try {
            i = OperationsOnFile.readItemListFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<Item> tableListView = FXCollections.observableArrayList(i);




        ArrayList<Order> orderArrayList = null;
        try {
            orderArrayList = OperationsOnFile.readOrderListFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GeneratorId.init(orderArrayList);
        lblOrderId.setText(String.valueOf(GeneratorId.getNextId()));
        ArrayList<Element> elementArrayList = new ArrayList<Element>();
//        table.setOnMouseClicked(e->{
//            System.out.println(table.getRowFactory());
//            ArrayList<Item> itemArrayList = new ArrayList<>();
//            itemArrayList.add((Item) table.getRowFactory());
//        });

//        table.getSelectionModel().getSelectedItem()
//
//        TablePosition position = (TablePosition) table.getRowFactory();
//        System.out.println(position);


//                TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
//        int row = pos.getRow();
//        Item item = table.getItems().get(row);
//        TableColumn col = pos.getTableColumn();
//        String data = (String) col.getCellObservableValue(item).getValue();
//        System.out.println(data);

        table.setRowFactory( tv -> {
            TableRow<Item> roww = new TableRow<>();
            roww.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! roww.isEmpty()) ) {
                    Item rowData = roww.getItem();
                    Element element = new Element(rowData);
                    element.setAmount(Integer.parseInt(txtAmount.getText()));
                    rowData.setStore(element.setStoreQuantity(rowData));
                    elementArrayList.add(element);

//                    OperationsOnFile.writeItemListToFileArray(tableListView());

                    ArrayList<Item> ii = new ArrayList<Item>(tableListView);
                     OperationsOnFile.writeItemListToFileArray(ii);

                    for(Element e : elementArrayList)
                        System.out.println(e);

                    ObservableList<Element> listOrederOb = FXCollections.observableArrayList(elementArrayList);
                    String nameElement = element.getItem().getName();

                    nameOrder.setCellValueFactory(new PropertyValueFactory<Element,Item>("item"));
                    amountOrder.setCellValueFactory(new PropertyValueFactory<Element,Integer>("amount"));
                    tableOrder.setItems(listOrederOb);
                }
            });
            return roww;
        });

//        btnSaveOrder.setOnAction(e-> {
//            System.out.println("asd");
//        });





//        Order newOrder = new Order(elementArrayList,parseLong(lblOrderId.getText()));
//        System.out.println(newOrder);
//        orderArrayList.add(newOrder);
//
//
//
//
//        ArrayList<Order> finalOrderArrayList = orderArrayList;
//        btnSaveOrder.setOnAction(e -> {
//
//                    try {
//                        OperationsOnFile.writeOrderListToFile(finalOrderArrayList);
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//        );
//
//
//        ObservableList<Item> obList = null;
//        try {
//            obList = tableListView();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Item,Float>("price"));
        weight.setCellValueFactory(new PropertyValueFactory<Item,Float>("weight"));
        store.setCellValueFactory(new PropertyValueFactory<Item, Integer>("store"));
        table.setItems(tableListView);
//        table.setItems(obList);
        table.setEditable(true);

    }
    public void writeToFile(ActionEvent event) throws IOException {
        ArrayList<Order> orderListFromFile = OperationsOnFile.readOrderListFromFile();
        ObservableList<Element> listKurwaMac =  FXCollections.observableArrayList(tableOrder.getItems());
       ArrayList<Element> elementList = new ArrayList<>(listKurwaMac);


       Order order = new Order(elementList,parseLong(lblOrderId.getText()));
//        Order order = new Order(elementList,5);
//        ArrayList<Order> orderList = new ArrayList<Order>();
        orderListFromFile.add(order);
        for(Order o : orderListFromFile)
        System.out.println(o);
        OperationsOnFile.writeOrderListToFileArray(orderListFromFile);

        Parent addProduktScreen = FXMLLoader.load(getClass().getResource("NewOrder.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene addProduktScene = new Scene(addProduktScreen);
        stage.setScene(addProduktScene);
        stage.setTitle("Nowe zamówienie");
        stage.show();


    }
//    public void Tables (ActionEvent eve){
//        ArrayList<Element> elementArrayList = new ArrayList<>();
//        table.setOnMouseClicked(e->{
//            table.getItems();
//        });
//
//        table.setRowFactory( tv -> {
//            TableRow<Item> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
//                    Item rowData = row.getItem();
//                    Element element = new Element(rowData);
//                    elementArrayList.add(element);
//                    for(Element e : elementArrayList)
//                        System.out.println(e);
//                    element.setAmount(Integer.parseInt(txtAmount.getText()));
//                    ObservableList<Element> listOrederOb = FXCollections.observableArrayList(elementArrayList);
//                    String nameElement = element.getItem().getName();
//                    nameOrder.setCellValueFactory(new PropertyValueFactory<Element,String>("item"));
//                    amountOrder.setCellValueFactory(new PropertyValueFactory<Element,Integer>("amount"));
//                    tableOrder.setItems(listOrederOb);
//                }
//            });
//            return row ;
//        });
//    }

//    public  ObservableList<Item> tableListView() throws IOException {
//        ArrayList<Item> i = OperationsOnFile.readItemListFromFile();
//        ObservableList<Item> itemObservableList = FXCollections.observableArrayList(i);
//        return itemObservableList;
//    }
//    public void search(ActionEvent event) throws IOException {
//        ObservableList<Item> itemObservableList = FXCollections.observableArrayList(tableListView());
//        ObservableList<Item> listSearch = FXCollections.observableArrayList();
//        lblValue.setText(txtSearchName.getText());
//
//        for(Item item: itemObservableList)
//
//            if(txtSearchName.getText().equals(item.getName())) {
//                listSearch.add(item);
//
//                table.setItems(listSearch);
//                table.getOnMouseReleased();
//                table.getOnMouseClicked();
//
//            }
//    }
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
    public void itemsViewScreen(ActionEvent event) throws IOException {
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
