package pharmacy;
import java.util.ArrayList;
import pharmacy.PersonClasses.Customer;
import pharmacy.ExtraClasses.DateTime;
import pharmacy.ProductClasses.Product;
public class Order {
    //--------(Attributes)----------//
    private static int counterID = 0;
    private final int invoiceId;
    private double invoicePrice;
    private Customer invoiceCustomer;
    private DateTime invoiceDate;
    private ArrayList<Product> productList;
    //---------(Methods)-------------//
    private int generateId(){
        counterID++;
        return counterID;
    }
    //Constructor Methods
    public Order(Customer c){
        invoiceId = generateId();
        invoiceCustomer = c;
        invoiceDate = new DateTime();
        productList = new ArrayList<Product>();
    }
    //Mutator Methods
    public void setInvoiceCustomer(Customer c){ 
        invoiceCustomer = c; 
    }
    public void setInvoiceDate(DateTime dt){ 
        invoiceDate = dt; 
    }
    public void addProduct(Product p){
        productList.add(p);
        invoicePrice += p.getTotalPrice();
    }
    public void deleteProduct(int code){
        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).getCode() == code){
                productList.remove(i);
            }
        }
    }
    //Accessor Methods
    public int getInvoiceId() { return invoiceId; }
    public double getInvoicePrice() { return invoicePrice; }
    public Customer getInvoiceCustomer() { return invoiceCustomer; }
    public DateTime getInvoiceDate() { return invoiceDate; }
    public Product productIndex(int index){ 
        try {
            return productList.get(index); 
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.print(e.toString());
            return null;
        }
    }
    public ArrayList<Product> getProductList() { return productList; }
}
