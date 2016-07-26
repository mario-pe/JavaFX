package ScenesAndControllers;

import Model.Book;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by domowy on 2016-07-19.
 */
public class AddBookController implements Initializable {


    @FXML private Button itemsSearch;
    @FXML private Button btnExit;

    @FXML private TextField txtTitle;
    @FXML private TextField txtAuthor;
    @FXML private TextField txtPrice;
    @FXML private TextField txtWeight;
    @FXML private TextField txtISBN;
    @FXML private TextField txtStore;

    @FXML private Label lblTitle;
    @FXML private Label lblAuthor;
    @FXML private Label lblPrice;
    @FXML private Label lblWeight;
    @FXML private Label lblISBN;
    @FXML private Label lblStore;

    @FXML public ComboBox<String> cmbAddProuct;


    public void addingBook(ActionEvent event) throws IOException {
        boolean booleanTitle = false;
        boolean booleanAuthor = false;
        boolean booleanPrice = false;
        boolean booleanWeight = false;
        boolean booleanISBN = false;
        boolean booleanStore = false;

        String title ="T";
        String author ="A";
        float price = 1;
        float weight = 2;
        float ISBN = 3;
        int store = 4;

        if(!txtTitle.getText().equals("")) {title = txtTitle.getText(); booleanTitle = true;}
        else lblTitle.setText("pole obowiązkowe");

        if((!txtAuthor.getText().equals(""))) {author = txtAuthor.getText(); booleanAuthor = true;}
        else lblAuthor.setText("pole obowiązkowe");

        try{
        if((!txtPrice.getText().equals("")) && (price != Float.parseFloat(txtPrice.getText()))) {price = Float.parseFloat(txtPrice.getText()); booleanPrice = true;}
        else lblPrice.setText("pole obowiązkowe");}
        catch (Exception e){
            lblPrice.setText("zła wartosc");}

        try{
        if(!txtWeight.getText().equals("")) {weight = Float.parseFloat(txtWeight.getText()); booleanWeight = true;}
        else lblWeight.setText("pole obowiązkowe");}
        catch (Exception e){
            lblWeight.setText("zła wartosc");}

        try{
        if(!txtISBN.getText().equals("")) {ISBN = Float.parseFloat(txtISBN.getText()); booleanISBN = true;}
        else lblISBN.setText("pole obowiązkowe");}
        catch (Exception e){
            lblISBN.setText("zła wartosc");}

        try{
        if(!txtStore.getText().equals("")) { store = Integer.parseInt(txtStore.getText()); booleanStore = true;}
        else lblStore.setText("pole obowiązkowe");}
        catch (Exception e){
            lblStore.setText("zła wartosc");}

        if((booleanTitle == true) && (booleanAuthor == true) && (booleanPrice==true) &&  (booleanWeight == true) && (booleanISBN == true) && (booleanStore == true))

        {
            ArrayList<Item> itemArrayList = OperationsOnFile.readItemListFromFile();
            itemArrayList.add(Book.createBook(title,author, price, weight,  ISBN, store));
            OperationsOnFile.writeItemListToFileArray(itemArrayList);


            txtTitle.clear();
            txtAuthor.clear();
            txtPrice.clear();
            txtWeight.clear();
            txtISBN.clear();
            txtStore.clear();

            lblAuthor.setText("");
            lblTitle.setText("");
            lblPrice.setText("");
            lblWeight.setText("");
            lblISBN.setText("");
            lblStore.setText("");
        }

    }

    @Override public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        ObservableList<String> productTypes = FXCollections.observableArrayList("urzadzenie", "ksiazka");
        cmbAddProuct.setItems(productTypes);
    }
    public void productChooser(ActionEvent event) {
        if (cmbAddProuct.getValue().equals("urzadzenie")) {
//			System.out.println("U urzadzenie");
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
//			System.out.println("k kiazka");
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
