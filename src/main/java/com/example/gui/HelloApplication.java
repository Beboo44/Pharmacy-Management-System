package com.example.gui;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.mycompany.pharmacy.*;
import javafx.util.Duration;
import javafx.geometry.HPos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class HelloApplication extends Application {


    Stage stage = new Stage();

    private Background createBackground() {
        Image backgroundImage = new Image("file:C:\\Users\\pc\\Pictures\\background.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );
        return new Background(bgImage);
    }



    private Scene loginScene( int num) {
        // Create layout
        GridPane p = new GridPane();
        p.setVgap(10);
        p.setHgap(10);
        p.setPadding(new Insets(20));
        p.setAlignment(Pos.CENTER);
        p.setBackground(createBackground());
        // Labels
        Label t_email = new Label("Email:");
        Label t_password = new Label("Password:");

        // Input fields
        TextField tf_email = new TextField();
        tf_email.setPromptText("user@domain.com");

        PasswordField tf_password = new PasswordField();

        // Login button
        Button b_next = new Button("Next");
        b_next.setStyle("-fx-background-color: #aac1dd; -fx-text-fill: white; -fx-background-radius: 10;");
        b_next.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        b_next.setPrefSize(100, 50);

        // Add controls to layout
        p.add(t_email, 0, 0);
        p.add(tf_email, 1, 0);
        p.add(t_password, 0, 1);
        p.add(tf_password, 1, 1);
        p.add(b_next, 1, 2);
        GridPane.setHalignment(b_next, HPos.RIGHT);

        // Create login scene
        Scene loginScene = new Scene(p, 1280, 720);

        // Login logic
        Runnable loginAction = () -> {
            String email = tf_email.getText().trim();
            String password = tf_password.getText().trim();

            // Reset styles
            tf_email.setStyle("");
            tf_password.setStyle("");


            if (email.isEmpty() || password.isEmpty()) {
                if (email.isEmpty()) tf_email.setStyle("-fx-border-color: red;");
                if (password.isEmpty()) tf_password.setStyle("-fx-border-color: red;");
                System.out.println("Please fill in all fields.");
                return;
            }

            if(num==0){
                if (!email.equalsIgnoreCase(Manager.getManager().getEmail())) {
                    tf_email.setStyle("-fx-border-color: red;");
                    tf_email.clear();
                    System.out.println("Incorrect email");
                    return;
                }
                if (!password.equals(Manager.getManager().getPassword())) {
                    tf_password.setStyle("-fx-border-color: red;");
                    tf_password.clear();
                    System.out.println("Incorrect password");
                    return;
                }
                currentPerson = Manager.getManager();
                // Login successful
                System.out.println("Logging in...");
                stage.setScene(managerScene());
            }
            else{
                ArrayList<Pharmacist> ph= Person.getSystem().getPharmacistsList();
                int found = 0;
                for(int i = 0; i<ph.size(); ++i){
                    if (email.equalsIgnoreCase(ph.get(i).getEmail()) && password.equals(ph.get(i).getPassword())) {
                        currentPerson = ph.get(i);
                        found = 1;
                        break;
                    }
                }
                if(found==0){
                    tf_email.setStyle("-fx-border-color: red;");
                    tf_email.clear();
                    System.out.println("Incorrect email");
                    tf_password.setStyle("-fx-border-color: red;");
                    tf_password.clear();
                    System.out.println("Incorrect password");
                    return;
                }
                // Login successful
                System.out.println("Logging in...");
                stage.setScene(pharmacistScene());
            }


        };

        // Handle button and Enter key
        b_next.setOnAction(e -> loginAction.run());
        tf_password.setOnAction(e -> loginAction.run());

        // Show login scene
        return loginScene;
    }



    Scene clientScene(){

        Image backgroundImage = new Image("file:C:\\Users\\pc\\Pictures\\background.jpg");
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        Button showProducts = new Button("Show available products");
        Button goback = new Button("Choose another role");
        showProducts.setFont(Font.font("Times New Roman", FontWeight.BOLD, 38));
        goback.setFont(Font.font("Times New Roman", FontWeight.BOLD, 38));
        showProducts.setPrefSize(450, 200);
        goback.setPrefSize(450, 200);
        GridPane pane = new GridPane();
        pane.add(showProducts, 0, 1);
        pane.add(goback, 0, 2);
        showProducts.setStyle("-fx-background-color: #8aa2d4; -fx-text-fill: white;-fx-background-radius: 60;");
        goback.setStyle("-fx-background-color: #8aa2d4; -fx-text-fill: white;-fx-background-radius: 60;");
        pane.setAlignment(Pos.CENTER);
        VBox layout = new VBox(40);
        layout.setBackground(new Background(bgImage));
        layout.getChildren().addAll(showProducts, goback);
        layout.setAlignment(Pos.CENTER);
        clientOptions = new Scene(layout, 1280, 720);
        showProducts.setOnAction(e -> stage.setScene(showScene));
        goback.setOnAction(e -> stage.setScene(homePage()));

        //*Show Scene*//
        TableView<Product> tableView = new TableView<>();

        // ID Column
        TableColumn<Product, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        idCol.setPrefWidth(250);

        //name coloumn
        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProductName()));
        nameCol.setPrefWidth(450);

        // Price Column
        TableColumn<Product, Double> priceCol = new TableColumn<>("Price ($)");
        priceCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPrice()));
        priceCol.setPrefWidth(300);

        // Add columns
        tableView.getColumns().addAll(idCol, nameCol, priceCol);
        tableView.setPrefWidth(1000);
        tableView.setMaxWidth(1000);

        // Sample product list
        ObservableList<Product> productList = FXCollections.observableArrayList(
                new SelfCare("shampoo", 50, 4.99, 101, "body", "Bath", 250, LocalDate.of(2025, 12, 31)),
                new SelfCare("Face Cream", 20, 15.99, 102, "Cosmetics", "Skincare", 100, LocalDate.of(2026, 6, 30)),
                new Device("Digital Thermometer", 10, 29.99, 201, "Temperature", "Health Device",12),

                new Medicine("Aspirin", 100, 5.50,"k", false,9,"Acetylsalicylic Acid", 1001, LocalDate.of(2026, 8, 15)),
                new Medicine("PowerCapsule", 80, 8.99,"k" ,false,9,"Ibuprofen",345, LocalDate.of(2025, 11, 30)),
                new Medicine("Paracetamol", 120, 4.75,"k", false,9,"Acetaminophen",76, LocalDate.of(2027, 2, 28)),
                new FirstAid("Sterile Bandage", 30, 2.99, 301,true,"clean wound", LocalDate.of(2027, 10, 15))


        );
        tableView.setItems(productList);


        Button backBtn = new Button("← Back");
        backBtn.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
        backBtn.setStyle("-fx-background-color: #8aa2d4; -fx-text-fill: white;-fx-background-radius: 60;");

        backBtn.setPrefWidth(100);
        backBtn.setOnAction(e -> stage.setScene(clientOptions));
        HBox topBar=new HBox(backBtn);
        topBar.setAlignment(Pos.TOP_RIGHT);

        BorderPane root = new BorderPane();

        topBar.setPadding(new Insets(20, 20, 10, 20));
        root.setTop(topBar);
        VBox centerPane = new VBox(tableView);
        centerPane.setAlignment(Pos.CENTER);
        root.setCenter(centerPane);

        showScene = new Scene(root,1280, 720);
        root.setBackground(new Background(bgImage));


        return clientOptions;
    }

    Scene managerScene(){
        // manager Scene
        BorderPane managerRoot = new BorderPane();  // Use BorderPane for layout
        managerRoot.setBackground(createBackground());

        GridPane p2 = new GridPane();    // Center l buttons
        p2.setVgap(100);
        p2.setHgap(100);
        p2.setPadding(new Insets(20));
        p2.setAlignment(Pos.CENTER);

        Scene decisionScene = new Scene(managerRoot, 1280, 720);

        // Buttons
        Button b_hire = new Button("Hire Pharmacist");
        Button b_fire = new Button("Fire Pharmacist");

        // Style and size
        for (Button b : new Button[]{b_hire, b_fire}) {
            b.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
            b.setStyle("-fx-background-color: #8aa2d4; -fx-text-fill: white; -fx-background-radius: 60;");
            b.setPrefSize(300, 100);
        }

        // Add to center layout
        p2.add(b_hire, 0, 0);
        p2.add(b_fire, 1, 0);

        // Top bar with back button
        Button backBtn = new Button("← Back");
        backBtn.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        backBtn.setStyle("-fx-background-color: #8aa2d4; -fx-text-fill: white; -fx-background-radius: 60;");
        backBtn.setOnAction(e -> stage.setScene(homePage()));

        BorderPane topWrapper = new BorderPane();
        topWrapper.setRight(backBtn);
        topWrapper.setPadding(new Insets(10));

        managerRoot.setTop(topWrapper);
        managerRoot.setCenter(p2);



        GridPane p3 = new GridPane();
        Scene hire = new Scene(p3, 1280, 720);
        b_hire.setOnAction(e->{
                    stage.setScene(hire);
                }

        );
        // Labels
        Label L_name = new Label("Name:");
        Label L_phoneNo = new Label("Phone Number:");
        Label L_gender = new Label("Gender:");
        Label L_id = new Label("ID:");
        Label L_shiftS = new Label("Shift Start Time:");
        Label L_shiftE = new Label("Shift End Time:");

        // TextFields
        TextField tf_name = new TextField();
        TextField tf_phoneNo = new TextField();
        TextField tf_gender = new TextField();
        TextField tf_id = new TextField();
        TextField tf_shiftS = new TextField();
        TextField tf_shiftE = new TextField();
        TextField tf_salary = new TextField();
        TextField tf_age = new TextField();
        TextField tf_email = new TextField();
        PasswordField pf_password = new PasswordField(); // Password field

        // Prompt Texts
        tf_name.setPromptText("Name");
        tf_phoneNo.setPromptText("Phone number (11 digits)");
        tf_gender.setPromptText("Gender (male/female)");
        tf_id.setPromptText("ID (numbers only)");
        tf_shiftS.setPromptText("Shift start time (e.g. 09:00)");
        tf_shiftE.setPromptText("Shift end time (e.g. 17:00)");
        tf_salary.setPromptText("Salary (numbers only)");
        tf_age.setPromptText("Age (numbers only)");
        tf_email.setPromptText("Email");
        pf_password.setPromptText("Password");

        // Button
        Button add = new Button("Add");
        add.setStyle("-fx-background-color: #aac1dd; -fx-text-fill: white; -fx-background-radius: 10;");
        add.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        add.setPrefSize(100, 50);

        Button backFromHire = new Button("← Back");
        backFromHire.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        backFromHire.setStyle("-fx-background-color: #8aa2d4; -fx-text-fill: white; -fx-background-radius: 60;");
        backFromHire.setOnAction(e -> stage.setScene(managerScene()));

        // Layout
        p3.add(backFromHire,3,0);
        p3.add(L_name, 0, 1);
        p3.add(tf_name, 1, 1);
        p3.add(L_phoneNo, 0, 2);
        p3.add(tf_phoneNo, 1, 2);
        p3.add(L_gender, 0, 3);
        p3.add(tf_gender, 1, 3);
        p3.add(L_id, 0, 4);
        p3.add(tf_id, 1, 4);
        p3.add(L_shiftS, 0, 5);
        p3.add(tf_shiftS, 1, 5);
        p3.add(L_shiftE, 0, 6);
        p3.add(tf_shiftE, 1, 6);
        p3.add(new Label("Salary:"), 0, 7);
        p3.add(tf_salary, 1, 7);
        p3.add(new Label("Age:"), 0, 8);
        p3.add(tf_age, 1, 8);
        p3.add(new Label("Email:"), 0, 9);
        p3.add(tf_email, 1, 9);
        p3.add(new Label("Password:"), 0, 10);
        p3.add(pf_password, 1, 10);
        p3.add(add, 1, 11);

        p3.setAlignment(Pos.CENTER);
        p3.setVgap(10);
        p3.setHgap(10);
        p3.setPadding(new Insets(20));

        add.setOnAction(e -> {
            boolean valid = true;

            // Reset all field styles
            TextField[] fields = {tf_name, tf_phoneNo, tf_gender, tf_id, tf_shiftS, tf_shiftE, tf_salary, tf_age, tf_email};
            for (TextField field : fields) {
                field.setStyle("");
            }
            pf_password.setStyle("");

            // Name - must not be empty
            if (tf_name.getText().trim().isEmpty()) {
                tf_name.setStyle("-fx-border-color: red; -fx-background-color: white;");
                tf_name.clear();
                valid = false;
            }

            // Phone number: exactly 11 digits
            String phone = tf_phoneNo.getText().trim();
            if (!phone.matches("\\d{11}")) {
                tf_phoneNo.setStyle("-fx-border-color: red; -fx-background-color: white;");
                tf_phoneNo.clear();
                valid = false;
            }

            // Gender: must be male or female (case insensitive)
            String gender = tf_gender.getText().trim().toLowerCase();
            if (!(gender.equals("male") || gender.equals("female"))) {
                tf_gender.setStyle("-fx-border-color: red; -fx-background-color: white;");
                tf_gender.clear();
                valid = false;
            }

            // ID: numbers only (at least 1 digit)
            String id = tf_id.getText().trim();
            if (!id.matches("\\d+")) {
                tf_id.setStyle("-fx-border-color: red; -fx-background-color: white;");
                tf_id.clear();
                valid = false;
            }

            // Shift times validation
            if (!isValidTime(tf_shiftS.getText().trim())) {
                tf_shiftS.setStyle("-fx-border-color: red; -fx-background-color: white;");
                tf_shiftS.clear();
                valid = false;
            }
            if (!isValidTime(tf_shiftE.getText().trim())) {
                tf_shiftE.setStyle("-fx-border-color: red; -fx-background-color: white;");
                tf_shiftE.clear();
                valid = false;
            }

            // Salary: must be a valid double number
            try {
                Double.parseDouble(tf_salary.getText().trim());
            } catch (NumberFormatException ex) {
                tf_salary.setStyle("-fx-border-color: red; -fx-background-color: white;");
                tf_salary.clear();
                valid = false;
            }

            // Age: must be a valid integer number
            try {
                Integer.parseInt(tf_age.getText().trim());
            } catch (NumberFormatException ex) {
                tf_age.setStyle("-fx-border-color: red; -fx-background-color: white;");
                tf_age.clear();
                valid = false;
            }

            // Email: not empty
            if (tf_email.getText().trim().isEmpty()) {
                tf_email.setStyle("-fx-border-color: red; -fx-background-color: white;");
                tf_email.clear();
                valid = false;
            }

            // Password: not empty
            if (pf_password.getText().trim().isEmpty()) {
                pf_password.setStyle("-fx-border-color: red; -fx-background-color: white;");
                pf_password.clear();
                valid = false;
            }

            if (!valid) {
                // Just visual feedback and cleared invalid fields, no popup
                return;
            }

            // Success: clear all fields and styles
            for (TextField field : fields) {
                field.clear();
                field.setStyle("");
            }
            pf_password.clear();
            pf_password.setStyle("");
        });

        GridPane p4 = new GridPane ();

        Scene fire =new Scene (p4,700,500);

        Button backFromFire = new Button("← Back");
        backFromFire.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        backFromFire.setStyle("-fx-background-color: #8aa2d4; -fx-text-fill: white; -fx-background-radius: 60;");
        backFromFire.setOnAction(e -> stage.setScene(managerScene()));

        Label L_fireById = new Label("id :");
        TextField tf_fireById = new TextField();

        p4.add(backFromFire,2,0);
        p4.add(L_fireById,2,2);
        p4.add(tf_fireById,3,2);
        b_fire.setOnAction(e->{
            stage.setScene(fire);
                }

        );

        GridPane.setHalignment(L_fireById, HPos.CENTER);
        Button b_search =new Button ("search");
        p4.add(b_search,3,6);

        p4.setVgap(10);
        p4.setHgap(10);
        p4.setPadding(new Insets(20));


        //int id ;
        b_search.setOnAction(e -> {

            try {
                String input = tf_fireById.getText().trim();
                int id = Integer.parseInt(input);
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("ID field cannot be empty.");
                }
                (Manager.getManager()).firePharmacist(id);
                Label b_fired =new Label("fired successfully");
                p4.add(b_fired, 4, 5);
            }
            catch (NumberFormatException ex) {
                tf_fireById.clear();
                Label b_fired =new Label("not found");
                p4.add(b_fired, 4, 5);
                return;
            }
            catch (IllegalStateException ex) {
                tf_fireById.clear();
                Label b_fired =new Label("not found");
                p4.add(b_fired, 4, 5);
                return;
            }
        }
        );



        return decisionScene;
    }


    Scene pharmacistScene(){
        // Create styled buttons
        Button addProduct = createButton("Add Product");
        Button removeProduct = createButton("Remove Product");
        Button backBtn = createButton("← Back");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backBtn.setPrefWidth(100);

        // Welcome text
        Text welcome = new Text("Welcome "+ ((Pharmacist)currentPerson).getName());
        welcome.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        welcome.setFill(Color.DARKBLUE);

        // Top section with welcome and back button
        BorderPane topWrapper = new BorderPane();
        StackPane leftTop = new StackPane(welcome);
        leftTop.setAlignment(Pos.CENTER_LEFT);
        topWrapper.setLeft(leftTop);
        topWrapper.setRight(backBtn);
        topWrapper.setPadding(new Insets(20, 20, 10, 20));

        // Center layout
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(30);
        pane.setVgap(30);
        pane.setPadding(new Insets(20));

        pane.add(addProduct, 1, 1);
        pane.add(removeProduct, 2, 1);

        // Main layout
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topWrapper);
        borderPane.setCenter(pane);
        borderPane.setStyle("-fx-background-color: #f0f4f8;");

        // Scene and stage setup
        borderPane.setBackground(commonBackGround);
        Scene pharmacistScene = new Scene(borderPane, 1280, 720);
        //stage.setScene(pharmacistScene);
