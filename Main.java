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
//-------------------------------------------
import pharmacy.ExtraClasses.DateTime;
//import static pharmacy.LoginAndRegister.CheckLogin; //Edited
import pharmacy.ProductClasses.PrescriptionMedication;
import pharmacy.PersonClasses.Person;
import pharmacy.ProductClasses.*;

import java.util.Hashtable;

public class Main {
    public static ObservableList<Product> data = FXCollections.observableArrayList(); //Edited


    static User currentUser;
    static Person currentCustomer;
    enum ProductType { 
        Cosmetics, Medication, PM, All 
    };

    public static boolean isValidPrescription(String Code) {
        Hashtable <String , Boolean> PrescriptionList = new Hashtable<>();
        PrescriptionList.put("12345678" , true);
        PrescriptionList.put("87654321" , true);

        if (PrescriptionList.containsKey(Code))
            return true;
        else
            return false;
    }

    public static void FillTableViewByProduct(ObservableList<Product> data, ProductType type){
        
        for (int i = 0; i < PharmacyDatabase.ProductTable.getProducts_Number(); i++){
            Product temp = PharmacyDatabase.ProductTable.getProductOfIndex(i);
            if (type == type.All) data.add(temp);
            else if (type == type.PM){
                if (temp instanceof PrescriptionMedication){
                    data.add(temp);
                }
            }
            else if (type == type.Medication){
                if (temp instanceof Medication){
                    data.add(temp);
                }
            }
            else if (type == type.Cosmetics){
                if (temp instanceof Cosmetics){
                    data.add(temp);
                }
            }
            
        }
    }

    public static ObservableList<Product> pCart_tvOrder_data = FXCollections.observableArrayList(); //Edited List My Cart

    public static boolean AddToCart(PrescriptionMedication p) {
        boolean flag = true;
        int Index = 0;
        for (Product current : pCart_tvOrder_data) {
            if (current.getName() == p.getName()) {

                if (pCart_tvOrder_data.get(Index).getQuantity() > 1 && p.getQuantity() - pCart_tvOrder_data.get(Index).getQuantity() < 8) {
                    String msg = "You can't order more than " + pCart_tvOrder_data.get(Index).getQuantity() + " of this product";
                    ErrorMsg.ShowMsg("Error", msg , ErrorMsg.type.error);
                    return false;
                } else {
                    pCart_tvOrder_data.set(Index, new PrescriptionMedication(p.getName(), p.getCode(), p.getPrice(), current.getQuantity() + 1, p.getPurpose(), p.getAdultDose(), p.getChildDose(), p.getActiveIngredient(), p.getExpiredDate(), p.getManufacturer()));
                    if (p.getQuantity() - pCart_tvOrder_data.get(Index).getQuantity() == 0) {
                        data.remove(p);
                    }
                    return true;
                }
            }
            Index++;
        }
        pCart_tvOrder_data.add(new PrescriptionMedication(p.getName(), p.getCode(), p.getPrice(), 1, p.getPurpose(), p.getAdultDose(), p.getChildDose(), p.getActiveIngredient(), p.getExpiredDate(), p.getManufacturer()));
        if (p.getQuantity() - pCart_tvOrder_data.get(Index).getQuantity() == 0) {
            data.remove(p);
        }
        return true;
    }

