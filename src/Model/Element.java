package Model;

import ScenesAndControllers.AlertBox;

import java.io.Serializable;

/**
 * Created by domowy on 2016-07-19.
 */
public class Element implements Serializable {
//    private Item item;
    private int amount;
    private String name;
    //    private float quantity;
    private float price;
    private float weight;
    private float cost;
    private float load;


    public Element(Item i){
//        this.item = i;
        this.amount = 0;
        this.price = i.getPrice();
        this.weight = i.getWeight();
        this.name = i.getName();
        this.cost= 0;
        this.load= 0;
    }
    public Element(Item i, int amount){
        this.name = i.getName();
        this.price = i.getPrice();
        this.weight = i.getWeight();
        this.amount = amount;
        this.cost= i.getPrice();
        this.load = load(i, amount);
    }
    public String getName() {return name;}
    public float getPrice() {
        return price;
    }
    public float getWeight() {
        return weight;
    }
    public float getCost() {
        return cost;
    }
    public float getLoad() {return load;}
    public void setLoad(float load) {this.load = load;}
    public void setName (String name){this.name = name;}
    public void setPrice(float price) {
        this.price = price;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }

//    public Item getItem(){
//        return item;
//    }
    public int getAmount(){
        return amount;
    }
//    public void setItem(Item item){
//        this.item=item;
//    }
    public void setAmount(int amount){
        this.amount=amount;
    }

    public String toString(){
        return "nazwa "+ getName() +" cena " + getPrice() + " waga " + getWeight() + " ilość "+ getAmount() + " wartosc "
                + getCost() + " ciezar "+ getLoad()+"\n";
    }

    public static float cost(Item i, int amount){
        return amount * i.getPrice();
    }
//    public static float cost(int amount, float price){
//        return amount * price;
//    }
    public static float load(Item i, int amount){
        return amount * i.getWeight();
    }
    public int setStoreQuantity(Item i) {
        return i.getQuantity() - this.getAmount();

    }

}
