package ProjectUtils;

import Order.Order;

import java.util.ArrayList;

/**
 * Created by domowy on 2016-07-22.
 */
public class GeneratorId {
    private static long id;

    public static long init(ArrayList<Order> orderList){
        long tmp = 0;
        for(Order order: orderList)
        if(order.getId()>tmp){
            tmp = order.getId();
        }
        id = tmp;
        return id;
    }
    public static long getNextId(){
        return ++id;
    }


}