    public static void GUI(String currentuser) {
        currentUser = PharmacyDatabase.UserTable.getUser(currentuser);
        currentCustomer = PharmacyDatabase.PeopleTable.getPerson(currentUser.getUserID());
        Stage window = new Stage();
        window.setResizable(false);
        window.setTitle("Pharmacy - Prescription Medications");


        //Start Sidebar

        Image UserImg = new Image (Main.class.getResourceAsStream("icons/UserIcon.png"));
        ImageView UserIcon = new ImageView(UserImg);
        UserIcon.setPreserveRatio(true);
        UserIcon.setFitHeight(25);
        UserIcon.setLayoutX(25);
        UserIcon.setLayoutY(25);
        
        Label Username = new Label(currentCustomer.getFirstName() + " " + currentCustomer.getLastName());
        Username.setId("username");
        Username.setLayoutX(70);
        Username.setLayoutY(25);

        Pane panel_user = new Pane();
        panel_user.setId("UserArea");
        panel_user.setLayoutX(0);
        panel_user.setLayoutY(0);
        panel_user.getChildren().setAll(UserIcon , Username);


        Pane panel_selected = new Pane();
        panel_selected.setMinHeight(45);
        panel_selected.setMinWidth(6);
        panel_selected.setStyle("-fx-background-color: #e2574c;");
        panel_selected.setLayoutY(100);


        Button label1 = new Button("Prescription Medications");
        label1.setId("cat");
        label1.setLayoutX(70);
        label1.setLayoutY(95);
        label1.setMinWidth(180);
        label1.setMinHeight(55);


        Button label2 = new Button("Medications");
        label2.setId("cat");
        label2.setLayoutX(70);
        label2.setLayoutY(155);
        label2.setMinWidth(180);
        label2.setMinHeight(55);

        Button label3 = new Button("Cosmetics");
        label3.setId("cat");
        label3.setLayoutX(70);
        label3.setLayoutY(215);
        label3.setMinWidth(180);
        label3.setMinHeight(55);

        Button label4 = new Button("Orders History");
        label4.setId("cat");
        label4.setLayoutX(70);
        label4.setLayoutY(275);
        label4.setMinWidth(180);
        label4.setMinHeight(55);

        Button label5 = new Button("Settings");
        label5.setId("cat");
        label5.setLayoutX(70);
        label5.setLayoutY(335);
        label5.setMinWidth(180);
        label5.setMinHeight(55);


        Button label6 = new Button("Logout");
        label6.setId("cat");
        label6.setLayoutX(70);
        label6.setLayoutY(395);
        label6.setMinWidth(180);
        label6.setMinHeight(55);


        Image img1 = new Image (Main.class.getResourceAsStream("icons/1.png"));
        ImageView Icon1 = new ImageView(img1);
        Icon1.setPreserveRatio(true);
        Icon1.setFitHeight(25);
        Icon1.setLayoutX(25);
        Icon1.setLayoutY(110);


        Image img2 = new Image (Main.class.getResourceAsStream("icons/2.png"));
        ImageView Icon2 = new ImageView(img2);
        Icon2.setPreserveRatio(true);
        Icon2.setFitHeight(25);
        Icon2.setLayoutX(25);
        Icon2.setLayoutY(170);


        Image img3 = new Image (Main.class.getResourceAsStream("icons/3.png"));
        ImageView Icon3 = new ImageView(img3);
        Icon3.setPreserveRatio(true);
        Icon3.setFitHeight(25);
        Icon3.setLayoutX(25);
        Icon3.setLayoutY(230);

        Image img4 = new Image (Main.class.getResourceAsStream("icons/4.png"));
        ImageView Icon4 = new ImageView(img4);
        Icon4.setPreserveRatio(true);
        Icon4.setFitHeight(25);
        Icon4.setLayoutX(25);
        Icon4.setLayoutY(290);

        Image img5 = new Image (Main.class.getResourceAsStream("icons/5.png"));
        ImageView Icon5 = new ImageView(img5);
        Icon5.setPreserveRatio(true);
        Icon5.setFitHeight(25);
        Icon5.setLayoutX(25);
        Icon5.setLayoutY(350);

        Image img6 = new Image (Main.class.getResourceAsStream("icons/6.png"));
        ImageView Icon6 = new ImageView(img6);
        Icon6.setPreserveRatio(true);
        Icon6.setFitHeight(25);
        Icon6.setLayoutX(25);
        Icon6.setLayoutY(410);


        Pane panel_menu = new Pane();
        panel_menu.getChildren().addAll(panel_selected, panel_user, label1 , label2 , label3 , label4 , label5 , label6 , Icon1 , Icon2 , Icon3 , Icon4 , Icon5 , Icon6);
        panel_menu.setId("Sidebar");
        panel_menu.setMinWidth(250);
        panel_menu.setMinHeight(600);
        panel_menu.setLayoutX(0);
        panel_menu.setLayoutY(0);

        //End Sidebar


        //Start Body


        //Start Page1
        
        //New
        Button btnAdd_PM = new Button("Add To Cart");
        btnAdd_PM.setId("addtocartBtn");
        btnAdd_PM.setLayoutX(20);
        btnAdd_PM.setLayoutY(396);
        btnAdd_PM.setMinWidth(200);
        btnAdd_PM.setMaxHeight(40);
        btnAdd_PM.setMinHeight(40);
        btnAdd_PM.setDisable(true);
        //End New
        Label Title1 = new Label("Prescription Medications");
        Title1.setId("Title");
        Title1.setLayoutY(25);

        Pane topPage1 = new Pane();
        topPage1.setId("topPage");
        topPage1.getChildren().setAll(Title1);
        Title1.layoutXProperty().bind(topPage1.widthProperty().subtract(Title1.widthProperty()).divide(2));

        if (OrderConfirmation.NeverMadeOrderBefore)
            FillTableViewByProduct(data, ProductType.PM); // Edited

        TableView table = new TableView();
        table.setId("productsTable");
        table.setPlaceholder(new Label("There is no products !"));
        table.setLayoutX(20);
        table.setLayoutY(100);
        table.setMinWidth(624); //Edited
        table.setMaxWidth(624); //Edited
        table.setMinHeight(278); //Edited
        table.setMaxHeight(278); //Edited
        table.setEditable(true);


        TableColumn name = new TableColumn("Product name");
        name.setCellValueFactory(new PropertyValueFactory<PrescriptionMedication, String>("name"));
        name.setMinWidth(110);
        name.setMaxWidth(110);

        TableColumn code = new TableColumn("Code");
        code.setCellValueFactory(new PropertyValueFactory<PrescriptionMedication, Integer>("code"));
        code.setMinWidth(102);
        code.setMaxWidth(102);


        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<PrescriptionMedication, Double>("price"));
        price.setMinWidth(102);
        price.setMaxWidth(102);

