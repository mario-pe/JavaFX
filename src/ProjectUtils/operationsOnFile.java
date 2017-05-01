package ProjectUtils;

import Model.Item;
import Model.User;
import javafx.collections.ObservableList;
import Order.Order;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by domowy on 2016-07-18.
 */
public class OperationsOnFile {
    private static final String userFileName = "src\\user.ser";
    private static final String userItemName = "src\\item.ser";
    private static final String userOrderName = "src\\order.ser";


    public static void writeUsersListToFileArray(ArrayList<User> list) {
        try {

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFileName));
            oos.writeObject(list);
            oos.close();
            System.out.println("zapisane lista user");

        } catch (FileNotFoundException e) {
            System.out.println("cos sie niezapisane");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("cos sie niezapisane");
            e.printStackTrace();
        }
    }
    public static ArrayList<User> readUsersListFromFile() throws IOException {

        try {
            FileInputStream fis = new FileInputStream(userFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<User> list = (ArrayList<User>) ois.readObject();
            ois.close();
            System.out.println("wczytana lista User");
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void writeItemListToFileArray(ArrayList<Item> list) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userItemName));
            oos.writeObject(list);
            oos.close();
            System.out.println("zapisane lista item");

        } catch (FileNotFoundException e) {
            System.out.println("cos sie niezapisane");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("cos sie niezapisane");
            e.printStackTrace();
        }
    }
    public static ArrayList<Item> readItemListFromFile() throws IOException {

        try {
            FileInputStream fis = new FileInputStream(userItemName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Item> list = (ArrayList<Item>) ois.readObject();
            ois.close();
            System.out.println("wczytana lista item");
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void writeOrderListToFileArray(ArrayList<Order> list) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userOrderName));
            oos.writeObject(list);
            oos.close();
            System.out.println("zapisane lista order");

        } catch (FileNotFoundException e) {
            System.out.println("cos sie niezapisane");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("cos sie niezapisane");
            e.printStackTrace();
        }
    }
    public static ArrayList<Order> readOrderListFromFile() throws IOException {

        try {
            FileInputStream fis = new FileInputStream(userOrderName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Order> list = (ArrayList<Order>) ois.readObject();
            ois.close();
            System.out.println("wczytana lista order");
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
