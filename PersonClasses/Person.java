package pharmacy.PersonClasses;
import pharmacy.ExtraClasses.DateTime;
public abstract class Person {
    private static int counterID = 0;
    protected int ID;
    protected String firstName;
    protected String lastName;
    protected String personAddress;
    protected String personPhone;
    protected String gender;
    protected DateTime dateOfBirth;
    private int generateID(){
        counterID++;
        return counterID;
    }
    public Person() {
        ID = generateID();
        firstName = "";
        lastName = "";
        personAddress = "";
        gender = "";
        personPhone = "";
        dateOfBirth = new DateTime();
    }
  
    public Person(String Fname, String Lname, DateTime dt, String address, String phone, String gender) {
        ID = generateID();
        this.firstName = Fname;
        this.lastName = Lname;
        this.personAddress = address;
        this.personPhone = phone;
        this.gender = gender;
        dateOfBirth = dt;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    
    public void setFirstName(String Fname) {
        this.firstName = Fname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String Lname) {
        this.lastName = Lname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAddress(String address) {
        this.personAddress = address;
    }

    public String getAddress() {
        return personAddress;
    }

    public void setPhone(String phone) {
        this.personPhone = phone;
    }

    public String getPhone() {
        return personPhone;
    }

    public void setDateOfBirth(int Day, int Month, int Year) {
        dateOfBirth.setDate(Day, Month, Year);
    }
    public void setDateOfBirth(DateTime dt) {
        dateOfBirth = dt;
    }

    public DateTime getDateOfBirth(){
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    

 }