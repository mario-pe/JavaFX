package Order;

import Model.Element;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by domowy on 2016-07-22.
 */
public class Order implements Serializable {

    private ArrayList<Element> position;
    private long id;
//    private float cost;


    public Order(ArrayList<Element> position, long id){
        this.position = position;
        this.id = id;
    }
    public ArrayList getPosition(){
        return position;
    }
    public long getId(){
        return id;
    }
    public void setPosition(ArrayList<Element> position){
        this.position = position;
    }
    public void setId(long id){
        this.id= id;
    }
    public String toString () {
        return "pozycja " +getPosition() + " id " + getId()+"\n";
    }
}