        /*TableColumn quantity = new TableColumn("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<PrescriptionMedication, Integer>("quantity"));
        quantity.setMinWidth(66);
        quantity.setMaxWidth(66);*/

        TableColumn purpose = new TableColumn("Purpose");
        purpose.setCellValueFactory(new PropertyValueFactory<PrescriptionMedication, String>("purpose"));
        purpose.setMinWidth(102);
        purpose.setMaxWidth(102);

        TableColumn adultDose = new TableColumn("Adult dose");
        adultDose.setCellValueFactory(new PropertyValueFactory<PrescriptionMedication, String>("adultDose"));
        adultDose.setMinWidth(102);
        adultDose.setMaxWidth(102);

        TableColumn childDose = new TableColumn("Child dose");
        childDose.setCellValueFactory(new PropertyValueFactory<PrescriptionMedication, String>("childDose"));
        childDose.setMinWidth(102);
        childDose.setMaxWidth(102);

        /*TableColumn activeIngredient = new TableColumn("Active Ingredient");
        activeIngredient.setCellValueFactory(new PropertyValueFactory<PrescriptionMedication, String>("activeIngredient"));
        activeIngredient.setMinWidth(75);
        activeIngredient.setMaxWidth(75);*/

        /*TableColumn expiredDateS = new TableColumn("Expired");
        expiredDateS.setCellValueFactory(new PropertyValueFactory<PrescriptionMedication, String>("expiredDateS"));
        expiredDateS.setMinWidth(80);
        expiredDateS.setMaxWidth(80);*/

