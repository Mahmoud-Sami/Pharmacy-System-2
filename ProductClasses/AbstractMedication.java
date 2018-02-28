package pharmacy.ProductClasses;
import java.util.ArrayList;
import pharmacy.ExtraClasses.DateTime;
public abstract class AbstractMedication extends Product {
    protected String purpose;
    protected String adultDose;
    protected String childDose;
    protected String activeIngredient;
    protected DateTime expiredDate;
    protected String manufacturer;
    protected ArrayList<String> sideEffect;

    public AbstractMedication(String name, int code
            , double price, int quantity, String purpose
            , String adultDose, String childDose
            , String activeIngredient, DateTime expiredDate
            , String manufacturer) 
    {
        super(name, code, price, quantity);
        this.purpose = purpose;
        this.adultDose = adultDose;
        this.childDose = childDose;
        this.activeIngredient = activeIngredient;
        this.expiredDate = expiredDate;
        this.manufacturer = manufacturer;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAdultDose() {
        return adultDose;
    }

    public void setAdultDose(String adultDose) {
        this.adultDose = adultDose;
    }

    public String getChildDose() {
        return childDose;
    }

    public void setChildDose(String childDose) {
        this.childDose = childDose;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public DateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(DateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
}