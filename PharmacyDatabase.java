package pharmacy;
import java.util.ArrayList;
import pharmacy.User;
import pharmacy.Order;
import pharmacy.ProductClasses.*;
import pharmacy.PersonClasses.*;
import pharmacy.ExtraClasses.*;
public abstract class PharmacyDatabase {
    
    public static abstract class UserTable {
        private static ArrayList < User > userTable = new ArrayList < User > ();
        public static boolean addUser(User u) {
            try{
               userTable.add(u); 
               return true;
            }
            catch(Exception e){
                return false;
            }
            
        }
        public static boolean deleteUser(String username) {
            for (int i = 0; i < userTable.size(); i++) {
                if (userTable.get(i).getUsername().equalsIgnoreCase(username)) {
                    userTable.remove(i);
                    return true;
                }
            }
            return false;
        }
        public static User getUser(String username) {
            for (int i = 0; i < userTable.size(); i++) {
                if (userTable.get(i).getUsername().equalsIgnoreCase(username)) {
                    return userTable.get(i);
                }
            }
            return null;
        }
        public static User getUser(int index) {
            try{
                return userTable.get(index);
            }
            catch(Exception e){
                return null;
            }
        }
        public static boolean updateUser_Password(String username, String password) {
            for (int i = 0; i < userTable.size(); i++) {
                if (userTable.get(i).getUsername().equalsIgnoreCase(username)) {
                    userTable.get(i).setPassword(password);
                    return true;
                }
            }
            return false;
        }
        public static boolean updateUser_Role(String username, User.Role role) {
            for (int i = 0; i < userTable.size(); i++) {
                if (userTable.get(i).getUsername() == username) {
                    userTable.get(i).setRole(role);
                    return true;
                }
            }
            return false;
        }
        public static int getUser_Numbers(){
            return userTable.size();
        }
    }
    public static abstract class OrderTable {
        private static ArrayList < Order > orderTable = new ArrayList < Order > ();
        //Methods
        public static boolean addOrder(Order o) {
            try{
                orderTable.add(o);
                return true;
            }catch(Exception e){
                return false;
            }
        }
        public static boolean deleteOrder(int orderID) {
            for (int i = 0; i < orderTable.size(); i++) {
                if (orderTable.get(i).getInvoiceId() == orderID) {
                    orderTable.remove(i);
                    return true;
                }
            }
            return false;
        }
        public static Order getOrder(int orderID) {
            for (int i = 0; i < orderTable.size(); i++) {
                if (orderTable.get(i).getInvoiceId() == orderID) {
                    return orderTable.get(i);
                }
            }
            return null;
        }
        public static boolean updateOrder_Customer(int orderID, Customer c){
            for (int i = 0; i < orderTable.size(); i++) {
                if (orderTable.get(i).getInvoiceId() == orderID) {
                    orderTable.get(i).setInvoiceCustomer(c);
                    return true;
                }
            }
            return false;
        }
        public static boolean updateOrder_Date(int orderID, DateTime dt){
            for (int i = 0; i < orderTable.size(); i++) {
                if (orderTable.get(i).getInvoiceId() == orderID) {
                    orderTable.get(i).setInvoiceDate(dt);
                    return true;
                }
            }
            return false;
        }
        public static boolean updateOrder_AddNewProduct(int orderID, Product p){
            for (int i = 0; i < orderTable.size(); i++) {
                if (orderTable.get(i).getInvoiceId() == orderID) {
                    orderTable.get(i).addProduct(p);
                    return true;
                }
            }
            return false;
        }
    }
    public static abstract class PeopleTable {
        private static ArrayList < Person > peopleTable = new ArrayList < Person > ();
        //Methods
        public static boolean addPerson(Person p) {
            try{
                peopleTable.add(p);
                return true;
            }catch(Exception e){
                return false;
            } 
        }
        public static boolean deletePerson(int personID) {
            for (int i = 0; i < peopleTable.size(); i++) {
                if (peopleTable.get(i).getID() == personID) {
                    peopleTable.remove(i);
                    return true;
                }
            }
            return false;
        }
        public static Person getPerson(int personID){
            for (int i = 0; i < peopleTable.size(); i++) {
                if (peopleTable.get(i).getID() == personID) {
                    return peopleTable.get(i);
                }
            }
            return null;
        }
        public static boolean updatePerson_firstName(int personID, String newName){
            for (int i = 0; i < peopleTable.size(); i++){
                if (peopleTable.get(i).getID() == personID){
                    peopleTable.get(i).setFirstName(newName);
                    return true;
                }
            }
            return false;
        }
        public static boolean updatePerson_lastName(int personID, String newName){
            for (int i = 0; i < peopleTable.size(); i++){
                if (peopleTable.get(i).getID() == personID){
                    peopleTable.get(i).setLastName(newName);
                    return true;
                }
            }
            return false; 
        }
        public static boolean updatePerson_Phone(int personID, String number){
            for (int i = 0; i < peopleTable.size(); i++){
                if (peopleTable.get(i).getID() == personID){
                    peopleTable.get(i).setPhone(number);
                    return true;
                }
            }
            return false;
        }
        public static boolean updatePerson_Adress(int personID, String ad){
            for (int i = 0; i < peopleTable.size(); i++){
                if (peopleTable.get(i).getID() == personID){
                    peopleTable.get(i).setAddress(ad);
                    return true;
                }
            }
            return false;
        }
        public static boolean updatePerson_DateOfBirth(int personID, DateTime dt){
            for (int i = 0; i < peopleTable.size(); i++){
                if (peopleTable.get(i).getID() == personID){
                    peopleTable.get(i).setDateOfBirth(dt);
                    return true;
                }
            }
            return false;
        }
    }
    public static abstract class ProductTable{
        private static ArrayList < Product > productTable = new ArrayList < Product > ();
        public static boolean addProduct(Product p){
            try{
                productTable.add(p);
                return true;
            }
            catch(Exception e){
                return false;
            }
        }
        public static boolean deleteProduct(int productCode){    
            for (int i = 0; i < productTable.size(); i++){
                if (productTable.get(i).getCode() == productCode){
                    productTable.remove(i);
                    return true;
                }
            }
            return false;
        }
        public static Product getProduct(int productCode){
            for (int i = 0; i < productTable.size(); i++){
                if (productTable.get(i).getCode() == productCode){
                    return productTable.get(i);
                }
            }
            return null; 
        }
        public static Product getProductOfIndex(int index){
            try{
                return productTable.get(index);
            }
            catch(Exception e){
                return null;
            }
        }
        public static boolean updateProduct_name(int productCode, String name){
            for (int i = 0; i < productTable.size(); i++){
                if (productTable.get(i).getCode() == productCode){
                    productTable.get(i).setName(name);
                    return true;
                }
            }
            return false;
        }
        public static boolean updateProduct_price(int productCode, double price){
            for (int i = 0; i < productTable.size(); i++){
                if (productTable.get(i).getCode() == productCode){
                    productTable.get(i).setPrice(price);
                    return true;
                }
            }
            return false;
        }
        public static boolean updateProduct_quantity(int productCode, int q){
            for (int i = 0; i < productTable.size(); i++){
                if (productTable.get(i).getCode() == productCode){
                    productTable.get(i).setQuantity(q);
                    return true;
                }
            }
            return false;
        }
        public static int getProducts_Number(){
            return productTable.size();
        }
    }
    
}