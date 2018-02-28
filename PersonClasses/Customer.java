package pharmacy.PersonClasses;
import pharmacy.ExtraClasses.DateTime;
public class Customer extends Person {
    public Customer(String Fname, String Lname, DateTime dt, String address, String phone, String gender) {
        super(Fname, Lname, dt, address, phone,gender);
    }
}