//        stage.setTitle("Pharmacist Dashboard");
//        stage.show();




        //Add product Scene

        /// ////////////////////////////////////////////
        Button addMedicine = createButton("ADD");
        Button backFromAdd = createButton("← Back");

        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backBtn.setPrefWidth(100);

        TextField productName = new TextField();
        productName.setPromptText("Product Name");

        TextField quantity = new TextField();
        quantity.setPromptText(" quantity");

        TextField ID = new TextField();
        ID.setPromptText("ID");

        TextField price = new TextField();
        price.setPromptText("Price");

        TextField amount = new TextField();
        amount.setPromptText("Amount");

        TextField Symptom = new TextField();
        Symptom.setPromptText("Symptom");

        TextField expiryDate = new TextField();
        expiryDate.setPromptText("use format: yyyy-mm-dd");

        ObservableList<String> items = FXCollections.observableArrayList("piece", "liquid");
        ListView Type = new ListView(items);

        TextField prispiction = new TextField("Does it need prispiction");
        prispiction.setEditable(false);

        ToggleGroup prispictionGroup = new ToggleGroup();
        RadioButton yes = new RadioButton("yes");
        RadioButton no = new RadioButton("no");
        yes.setToggleGroup(prispictionGroup);
        no.setToggleGroup(prispictionGroup);

        GridPane inputPane = new GridPane();
        inputPane.setVgap(15);
        inputPane.setHgap(20);
        inputPane.setPadding(new Insets(30));
        inputPane.setAlignment(Pos.CENTER);

        inputPane.add(backFromAdd,3,0);

        inputPane.add(new Label("Name:"), 0, 1);
        inputPane.add(productName, 1, 1);

        inputPane.add(new Label("ID:"), 0, 2);
        inputPane.add(ID, 1, 2);

        inputPane.add(new Label("Quantity:"), 0, 3);
        inputPane.add(quantity, 1, 3);

        inputPane.add(new Label("Price:"), 0, 4);
        inputPane.add(price, 1, 4);

        inputPane.add(new Label("Amount:"), 0, 5);
        inputPane.add(amount, 1, 5);

        inputPane.add(new Label("Symptom:"), 0, 6);
        inputPane.add(Symptom, 1, 6);

        inputPane.add(new Label("Expiry Date:"), 0, 7);
        inputPane.add(expiryDate, 1, 7);

        inputPane.add(new Label("Type:"), 0, 8);
        inputPane.add(Type, 1, 8);

        inputPane.add(new Label("Need Prescription:"), 0, 9);
        inputPane.add(prispiction, 1, 9);

        VBox bools = new VBox(10,yes,no);
        inputPane.add(bools,1,10);

        inputPane.add(addMedicine, 1, 11);

        backBtn.setOnAction(e->{
            stage.setScene(homePage());
        });