        /*TableColumn manufacturer = new TableColumn("Manufacturer");
        manufacturer.setCellValueFactory(new PropertyValueFactory<PrescriptionMedication, String>("manufacturer"));
        manufacturer.setMinWidth(75);
        manufacturer.setMaxWidth(75);*/


        table.getColumns().setAll(name,code,price,purpose,adultDose,childDose);
        table.setItems(data);


        Image PrescriptionIcon = new Image (Main.class.getResourceAsStream("icons/PrescriptionIcon.png"));
        ImageView PrescriptionImg = new ImageView(PrescriptionIcon);
        PrescriptionImg.setFitHeight(25);
        PrescriptionImg.setPreserveRatio(true);
        PrescriptionImg.setLayoutX(454);
        PrescriptionImg.setLayoutY(404);

        TextField PrescriptionCode = new TextField();
        PrescriptionCode.setPromptText("Enter Prescription Code");
        PrescriptionCode.setMaxHeight(40);
        PrescriptionCode.setMinHeight(40);
        PrescriptionCode.setLayoutX(444);
        PrescriptionCode.setLayoutY(396);
        PrescriptionCode.setMinWidth(200);
        PrescriptionCode.setStyle("-fx-padding: 2px 14px 2px 40px !important;");




        Pane Page1 = new Pane();
        Page1.setLayoutX(250);
        Page1.setId("Page1");
        Page1.getChildren().setAll(topPage1 , table , PrescriptionCode ,PrescriptionImg , btnAdd_PM);


        //End Page1



        //Start Page2

        Label Title2 = new Label("Medications");
        Title2.setId("Title");
        Title2.setLayoutY(25);
        Title2.setLayoutX(200);

        Pane topPage2 = new Pane();
        topPage2.setId("topPage");
        topPage2.getChildren().setAll(Title2);
        Title2.layoutXProperty().bind(topPage2.widthProperty().subtract(Title2.widthProperty()).divide(2));


        Pane Page2 = new Pane();
        Page2.setLayoutX(250);
        Page2.setId("Page2");
        Page2.getChildren().setAll(topPage2);

        //End Page2


        //Start Page3

        Label Title3 = new Label("Cosmetics");
        Title3.setId("Title");
        Title3.setLayoutY(25);
        Title3.setLayoutX(200);

        Pane topPage3 = new Pane();
        topPage3.setId("topPage");
        topPage3.getChildren().setAll(Title3);
        Title3.layoutXProperty().bind(topPage3.widthProperty().subtract(Title3.widthProperty()).divide(2));


        Pane Page3 = new Pane();
        Page3.setLayoutX(250);
        Page3.setId("Page3");
        Page3.getChildren().setAll(topPage3);

        //End Page3


        //Start Page4

        Label Title4 = new Label("Orders History");
        Title4.setId("Title");
        Title4.setLayoutY(25);
        Title4.setLayoutX(200);

        Pane topPage4 = new Pane();
        topPage4.setId("topPage");
        topPage4.getChildren().setAll(Title4);
        Title4.layoutXProperty().bind(topPage4.widthProperty().subtract(Title4.widthProperty()).divide(2));


        Pane Page4 = new Pane();
        Page4.setLayoutX(250);
        Page4.setId("Page4");
        Page4.getChildren().setAll(topPage4);

        //End Page4



        //Start Page5

        Label Title5 = new Label("Settings");
        Title5.setId("Title");
        Title5.setLayoutY(25);
        Title5.setLayoutX(200);

        Pane topPage5 = new Pane();
        topPage5.setId("topPage");
        topPage5.getChildren().setAll(Title5);
        Title5.layoutXProperty().bind(topPage5.widthProperty().subtract(Title5.widthProperty()).divide(2));



        Image newCustomer = new Image (Main.class.getResourceAsStream("icons/editCustomer.png"));
        ImageView NewCustomer = new ImageView(newCustomer);
        NewCustomer.setFitHeight(100);
        NewCustomer.setPreserveRatio(true);
        NewCustomer.setLayoutX(280);
        NewCustomer.setLayoutY(110);

