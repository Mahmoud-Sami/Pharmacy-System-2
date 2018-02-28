package pharmacy.ProductClasses;
//Abstract Class 
public abstract class Product {  
    protected String name;
    protected int code;
    protected double price;
    protected int quantity;
    //Constructor
    public Product(String name, int code, double price, int quantity) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
    }
    
    //Other Function
    //public AddToList()
    //Mutators
    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //Accessors
    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
   //Other Methods
    public double  getTotalPrice(){
        return quantity*price;
    }
}