// BorderPane Layout
        BorderPane root = new BorderPane();
        root.setCenter(inputPane);
        root.setBackground(commonBackGround);
// Scene setup
        Scene addProducts = new Scene (root, 1280, 720);


        addMedicine.setOnAction( e-> {

            String name = productName.getText();
            if(name==""){
                textFieldError(productName);
                return;
            }
            int id;

            try {
                id = Integer.parseInt(ID.getText());

            }
            catch (NumberFormatException ex){
                textFieldError(ID);
                return;
            }
            String symptom = Symptom.getText();
            if(symptom==""){
                textFieldError(Symptom);
                return;
            }
            int quantity1;
            try {
                quantity1 = Integer.parseInt(quantity.getText());

            }
            catch (NumberFormatException ex){
                textFieldError(quantity);
                return;
            }
            int Price;
            try {
                Price = Integer.parseInt(price.getText());

            }
            catch (NumberFormatException ex){
                textFieldError(price);
                return;
            }
            int Amount;
            try {
                Amount = Integer.parseInt(amount.getText());

            }
            catch (NumberFormatException ex){
                textFieldError(amount);
                return;
            }


            LocalDate expiry;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                expiry = LocalDate.parse(expiryDate.getText(), formatter);


            } catch (Exception ex) {
                textFieldError(expiryDate);
                return;
            }

            String type = (String)(Type.getSelectionModel().getSelectedItem());

            boolean isNeedPrispiction;

            if (yes.isSelected())
                isNeedPrispiction = true;
            else if (no.isSelected())
                isNeedPrispiction = false;
            else {
                textFieldError(prispiction);
                return;
            }

            Medicine med = new Medicine(name,quantity1,Price,type,isNeedPrispiction,Amount,symptom,id,expiry);
            ((Pharmacist)currentPerson).addProduct(med);
            productName.clear();
            quantity.clear();
            ID.clear();
            price.clear();
            amount.clear();
            Symptom.clear();
            expiryDate.clear();
        });
        addProduct.setOnAction( e-> {
                    stage.setScene(addProducts);
                }
        );
        backFromAdd.setOnAction(e->{
            stage.setScene(pharmacistScene);
        });



        //  remove product scene


        Button remove = createButton("REMOVE");
        TextArea status = new TextArea();
        TextField nameOfProduct = new TextField("Product's name");
        Button backFromRemove = createButton("← Back");
        backBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backBtn.setPrefWidth(100);

        BorderPane removePane = new BorderPane();
        removePane.setRight(backFromRemove);

        VBox removeLayout = new VBox(15);
        removeLayout.setAlignment(Pos.CENTER);
        removeLayout.setPadding(new Insets(20));

        nameOfProduct.setPrefWidth(250);
        nameOfProduct.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        nameOfProduct.setStyle("-fx-border-color: lightgray; -fx-background-color: white;");


        status.setPrefWidth(300);
        status.setPrefHeight(100);
        status.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        status.setEditable(false);

        removeLayout.getChildren().addAll(nameOfProduct, remove, status);

        removePane.setCenter(removeLayout);
        removePane.setBackground(commonBackGround);
        Scene removeProducts = new Scene(removePane, 700, 500);

        remove.setOnAction(ev->{
                    String prod = nameOfProduct.getText();
                    try {
                        ((Pharmacist)currentPerson).removeProduct(prod);
                        status.setText("The product is removed successfully");
                        nameOfProduct.clear();
                    }
                    catch (IllegalArgumentException ex){
                        status.setText(ex.getMessage());
                        nameOfProduct.clear();
                    }
                }

        );


        removeProduct.setOnAction( e-> {
                    stage.setScene(removeProducts);
                }
        );
        backFromRemove.setOnAction(e->{
            stage.setScene(pharmacistScene);
        });
        return pharmacistScene;
    }
    Scene homePage(){
        GridPane pane = new GridPane();              // Divde the pane to 2D grid consists of columns and rows
        BorderPane borderPane = new BorderPane();    // Divide pane into 5 sections (TOP,RIGHT,LEFT,CENETR,BUTTOM)
        StackPane top = new StackPane();             // put nodes on top of each other as layers
        pane.setAlignment(Pos.CENTER);               // to put buttons at the center
        // create top text
        Text titleText = new Text("ASU pharmacy");
        titleText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 75));
        titleText.setFill(Color.web("#222366"));
        top.getChildren().add(titleText);
        borderPane.setTop(top);             // text at the top
        top.setPadding(new Insets(60, 0, 10, 0));
        // create buttons
        Button managerBtn = new Button("Manager");
        Button PharmacistBtn = new Button("Pharmacist");
        Button ClientBtn = new Button("Client");

        // adjust font of the buttons keywords and buttons size
        for (Button b : new Button[]{managerBtn, PharmacistBtn, ClientBtn}) {
            b.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
            b.setStyle("-fx-background-color: #8aa2d4; -fx-text-fill: white;-fx-background-radius: 60;");
            b.setPrefSize(200, 100);
        }

        pane.add(managerBtn,0,0);
        pane.add(PharmacistBtn,1,0);
        pane.add(ClientBtn,2,0);
        pane.setHgap(200);
        borderPane.setCenter(pane); // buttons at the center
        // create Background
        borderPane.setBackground(createBackground());
        // create Scenes
        Scene mainScene = new Scene(borderPane,1280,720);

        // Buttons handling
        managerBtn.setOnAction(e -> stage.setScene(loginScene(0)));
        PharmacistBtn.setOnAction(e -> stage.setScene(loginScene(1)));
        ClientBtn.setOnAction(e -> stage.setScene(clientScene()));

        return mainScene;
    }
    Scene clientOptions;
    Scene showScene;
    Background commonBackGround = createBackground();
    Person currentPerson;

    @Override
    public void start(Stage stage2) {
        Scene sc = homePage();
        stage.setScene(sc);
        stage.show();
    }





    // Helper method for button styling
    private Button createButton(String text) {
        Button btn = new Button(text);
        btn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        btn.setPrefWidth(180);
        btn.setPrefHeight(45);
        return btn;
    }

    void textFieldError(TextField tf){
        tf.setStyle("-fx-border-color: red; -fx-background-color: white;");
        tf.clear();
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e-> tf.setStyle(""));
        delay.play();
    }
    private boolean isValidTime(String time) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime.parse(time, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    public static void main(String[] args) {
        PharmacyManagementSystem p = new PharmacyManagementSystem();
        p.addPharmacist(new Pharmacist("lotfy", "01228392057", "male", 12, 21, "lotfy@gmail.com", "admin",4,"06:00", "09:00"));
        p.addPharmacist(new Pharmacist("hamada", "01228392057", "male", 12, 21, "hamada@gmail.com", "admin",5,"06:00", "09:00"));
        Manager m = new Manager("Abanoub" ,"01228392057", "male", 100000, 20, "abanoub@gmail.com", "admin");
        launch(args);
    }
}
