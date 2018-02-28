package pharmacy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import pharmacy.ExtraClasses.DateTime;
//import static pharmacy.LoginAndRegister.CheckLogin; //Edited
import pharmacy.ProductClasses.PrescriptionMedication;
import pharmacy.PersonClasses.Person;
import pharmacy.Order;
import pharmacy.ProductClasses.Product;

public class OrderConfirmation {

    static boolean NeverMadeOrderBefore = true;
    public static void ChangeDB(ObservableList<Product> Cart) {
        for (int j = 0; j < Cart.size(); j++) {
            int qty = PharmacyDatabase.ProductTable.getProduct(Cart.get(j).getCode()).getQuantity();
            PharmacyDatabase.ProductTable.updateProduct_quantity(Cart.get(j).getCode(),qty-Cart.get(j).getQuantity());
        }

    }

    public static void GUI(ObservableList<Product> Cart) {
        ChangeDB(Cart);
        Cart.clear();
        NeverMadeOrderBefore = false;
        ErrorMsg.ShowMsg("DONE", "Your order has be confirmed successfully !" , ErrorMsg.type.success);
    }
}