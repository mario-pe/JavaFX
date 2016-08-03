package Model;

import java.io.Serializable;

/**
 * Created by domowy on 2016-07-18.
 */
public abstract class Item implements Serializable {
    private String name;
    private float price;
    private float weight;
    private int quantity;

    public Item(){
        this.name = "name";
        this.price = 0;
        this.weight = 0;
        this.quantity = 0;
    }
    public Item(String nazwa, float cena, float waga,int store){
        this.name = nazwa;
        this.price = cena;
        this.weight = waga;
        this.quantity =  store;
    }



    public Item(Item produkt){

        this.name = produkt.name;
        this.price = produkt.price;
        this.weight = produkt.weight;
        this.quantity = produkt.quantity;
    }
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public  String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String toString(){
        return "Nazwa " + getName() + " cena " + getPrice() +
                " waga " + getWeight() + " magazyn " + getQuantity();
    }
}
