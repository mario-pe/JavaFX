package Model;

/**
 * Created by domowy on 2016-07-19.
 */
public class Device extends Item {
    private int warranty;

    public Device(){
        super();
        this.warranty = 12;
    }
    public Device(Device item, int warranty){
        super(item);
        this.warranty = warranty;
    }
    public Device(Device item){
        super(item);
        this.warranty = item.warranty;
    }
    public static Device creatDevice(String name, float price, float weight, int warranty, int store){
        Device d = new Device();
        d.setName(name);
        d.setPrice(price);
        d.setWeight(weight);
        d.setWarranty(warranty);
        d.setQuantity(store);
        return d;
    }
    public int getWarranty(){
        return warranty;
    }
    public void setWarranty(int warranty){
        this.warranty= warranty;
    }

    public String toString(){
        return "nazwa " + getName() + " cena " + getPrice() + " waga " + getWeight() + " gwarancja "+ getWarranty() + " magazyn " + getQuantity();
    }

}
