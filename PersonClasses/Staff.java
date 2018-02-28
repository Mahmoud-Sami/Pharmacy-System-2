package pharmacy.PersonClasses;
import pharmacy.ExtraClasses.DateTime;
public class Staff extends Person {
    private int confirmedOrders;
    public Staff() {
        confirmedOrders = 0;
    }

    public Staff(String Fname, String Lname, DateTime dt, String address, String phone, String gender) {
        super(Fname, Lname, dt, address, phone, gender);
        confirmedOrders = 0;
    }
    public int getConfirmedOrders() {
        return confirmedOrders;
    }
    public void confirmNewOrder(){
        confirmedOrders++;
    }
}
