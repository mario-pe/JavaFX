package Model;

import java.io.Serializable;

/**
 * Created by domowy on 2016-07-19.
 */
public class Element implements Serializable {
    private Item item;
    private int amount;


    public Element(Item i){
        this.item = i;
        this.amount= 0;
    }
    public Element(Item item, int amount){
        this.item= item;
        this.amount= amount;
    }
    public Item getItem(){
        return item;
    }
    public int getAmount(){
        return amount;
    }
    public void setItem(Item item){
        this.item=item;
    }
    public void setAmount(int amount){
        this.amount=amount;
    }

    public String toString(){
        return "nazwa "+getItem().getName() +" cena " + getItem().getPrice() + " waga " + getItem().getWeight() + " ilość "+ getAmount() + "\n";
    }
    public static float cost(Element element){
        return element.getAmount() * element.getItem().getPrice();
    }
    public static float load(Element element){
        return element.getAmount()* element.getItem().getWeight();
    }

    public int setStoreQuantity(Item item){
        return item.getStore()- this.getAmount();
    }

}
