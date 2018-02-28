package pharmacy.ProductClasses;
import java.util.ArrayList;
import pharmacy.ExtraClasses.DateTime;
public class PrescriptionMedication extends AbstractMedication {
    
    public PrescriptionMedication(String name, int code, double price
            , int quantity, String purpose, String adultDose, 
            String childDose, String activeIngredient, 
            DateTime expiredDate, String manufacturer) {
        super(name, code, price, quantity, purpose
                ,adultDose ,childDose, activeIngredient
                ,expiredDate ,manufacturer);
    }
    
}