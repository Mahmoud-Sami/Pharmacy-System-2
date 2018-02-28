package pharmacy;
public class User {
    //------------Attributes---------//
    public static enum Role{
        Staff, Delivery, Customer
    }
    private final int personID;
    private String username;
    private String password;
    private Role role;
    //--------------Methods----------//
    //Constructor
    public User(String username, String password, Role role, int id) {
        this.personID = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    //Get & Set Methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public int getUserID(){
        return this.personID;
    }
    //Other Functions
    
    
}