        Image user2 = new Image (Main.class.getResourceAsStream("icons/user.png"));
        ImageView userIcon2 = new ImageView(user2);
        userIcon2.setFitHeight(35);
        userIcon2.setPreserveRatio(true);
        userIcon2.setLayoutX(90);
        userIcon2.setLayoutY(250);


        Image pass2 = new Image (Main.class.getResourceAsStream("icons/pass.png"));
        ImageView passIcon2 = new ImageView(pass2);
        passIcon2.setFitHeight(35);
        passIcon2.setPreserveRatio(true);
        passIcon2.setLayoutX(350);
        passIcon2.setLayoutY(250);

        Image phoneImg = new Image (Main.class.getResourceAsStream("icons/phone.png"));
        ImageView phoneIcon = new ImageView(phoneImg);
        phoneIcon.setFitHeight(34);
        phoneIcon.setPreserveRatio(true);
        phoneIcon.setLayoutX(100);
        phoneIcon.setLayoutY(322);


        Image genderImg = new Image (Main.class.getResourceAsStream("icons/gender.png"));
        ImageView genderIcon = new ImageView(genderImg);
        genderIcon.setFitHeight(30);
        genderIcon.setPreserveRatio(true);
        genderIcon.setLayoutX(355);
        genderIcon.setLayoutY(325);


        Image addressImg = new Image (Main.class.getResourceAsStream("icons/address.png"));
        ImageView addressIcon = new ImageView(addressImg);
        addressIcon.setFitHeight(26);
        addressIcon.setPreserveRatio(true);
        addressIcon.setLayoutX(355);
        addressIcon.setLayoutY(395);




        TextField p5_txtUsername = new TextField (currentUser.getUsername());
        p5_txtUsername.setEditable(false);
        p5_txtUsername.setLayoutX(80);
        p5_txtUsername.setLayoutY(240);
        p5_txtUsername.setMinWidth(240);

        final PasswordField p5_txtPassword = new PasswordField ();
        p5_txtPassword.setPromptText("Enter new Password");
        p5_txtPassword.setEditable(false);
        p5_txtPassword.setLayoutX(340);
        p5_txtPassword.setLayoutY(240);
        p5_txtPassword.setMinWidth(240);



        TextField p5_txtPhone = new TextField (currentCustomer.getPhone());
        p5_txtPhone.setEditable(false);
        p5_txtPhone.setLayoutX(80);
        p5_txtPhone.setLayoutY(310);
        p5_txtPhone.setMinWidth(240);



        ChoiceBox p5_CbGender = new ChoiceBox(FXCollections.observableArrayList("Male","Female"));
        if (currentCustomer.getGender().equalsIgnoreCase("Male"))
            p5_CbGender.getSelectionModel().select(0);
        else
            p5_CbGender.getSelectionModel().select(1);
        p5_CbGender.setId("gender");
        p5_CbGender.setDisable(true);
        p5_CbGender.setLayoutX(340);
        p5_CbGender.setLayoutY(310);
        p5_CbGender.setMinWidth(240);


