package ScenesAndControllers;

import Model.Element;
import Model.Item;
import Order.Order;
import ProjectUtils.GeneratorId;
import ProjectUtils.OperationsOnFile;
import com.sun.org.apache.bcel.internal.classfile.ExceptionTable;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
    @FXML private TableColumn<Item , Integer> quantity;
    @FXML private TableView<Element> tableOrder;
    @FXML private TableColumn<Element , String> nameOrder;
    @FXML private TableColumn<Element , Float> priceOrder;
    @FXML private TableColumn<Element , Float> weightOrder;
    @FXML private TableColumn<Element , Integer> amountOrder;
    @FXML private TableColumn<Element , Float> costOrder;
    @FXML private TableColumn<Element , Float> loadOrder;


    @FXML private Label lblOrderId;
    @FXML private TextField txtAmount;
    @FXML private Label lblValue;
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
        ObservableList<Element> listOrederOb = FXCollections.observableArrayList(elementArrayList);
//        listOrederOb.addListener();

        table.setRowFactory( tv -> {
            TableRow<Item> roww = new TableRow<>();
            roww.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !roww.isEmpty() ) {
                    int index = roww.getIndex();
                    Item rowData = roww.getItem();
                    Element element = new Element(rowData);

                    if((rowData.getQuantity()- Integer.parseInt(txtAmount.getText())) >= 0 && (Integer.parseInt(txtAmount.getText()) != 0)) {
                        element.setAmount(Integer.parseInt(txtAmount.getText()));
                        element.setCost(Element.cost(rowData, Integer.parseInt(txtAmount.getText())));
                        element.setLoad(Element.load(rowData, Integer.parseInt(txtAmount.getText())));

                        rowData.setQuantity(element.setStoreQuantity(rowData));
                        tableListView.set(index,rowData);
                        System.out.println(indexBoy(rowData,listOrederOb ));

                        if (indexBoy(rowData,listOrederOb) >= 0) {
                            Element elementDuplicat = listOrederOb.get(indexBoy(rowData, listOrederOb));
                            elementDuplicat.setAmount((listOrederOb.get(indexBoy(rowData,listOrederOb)).getAmount()+Integer.parseInt(txtAmount.getText())));
                            elementDuplicat.setCost(elementDuplicat.getAmount()*elementDuplicat.getPrice());
                            elementDuplicat.setLoad((elementDuplicat.getAmount()*elementDuplicat.getWeight()));
                            listOrederOb.set(indexBoy(rowData, listOrederOb),elementDuplicat);

                        }
                        else{System.out.println("indexs " +indexBoy(rowData,listOrederOb ));
                            listOrederOb.add(element);
                        }

                    lblValue.setText(String.valueOf(fullCost(listOrederOb)));}

                    else AlertBox.display("Błąd","nieodpowiednia ilość");

                    nameOrder.setCellValueFactory(new PropertyValueFactory<Element,String>("name"));
                    priceOrder.setCellValueFactory(new PropertyValueFactory<Element,Float>("price"));
                    weightOrder.setCellValueFactory(new PropertyValueFactory<Element,Float>("weight"));
                    amountOrder.setCellValueFactory(new PropertyValueFactory<Element,Integer>("amount"));
                    costOrder.setCellValueFactory(new PropertyValueFactory<Element,Float>("cost"));
                    loadOrder.setCellValueFactory(new PropertyValueFactory<Element,Float>("load"));
                    tableOrder.setItems(listOrederOb);
                }
            });
            return roww;
        });
        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Item,Float>("price"));
        weight.setCellValueFactory(new PropertyValueFactory<Item,Float>("weight"));
        quantity.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantity"));
        table.setItems(tableListView);
        table.setEditable(true);

    }
    public void writeToFile(ActionEvent event) throws IOException {
        ArrayList<Order> orderListFromFile = OperationsOnFile.readOrderListFromFile();
        table.getItems().toArray();
        ObservableList<Element> elementObservableList =  FXCollections.observableArrayList(tableOrder.getItems());
        ArrayList<Element> elementList = new ArrayList<>(elementObservableList);
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList(table.getItems());
        ArrayList<Item> itemArrayList = new ArrayList<>(itemObservableList);
        OperationsOnFile.writeItemListToFileArray(itemArrayList);
        Order order = new Order(elementList,parseLong(lblOrderId.getText()));
        orderListFromFile.add(order);
        OperationsOnFile.writeOrderListToFileArray(orderListFromFile);
        Parent addProduktScreen = FXMLLoader.load(getClass().getResource("NewOrder.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene addProduktScene = new Scene(addProduktScreen);
        stage.setScene(addProduktScene);
        stage.setTitle("Nowe zamówienie");
        stage.show();


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
        try{
        Element element;
        Item item = null;
        int index = 0;

        ObservableList<Element>  selectedProdukt, allProduckt;
        ObservableList<Item>  allItems;

        allItems = table.getItems();


        allProduckt = tableOrder.getItems();
        selectedProdukt = tableOrder.getSelectionModel().getSelectedItems();
        element = tableOrder.getSelectionModel().getSelectedItem();
        for(Item i : allItems)
        if(element.getName().equals(i.getName())&& element.getPrice()== i.getPrice()&& element.getWeight()== i.getWeight())
         index = allItems.indexOf(i);
        item = allItems.get(index);
        int elemenetAmount = element.getAmount();
        item.setQuantity(item.getQuantity()+ elemenetAmount);
        allItems.set(index,item);
        table.setItems(allItems);
        selectedProdukt.forEach(allProduckt :: remove);}
        catch (Exception e){AlertBox.display("Błąd","Niemożesz usunąć produktu z tej tabeli" );}

    }
    public static float fullCost(ObservableList<Element> list){
        float fullCost =0;
        for(Element element: list) fullCost = fullCost + element.getCost();
            return fullCost;
    }
    public static int indexBoy(Item i, ObservableList<Element> list){
        int result = -2;
        for(Element element : list)
            if((i.getName().equals(element.getName()))&& (i.getPrice() == element.getPrice())&& (i.getWeight()== element.getWeight())) {
                System.out.println("znaleziono duplikat " + element);
                result = list.indexOf(element);
                System.out.println("indexsBoy "+result);
            }
        return result;

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

