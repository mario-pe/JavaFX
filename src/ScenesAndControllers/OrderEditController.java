package ScenesAndControllers;

import Model.Element;
import Order.Order;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static ProjectUtils.OperationsOnFile.readOrderListFromFile;

/**
 * Created by domowy on 2016-07-23.
 */
public class OrderEditController implements Initializable{
    @FXML
    private Button btnItemsSearch;
    @FXML private Button btnExit;
    @FXML private ComboBox<String> cmbAddProuct;
    @FXML private TextField txtId;
    @FXML private TableView<Order> table;
    @FXML private TableColumn<Order, String> elementTableColumn;
    @FXML private TableColumn<Order, Long> idTableColumn;


    @Override public void initialize(URL location, ResourceBundle resources) {

        table.setRowFactory(tv -> {
            TableRow<Order> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && !row.isEmpty()) {

                    Order rowOrder = row.getItem();

//                    for(int i = 0; i < rowOrder.getPosition().size() ; i++)
//                    System.out.println(rowOrder.getPosition().get(i));
                    Object[] elements = rowOrder.getPosition().toArray();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderDetails.fxml"));
                    Parent root = null;
                    try {
                         root = (Parent)fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    OrderDetails controller = fxmlLoader.<OrderDetails>getController();

                    controller.setOrder(rowOrder);
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }
            });
            return row;
        });

        ObservableList<String> productTypes = FXCollections.observableArrayList("urzadzenie", "ksiazka");
        cmbAddProuct.setItems(productTypes);
        ArrayList<Order> orderArrayList = null;
        try {
            orderArrayList = OperationsOnFile.readOrderListFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<Order> orderObservableList = FXCollections.observableArrayList(orderArrayList);
        elementTableColumn.setCellValueFactory(new PropertyValueFactory<Order , String>("position"));
        idTableColumn.setCellValueFactory(new PropertyValueFactory<Order, Long>("id"));
        table.setItems(orderObservableList);

    }
    public void search(ActionEvent event){

        ObservableList<Order> orderObservableList = null;
        try {
            orderObservableList = FXCollections.observableArrayList(OperationsOnFile.readOrderListFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<Order> result = FXCollections.observableArrayList();
        int w = orderObservableList.size() -1 ;


        try {
            for(int i = 0; i<orderObservableList.size();i++)
                if(orderObservableList.get(i).getId()==Long.parseLong(txtId.getText())){
                    result.add(orderObservableList.get(i));
                    table.setItems(result);


                }

            else if( result.isEmpty() && i == w ){
                    AlertBox.display("szukanie","Nie znaleziono żadnego zamowienia");
                    txtId.clear();
                }
        }
        catch (Exception e){ AlertBox.display("Błąd"," W tym polu musisz wpisac liczbe ");}



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