        ChoiceBox p5_CbDay = new ChoiceBox(FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"));
        p5_CbDay.getSelectionModel().select(currentCustomer.getDateOfBirth().getDay()-1);
        p5_CbDay.setDisable(true);
        p5_CbDay.setMaxWidth(70);
        p5_CbDay.setMinWidth(70);
        p5_CbDay.setLayoutX(80);
        p5_CbDay.setLayoutY(380);

        ChoiceBox p5_CbMonth = new ChoiceBox(FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12"));
        p5_CbMonth.getSelectionModel().select(currentCustomer.getDateOfBirth().getMonth()-1);
        p5_CbMonth.setDisable(true);
        p5_CbMonth.setMaxWidth(78);
        p5_CbMonth.setMinWidth(78);
        p5_CbMonth.setLayoutX(160);
        p5_CbMonth.setLayoutY(380);

        ChoiceBox p5_CbYear = new ChoiceBox(FXCollections.observableArrayList("1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018"));
        p5_CbYear.getSelectionModel().select(currentCustomer.getDateOfBirth().getYear()-1970);
        p5_CbYear.setDisable(true);
        p5_CbYear.setMaxWidth(70);
        p5_CbYear.setMinWidth(70);
        p5_CbYear.setLayoutX(248);
        p5_CbYear.setLayoutY(380);


        TextField p5_txtAddress = new TextField (currentCustomer.getAddress());
        p5_txtAddress.setEditable(false);
        p5_txtAddress.setLayoutX(340);
        p5_txtAddress.setLayoutY(380);
        p5_txtAddress.setMinWidth(240);



        Button p5_btnSubmit = new Button("Save");
        p5_btnSubmit.setDisable(true);
        p5_btnSubmit.setLayoutX(340);
        p5_btnSubmit.setLayoutY(460);
        p5_btnSubmit.setMinWidth(240);
        p5_btnSubmit.setId("submit");
        
        Button p5_btnEdit = new Button("Edit");
        p5_btnEdit.setLayoutX(80);
        p5_btnEdit.setLayoutY(460);
        p5_btnEdit.setMinWidth(240);
        p5_btnEdit.setId("edit");
        
        Button p5_btnCancel = new Button("Cancel");
        p5_btnCancel.setLayoutX(80);
        p5_btnCancel.setLayoutY(460);
        p5_btnCancel.setMinWidth(240);
        p5_btnCancel.setVisible(false);
        p5_btnCancel.setId("cancel2");
        
        Pane Page5 = new Pane();
        Page5.setLayoutX(250);
        Page5.setId("Page5");
        Page5.getChildren().setAll(topPage5 , NewCustomer, p5_txtUsername, p5_txtPassword , p5_txtPhone, p5_CbGender , p5_CbDay , p5_CbMonth , p5_CbYear , p5_txtAddress, p5_btnCancel, p5_btnEdit, p5_btnSubmit, userIcon2, passIcon2, phoneIcon, genderIcon, addressIcon);

        //End Page5


        //End Body


        //Start My Cart


        Image CartImg = new Image (Main.class.getResourceAsStream("icons/mycart.png"));
        ImageView CartIcon = new ImageView(CartImg);
        CartIcon.setPreserveRatio(true);
        CartIcon.setFitHeight(25);
        CartIcon.setLayoutX(25);
        CartIcon.setLayoutY(25);

        Label Mycart = new Label("My Cart");
        Mycart.setId("mycart");
        Mycart.setLayoutX(60);
        Mycart.setLayoutY(25);

        Pane panel_cartArea = new Pane();
        panel_cartArea.setId("CartArea");
        panel_cartArea.setLayoutX(0);
        panel_cartArea.setLayoutY(0);
        panel_cartArea.getChildren().setAll(CartIcon , Mycart);

        Label TotalDue = new Label("Total Due : 0 LE");
        TotalDue.setLayoutX(20);
        TotalDue.setLayoutY(396);
        TotalDue.setStyle("-fx-text-fill: #fff;");


        Button confirm = new Button("Confirm Order");
        confirm.setId("confirm");
        confirm.setLayoutX(20);
        confirm.setLayoutY(440);
        confirm.setMinWidth(210);
        confirm.setMinHeight(40);
        confirm.setMaxHeight(40);
        confirm.setDisable(true);

        Button cancel = new Button("Clear");
        cancel.setId("cancel");
        cancel.setLayoutX(20);
        cancel.setLayoutY(490); //Edited
        cancel.setMinWidth(210);
        cancel.setMinHeight(40);
        cancel.setMaxHeight(40);
        cancel.setDisable(true);

        TableView pCart_tvOrder = new TableView();
        pCart_tvOrder.setPlaceholder(new Label("Your cart is empty !"));

        pCart_tvOrder.setStyle(" -fx-border-width: 0 !important;");
        pCart_tvOrder.setLayoutX(20);
        pCart_tvOrder.setLayoutY(100);
        pCart_tvOrder.setMinWidth(210); //Edited
        pCart_tvOrder.setMaxWidth(210); //Edited
        pCart_tvOrder.setMinHeight(276); //Edited
        pCart_tvOrder.setMaxHeight(276); //Edited
        pCart_tvOrder.setEditable(true);

        TableColumn ProductName = new TableColumn("Product");
        ProductName.setMinWidth(80);
        ProductName.setMaxWidth(80);
        TableColumn ProductPrice = new TableColumn("Price");
        ProductPrice.setMinWidth(55);
        ProductPrice.setMaxWidth(55);
        TableColumn ProductQuantity = new TableColumn("Quantity");
        ProductQuantity.setMinWidth(73);
        ProductQuantity.setMaxWidth(73);

        ProductName.setCellValueFactory( new PropertyValueFactory<Person,String>("name"));
        ProductPrice.setCellValueFactory(new PropertyValueFactory<Person,String>("price"));
        ProductQuantity.setCellValueFactory(new PropertyValueFactory<Person,String>("quantity"));

        pCart_tvOrder.getColumns().addAll(ProductName, ProductPrice, ProductQuantity);
        pCart_tvOrder.setItems(pCart_tvOrder_data);

        Pane MyCart = new Pane();
        MyCart.getChildren().addAll(panel_cartArea , TotalDue , confirm, cancel, pCart_tvOrder);
        MyCart.setId("Sidebar");
        MyCart.setMinWidth(250);
        MyCart.setMinHeight(600);
        MyCart.setLayoutX(910);
        MyCart.setLayoutY(0);


        //End My Cart


        Pane panel = new Pane();
        panel.setId("Panel");
        panel.getChildren().setAll(panel_menu , Page1 , MyCart);


        Scene scene = new Scene(panel , 1160, 600);
        scene.getStylesheets().add("pharmacy/style.css");
        window.setScene(scene);
        window.show();


        label1.setOnMouseClicked(event -> {
            panel.getChildren().setAll(panel_menu , Page1 , MyCart);
            panel_selected.setLayoutY(100);
            window.setTitle("Pharmacy - Prescription Medications");
        });


        label2.setOnMouseClicked(event -> {
            panel.getChildren().setAll(panel_menu , Page2 , MyCart);
            panel_selected.setLayoutY(160);
            window.setTitle("Pharmacy - Medications");
        });



        label3.setOnMouseClicked(event -> {
            panel.getChildren().setAll(panel_menu , Page3 , MyCart);
            panel_selected.setLayoutY(220);
            window.setTitle("Pharmacy - Cosmetics");
        });



        label4.setOnMouseClicked(event -> {
            panel.getChildren().setAll(panel_menu , Page4 , MyCart);
            panel_selected.setLayoutY(280);
            window.setTitle("Pharmacy - Orders History");
        });


        label5.setOnMouseClicked(event -> {
            panel.getChildren().setAll(panel_menu , Page5 , MyCart);
            panel_selected.setLayoutY(340);
            window.setTitle("Pharmacy - Settings");
        });


        label6.setOnMouseClicked(event -> {
            if (ErrorMsg.ShowMsg("Logout ?","Are you sure you want to log out and quit ?",ErrorMsg.type.warning)) {
                pCart_tvOrder_data.clear();
                if (OrderConfirmation.NeverMadeOrderBefore)
                    data.clear();
                LoginAndRegister obj = new LoginAndRegister();
                obj.start(new Stage());
                window.hide();
            }
        });


        btnAdd_PM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    if (isValidPrescription(PrescriptionCode.getText())) {
                        PrescriptionMedication p = (PrescriptionMedication) table.getSelectionModel().getSelectedItem();
                        if (AddToCart(p)) {
                            confirm.setDisable(false);
                            cancel.setDisable(false);
                            double total = 0.0;
                            for (Product item : pCart_tvOrder_data) {
                                total += item.getPrice() * item.getQuantity();
                            }
                            TotalDue.setText("Total Due : " + String.valueOf(total) + " LE");
                        }
                    } else {
                        if (PrescriptionCode.getText().equals("")) {
                            ErrorMsg.ShowMsg("Ops !" , "You must enter prescription code !" , ErrorMsg.type.error);
                        } else if (PrescriptionCode.getText().length() != 8) {
                            ErrorMsg.ShowMsg("Ops !" , "Prescription code must be 8 digits only !" , ErrorMsg.type.error);
                        } else {
                            ErrorMsg.ShowMsg("Ops !" , "Prescription code is invalid !" , ErrorMsg.type.error);
                        }
                    }
                }
            }
        });


        confirm.setOnMouseClicked(event -> {
            OrderConfirmation.GUI(pCart_tvOrder_data);
            TotalDue.setText("Total Due : 0 LE");
            confirm.setDisable(true);
            cancel.setDisable(true);
        });


        cancel.setOnMouseClicked(event -> {
            pCart_tvOrder_data.clear();
            data.clear();
            TotalDue.setText("Total Due : 0 LE");
            confirm.setDisable(true);
            cancel.setDisable(true);
            btnAdd_PM.setDisable(true);
            FillTableViewByProduct(data,ProductType.PM);
        });

        table.setOnMouseClicked(event -> {
            if (table.getSelectionModel().getSelectedItem() != null) {
                btnAdd_PM.setDisable(false);
            }
        });

        p5_btnEdit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                p5_CbDay.setDisable(false);
                p5_CbGender.setDisable(false);
                p5_CbMonth.setDisable(false);
                p5_CbYear.setDisable(false);
                p5_btnSubmit.setDisable(false);
                p5_txtAddress.setEditable(true);
                p5_txtPassword.setEditable(true);
                p5_txtPhone.setEditable(true);
                p5_txtUsername.setEditable(true);
                
                p5_btnEdit.setDisable(true);
                p5_btnEdit.setVisible(false);
                p5_btnCancel.setVisible(true);
                p5_btnCancel.setDisable(false);
                panel_menu.setDisable(true);
                
            }
        });
        
        p5_btnCancel.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                p5_CbDay.setDisable(true);
                p5_CbGender.setDisable(true);
                p5_CbMonth.setDisable(true);
                p5_CbYear.setDisable(true);
                p5_btnSubmit.setDisable(true);
                p5_txtAddress.setEditable(false);
                p5_txtPassword.setEditable(false);
                p5_txtPhone.setEditable(false);
                p5_txtUsername.setEditable(false);
                
                p5_btnCancel.setDisable(true);
                p5_btnCancel.setVisible(false);
                p5_btnEdit.setVisible(true);
                p5_btnEdit.setDisable(false);
                panel_menu.setDisable(false);
                
                //Old Values
                p5_txtAddress.setText(currentCustomer.getAddress());
                p5_txtPassword.clear();
                p5_txtPhone.setText(currentCustomer.getPhone());
                p5_txtUsername.setText(currentUser.getUsername());
                if (currentCustomer.getGender().equalsIgnoreCase("Male"))
                     p5_CbGender.getSelectionModel().select(0);
                else
                     p5_CbGender.getSelectionModel().select(1);
                p5_CbDay.getSelectionModel().select(currentCustomer.getDateOfBirth().getDay()-1);
                p5_CbMonth.getSelectionModel().select(currentCustomer.getDateOfBirth().getMonth()-1);
                p5_CbYear.getSelectionModel().select(currentCustomer.getDateOfBirth().getYear()-1970);
                
            }
        });
    }

}
