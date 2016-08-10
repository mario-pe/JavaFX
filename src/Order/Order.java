package Order;

import Model.Element;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by domowy on 2016-07-22.
 */
public class Order implements Serializable {

    private ArrayList<Element> position;
    private long id;

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
//    public float fullCost(){
//        ArrayList<Element> list = this.getPosition();
//        int i = 0;
//        float cost = 0;
//        for(i =0; i< list.size(); i++)
//            System.out.println(getPosition().getClass().getName());
//
//        return cost;
//    }
    public String toString () {
        return "pozycja " +getPosition() + " id " + getId()+"\n";
    }
}
