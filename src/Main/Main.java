package Main;

import Model.*;
import Order.Order;
import ProjectUtils.OperationsOnFile;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

//         ObservableList<User> list = FXCollections.observableArrayList(
//                new User ("mario","1234", "perk@asd.pl","mariope","pass",22),
//                new User ("wacek","zaza", "sf@asd.pl","aa","aa" ,55),
//                new User ("klocek","zaza", "asf@asd.pl","gruby","hass" ,56),
//                new User ("wuj","zaza", "gaer@asd.pl","killer","aa" ,25),
//                new User ("stasiek","zaza", "reagk@asd.pl","malpa","qq" ,2),
//                new User ("norman","zaza", "pragrk@ads.pl","gucio","zz" ,62),
//                new User ("cyc","zaza", "pearegrk@asd.pl","hitler","ss" ,82),
//                new User ("kazik","zaza", "asdb@asd.pl","kazik","pass" ,12),
//                new User ("aa","aa","ewf@asd.pl","alf","pass",45)
//        );
//        ArrayList<User> lista = new ArrayList<>(list);
//        File file1 = new File("user.ser");
//        OperationsOnFile.writeUsersListToFileArray(lista);
//        ArrayList<User> usersList =  OperationsOnFile.readUsersListFromFile();
////        for(User users: usersList)
////            System.out.println(users);
////                ArrayList<Item> itemList =  OperationsOnFile.readItemListFromFile();
////             for(Item item: itemList)
////            System.out.println(item);
//
//        ArrayList<Item> itemList = new ArrayList<>();
//        Device d = new Device();
//        Book b = new Book();
//        itemList.add(b);
//        itemList.add(d);
//        File file = new File("item.ser");
//        OperationsOnFile operationsOnFile;
//        OperationsOnFile.writeItemListToFileArray(itemList);
//        ArrayList<Item> itemLista = OperationsOnFile.readItemListFromFile();
//        for(Item item: itemList) System.out.println(item);
//        ArrayList<User> usersLista =  OperationsOnFile.readUsersListFromFile();
//        for(User u : usersLista)
//            System.out.println(u);
//
//        Device d1 = new Device();
//        Book b1 = new Book();
//        Element e = new Element(d1,3);
//        Element e1 = new Element(b1,4);
//        ArrayList<Element> elementArrayList =  new ArrayList<>();
//        elementArrayList.add(e);
//        elementArrayList.add(e1);
//        ArrayList<Order> orderArrayList = new ArrayList<Order>();
//        Order o1 = new Order(elementArrayList,1);
//        Order o2 = new Order(elementArrayList,2);
//        orderArrayList.add(o1);
//        orderArrayList.add(o2);
//        OperationsOnFile.writeOrderListToFileArray(orderArrayList);
//        ArrayList<Item> itemArrayList = OperationsOnFile.readItemListFromFile();
//        for(Item item: itemArrayList) System.out.println(item);
//        ArrayList<Order> orderListFromFile = OperationsOnFile.readOrderListFromFile();
//        for(Order or: orderListFromFile) System.out.println(or);

//        Parent root = FXMLLoader.load(getClass().getResource("../ScenesAndControllers/MenuScreen.fxml"));
//        primaryStage.setTitle("Menu Główne");
        Parent root = FXMLLoader.load(getClass().getResource("../ScenesAndControllers/LoginForm.fxml"));
        primaryStage.setTitle("Logowanie");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
