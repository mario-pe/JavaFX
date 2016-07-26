package Model;

/**
 * Created by domowy on 2016-07-19.
 */
public class Book extends Item {

    private String author;
    private float ISBN;

    public Book(){
        super();
        this.author = "unknown";
        this.ISBN = 00000000;
    }
    public Book(Book product, String author, float ISBN){
        super(product);
        this.author = author;
        this.ISBN = ISBN;
    }
    public Book(Book product){
        super(product);
        author = product.author;
        ISBN = product.ISBN;
    }
    public float getISBN(){
        return ISBN;
    }
    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }
    public void setISBN(float ISBN){
        this.ISBN = ISBN;
    }

    public static Book createBook(String name,String authorName, float price, float weight,  float ISBN, int store ){
        Book b = new Book();

        b.setName(name);
        b.setAuthor(authorName);
        b.setPrice(price);
        b.setWeight(weight);
        b.setISBN(ISBN);
        b.setStore(store);

        return b;
    }
    public String toString() {
        return "Nazwa " + getName() + " autor " + getAuthor()
                 + ", cena " + getPrice()+ " waga " + getWeight() + ", kod " + getISBN()
                + " magazyn " + getStore();
    }


}
