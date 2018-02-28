package pharmacy;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
//--------------------------------------------------
import java.util.ArrayList;
import pharmacy.PersonClasses.*;
import pharmacy.ExtraClasses.*;
import pharmacy.PharmacyDatabase.*;
import pharmacy.ProductClasses.*;
public class LoginAndRegister extends Application {
    static public boolean filled = false;
    public static void fillDb(){
        Person temp = new Staff("Ahmed","Mohamed",new DateTime(1,1,1,20,12,1995),"34 Elsalam St.","011543736483","Male");
        PeopleTable.addPerson(temp);
        UserTable.addUser(new User("admin","admin",User.Role.Staff,temp.getID()));
        //---------
        ProductTable.addProduct(new PrescriptionMedication("Avipect", 1435, 6.5, 3, "Productive cough","3 Times","2 Times","Y" , new DateTime(0,0,0,12,5,2019) ,"y"));
        ProductTable.addProduct(new PrescriptionMedication("Eucaphol", 3723, 8.5, 4, "Cough Sedative","2 Times","1 Times","Y" , new DateTime(0,0,0,9,3,2019) ,"z"));
        filled = true;
    }
    public static boolean userExist(String username){
        int user_n = UserTable.getUser_Numbers();
        for (int i = 0; i < user_n; i++){
            if (UserTable.getUser(i).getUsername().equalsIgnoreCase(username)){
                return true;
            }
        }
        return false;
    }
    public static boolean CheckLogin(String username, String password){
        int user_n = UserTable.getUser_Numbers();
        for (int i = 0; i < user_n; i++){
            if (UserTable.getUser(i).getUsername().equalsIgnoreCase(username)){
                if (UserTable.getUser(i).getPassword().equals(password)){
                   return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        if (!filled) fillDb();
        Stage window;
        window = primaryStage;
        window.setResizable(false);
        window.setHeight(400);
        window.setWidth(660);
        window.setTitle("Pharmacy - Login");

        //Start Login

        Image logo = new Image (getClass().getResourceAsStream("icons/1.png"));
        ImageView img1 = new ImageView(logo);
        img1.setFitHeight(200);
        img1.setPreserveRatio(true);
        img1.setLayoutX(50);
        img1.setLayoutY(90);

        Image user = new Image (getClass().getResourceAsStream("icons/user.png"));
        ImageView userIcon = new ImageView(user);
        userIcon.setFitHeight(35);
        userIcon.setPreserveRatio(true);
        userIcon.setLayoutX(290);
        userIcon.setLayoutY(110);


        Image pass = new Image (getClass().getResourceAsStream("icons/pass.png"));
        ImageView passIcon = new ImageView(pass);
        passIcon.setFitHeight(35);
        passIcon.setPreserveRatio(true);
        passIcon.setLayoutX(290);
        passIcon.setLayoutY(175);





        TextField username = new TextField ();
        username.setPromptText("Enter your Username");
        username.setLayoutX(280);
        username.setLayoutY(100);
        username.setMinWidth(300);
        username.setId("TextField");


        final PasswordField password = new PasswordField ();
        password.setPromptText("Enter your Password");
        password.setLayoutX(280);
        password.setLayoutY(165);
        password.setMinWidth(300);





        Button register = new Button("Register");
        register.setLayoutX(280);
        register.setLayoutY(232);
        register.setMinWidth(144);
        register.setId("Register");

        Button login = new Button("Login");
        login.setLayoutX(436);
        login.setLayoutY(232);
        login.setMinWidth(144);
        login.setId("Login");

        //End Login



        //Start Register


        Image newCustomer = new Image (getClass().getResourceAsStream("icons/newCustomer.png"));
        ImageView NewCustomer = new ImageView(newCustomer);
        NewCustomer.setFitHeight(100);
        NewCustomer.setPreserveRatio(true);
        NewCustomer.setLayoutX(280);
        NewCustomer.setLayoutY(30);

        Image user2 = new Image (getClass().getResourceAsStream("icons/user.png"));
        ImageView userIcon2 = new ImageView(user2);
        userIcon2.setFitHeight(35);
        userIcon2.setPreserveRatio(true);
        userIcon2.setLayoutX(90);
        userIcon2.setLayoutY(170);


        Image pass2 = new Image (getClass().getResourceAsStream("icons/pass.png"));
        ImageView passIcon2 = new ImageView(pass2);
        passIcon2.setFitHeight(35);
        passIcon2.setPreserveRatio(true);
        passIcon2.setLayoutX(350);
        passIcon2.setLayoutY(170);

        Image phoneImg = new Image (getClass().getResourceAsStream("icons/phone.png"));
        ImageView phoneIcon = new ImageView(phoneImg);
        phoneIcon.setFitHeight(34);
        phoneIcon.setPreserveRatio(true);
        phoneIcon.setLayoutX(100);
        phoneIcon.setLayoutY(242);


        Image genderImg = new Image (getClass().getResourceAsStream("icons/gender.png"));
        ImageView genderIcon = new ImageView(genderImg);
        genderIcon.setFitHeight(30);
        genderIcon.setPreserveRatio(true);
        genderIcon.setLayoutX(355);
        genderIcon.setLayoutY(245);


        Image addressImg = new Image (getClass().getResourceAsStream("icons/address.png"));
        ImageView addressIcon = new ImageView(addressImg);
        addressIcon.setFitHeight(26);
        addressIcon.setPreserveRatio(true);
        addressIcon.setLayoutX(355);
        addressIcon.setLayoutY(315);




        TextField username2 = new TextField ();
        username2.setPromptText("Enter your Username");
        username2.setLayoutX(80);
        username2.setLayoutY(160);
        username2.setMinWidth(240);

        final PasswordField password2 = new PasswordField ();
        password2.setPromptText("Enter your Password");
        password2.setLayoutX(340);
        password2.setLayoutY(160);
        password2.setMinWidth(240);



        TextField phone = new TextField ();
        phone.setPromptText("Enter your Phone");
        phone.setLayoutX(80);
        phone.setLayoutY(230);
        phone.setMinWidth(240);



        ChoiceBox gender = new ChoiceBox(FXCollections.observableArrayList("Select your Gender", "Male","Female"));
        gender.getSelectionModel().selectFirst();
        gender.setId("gender");
        gender.setLayoutX(340);
        gender.setLayoutY(230);
        gender.setMinWidth(240);


        ChoiceBox day = new ChoiceBox(FXCollections.observableArrayList("Day", "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"));
        day.getSelectionModel().selectFirst();
        day.setMaxWidth(70);
        day.setMinWidth(70);
        day.setLayoutX(80);
        day.setLayoutY(300);

        ChoiceBox month = new ChoiceBox(FXCollections.observableArrayList("Month","1","2","3","4","5","6","7","8","9","10","11","12"));
        month.getSelectionModel().selectFirst();
        month.setMaxWidth(78);
        month.setMinWidth(78);
        month.setLayoutX(160);
        month.setLayoutY(300);

        ChoiceBox year = new ChoiceBox(FXCollections.observableArrayList("Year","1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018"));
        year.getSelectionModel().selectFirst();
        year.setMaxWidth(70);
        year.setMinWidth(70);
        year.setLayoutX(248);
        year.setLayoutY(300);


        TextField address = new TextField ();
        address.setPromptText("Enter your address");
        address.setLayoutX(340);
        address.setLayoutY(300);
        address.setMinWidth(240);



        Button submit = new Button("Sing up");
        submit.setLayoutX(80);
        submit.setLayoutY(380);
        submit.setMinWidth(500);
        submit.setId("submit");


        Label backLogin = new Label("Back to Login");
        backLogin.setLayoutX(286);
        backLogin.setLayoutY(472);

        //End Register

        Pane pLogin = new Pane();
        pLogin.getChildren().setAll(img1, username, password , register, login , userIcon, passIcon);


        Pane pRegister = new Pane();
        pRegister.getChildren().setAll(NewCustomer, username2, password2 , phone, gender , day , month , year , address , submit, userIcon2, passIcon2, phoneIcon, genderIcon, addressIcon , backLogin);


        Pane Panel = new Pane();
        Panel.getChildren().setAll(pLogin);



        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        username.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                pLogin.requestFocus();
                firstTime.setValue(false);
            }
        });

        final BooleanProperty firstTime2 = new SimpleBooleanProperty(true);
        username2.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime2.get()){
                pRegister.requestFocus();
                firstTime2.setValue(false);
            }
        });


        Scene scene = new Scene(Panel);
        scene.getStylesheets().add("pharmacy/style.css");

        //Database

        //Events Start
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (CheckLogin(username.getText(),password.getText())){
                    Main.GUI(username.getText());
                    window.close();
                }
                else{
                    ErrorMsg.ShowMsg("OPS!" , "Username or password is not correct!" , ErrorMsg.type.error);
                }
            }
        });


        register.setOnMouseClicked(event -> {
            window.setHeight(550);
            Panel.getChildren().setAll(pRegister);
            window.setTitle("Pharmacy - Register");
        });


        backLogin.setOnMouseClicked(event -> {
            window.setHeight(400);
            Panel.getChildren().setAll(pLogin);
            window.setTitle("Pharmacy - Login");
        });

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (username2.getText().equals("") || password2.getText().equals("")
                    || phone.getText().equals("")  || address.getText().equals("") ){
                    ErrorMsg.ShowMsg("OPS!" , "Fill the data!" , ErrorMsg.type.error);
                    return;
                }
                if (userExist(username2.getText())){
                    ErrorMsg.ShowMsg("OPS!" , "This Username taken already, try another one!" , ErrorMsg.type.error);
                }
                else{
                    Person temp = new Customer("",""
                            , new DateTime(1,1,1,Integer.parseInt(day.getValue().toString()),Integer.parseInt(month.getValue().toString()),Integer.parseInt(year.getValue().toString()))
                            , address.getText(),phone.getText(), gender.getValue().toString()  );
                    PeopleTable.addPerson(temp);
                    UserTable.addUser(new User(username2.getText(),password2.getText(),User.Role.Customer,temp.getID()));
                    window.setHeight(400);
                    Panel.getChildren().setAll(pLogin);
                    window.setTitle("Pharmacy - Login");
                }
            }
        });

        //Events End


        window.setScene(scene);
        window.show();


    }


}