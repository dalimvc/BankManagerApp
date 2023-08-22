package dalmir2;

//student:Dalibor Mirkovic
//ltu-id: dalmir2

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;




public class Gui extends Application {

    //declaring scenes, labels, manubars and text fields that will be used for all pages
    BankLogic bankLogic;
    Scene sceneHome, scene1;
    Stage primaryStage;

    MenuBar menuBar = new MenuBar();
    GridPane grid = new GridPane();

    Label pageHeading = new Label();
    Label pageDescription = new Label();
    Label resultInformation = new Label();
    Label resultInformationFileHandeling = new Label();
    Label extraInfo = new Label();
    Label nameLabel = new Label("Name");
    Label surnameLabel = new Label("Surname");
    Label personalNrLabel = new Label("Personal number");
    Label resultLabelPnr = new Label();
    Label resultLabelName = new Label();
    Label resultLabelSurname = new Label();
    Label resultLabelAccNr = new Label();
    Label resultLabelBalance = new Label();
    Label resultLabelAccType = new Label();
    Label resultLabelAccInterestRate = new Label();
    Label resultLabelPnr1 = new Label();
    Label resultLabelName1 = new Label();
    Label resultLabelSurname1 = new Label();
    Label customerH = new Label();
    Label accoumtsH = new Label();

    TextField nameInput = new TextField();
    TextField surnameInput = new TextField();
    TextField pnrInput = new TextField();

    public void clearAllCssClassLabels(){
        pageHeading.getStyleClass().removeAll("heading");
        pageDescription.getStyleClass().removeAll("pageDescClass");
        nameLabel.getStyleClass().removeAll("labelsPage");
        surnameLabel.getStyleClass().removeAll("labelsPage");
        personalNrLabel.getStyleClass().removeAll("labelsPage");
        resultInformation.getStyleClass().removeAll("ResultLabelsPage");
        pnrInput.getStyleClass().removeAll("input");
        customerH.getStyleClass().removeAll("ResultLabelsPage");
        accoumtsH.getStyleClass().removeAll("ResultLabelsPage");
        resultLabelPnr1.getStyleClass().removeAll("labelsPage");
        resultLabelName1.getStyleClass().removeAll("labelsPage");
        resultLabelSurname1.getStyleClass().removeAll("labelsPage");
        resultLabelAccNr.getStyleClass().removeAll("ResultLabelsPage");
        resultLabelBalance.getStyleClass().removeAll("ResultLabelsPage");
        resultLabelAccType.getStyleClass().removeAll("ResultLabelsPage");
        resultLabelAccInterestRate.getStyleClass().removeAll("ResultLabelsPage");
    }



    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        //creting an instance of BankLogic
        bankLogic = new BankLogic();

        //creating menus for the MenuBar
        Menu accountMenu = new Menu("Account");
        Menu customerMenu = new Menu("Customer");
        Menu readImportMenu = new Menu("File Handler");

        //making menu items (they will be shown in the form of drop-down)
        MenuItem createSavingsAccount = new MenuItem("Create savings account");
        MenuItem createCreditAccount = new MenuItem("Create credit account");
        MenuItem getAccountInformation = new MenuItem("Get account information");
        MenuItem depositMoney = new MenuItem("Deposit money to account");
        MenuItem withdrawMoney = new MenuItem("Withdraw money from account");
        MenuItem closeAccount = new MenuItem("Close account");
        MenuItem seeAllTransactions = new MenuItem("See all transactions");

        MenuItem addCust = new MenuItem("Add customer");
        MenuItem changeName = new MenuItem("Change customer's name");
        MenuItem seeCustInfo = new MenuItem("See customer's information");
        MenuItem deleteCustomer = new MenuItem("Delete customer");
        MenuItem seeAllCustomers = new MenuItem("See all customers");

        MenuItem importCustomers = new MenuItem("Import import");
        MenuItem exportCustomers = new MenuItem("Export data");
        MenuItem exportTransactions = new MenuItem("Save transactions");

        //adding menu items to the menus
        accountMenu.getItems().addAll(createSavingsAccount, createCreditAccount, getAccountInformation,
                depositMoney, withdrawMoney, closeAccount, seeAllTransactions);
        customerMenu.getItems().addAll(addCust, changeName, seeCustInfo, deleteCustomer, seeAllCustomers);
        readImportMenu.getItems().addAll(importCustomers, exportCustomers, exportTransactions);
        //adding menus to the menu bar
        menuBar.getMenus().addAll(customerMenu,accountMenu, readImportMenu);

        //setting event handlers for menu items
        addCust.setOnAction(event -> sceneAddCustomer());
        seeCustInfo.setOnAction(event -> sceneseeCustInfo());
        changeName.setOnAction(event -> sceneChangeName());
        deleteCustomer.setOnAction(event -> sceneDeleteCustomer());
        seeAllCustomers.setOnAction(event -> sceneSeeAllCustomers());

        createSavingsAccount.setOnAction(event -> sceneCreateSavingsAccount());
        createCreditAccount.setOnAction(event -> sceneCreateCreditAccount());
        getAccountInformation.setOnAction(event -> sceneSeeAccountInfo());
        depositMoney.setOnAction(event -> sceneDepositMoney());
        withdrawMoney.setOnAction(event -> sceneWithdrawMoney());
        closeAccount.setOnAction(event -> sceneCloseAccount());
        seeAllTransactions.setOnAction(event -> sceneSeeAllTransactions());

        exportCustomers.setOnAction(event -> sceneExportData());
        importCustomers.setOnAction(event -> sceneImportData());
        exportTransactions.setOnAction(event -> sceneExportTransactions());

        //creating the heading and description labels
        Label heading = new Label("Welcome to Bank Manager App");
        heading.setId("headingMainPage");
        Label description = new Label("Manage operations within customers and accounts.");
        description.setId("descMainPage");

        //making a VBox for the heading, description, and menuBar
        VBox vbox = new VBox(10); //spacing between the child nodes in VBox is 10
        vbox.getChildren().addAll(menuBar, heading, description); //adding children in vbox

        //creating layout BorderPane for the scene and set the VBox as the center
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.getStyleClass().add("root");

        //creates scene with the layout BorderPane
        sceneHome = new Scene(root, 800, 600); //size in px
        sceneHome.getStylesheets().add("bankManager.css");

        //set the scene on the stage
        primaryStage.setScene(sceneHome);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: ADD CUSTOMER/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public void sceneAddCustomer() {
        //clearing labels
        nameInput.setText("");
        surnameInput.setText("");
        pnrInput.setText("");
        resultInformation.setText("");

        //creates  VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        //creating GridPane
        grid.setPadding(new Insets(10, 10, 10, 10)); //padding
        grid.setVgap(8); //vertical gap
        grid.setHgap(10); //horisontal gap
        grid.getChildren().clear();

        //labels
        pageHeading.setText("Add new customer");
        pageHeading.getStyleClass().add("mainPageH1");
        pageDescription.setText("Add new bank customer by providing customer's information.");

        //specifying positioning the GridPane
        //second parameter is column index, third is row index
        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(nameInput, 1, 0);

        //specifying positioning the GridPane
        GridPane.setConstraints(surnameLabel, 0, 1);
        GridPane.setConstraints(surnameInput, 1, 1);

        //specifying positioning the GridPane
        GridPane.setConstraints(personalNrLabel, 0, 2);
        GridPane.setConstraints(pnrInput, 1, 2);

        //button to submit
        Button addCustomer = new Button("Add Customer");
        GridPane.setConstraints(addCustomer, 3, 2);

        //adding items into the grid
        grid.getChildren().addAll(nameLabel, nameInput, surnameLabel, surnameInput, personalNrLabel, pnrInput, addCustomer);

        //action handeler when user submits the form
        //when user clicks on the button, event will be started.
        //program will check if user input is correct and call bankLogic.createCustomer(name, surname, String.valueOf(pnr) method
        addCustomer.setOnAction(event -> {
            //getting users input to show it if success
            String name = nameInput.getText();
            String surname = surnameInput.getText();
            try {
                Integer pnr = Integer.valueOf(pnrInput.getText());
                boolean accountCustomer = bankLogic.createCustomer(name, surname, String.valueOf(pnr));
                //if successful
                if (accountCustomer == true) {
                    resultInformation.setText("New customer " + name + " " + surname + " with personal number " + pnr + " successfully created!");
                    //clearing labels
                    nameInput.setText("");
                    surnameInput.setText("");
                    pnrInput.setText("");
                } else if (accountCustomer != true) {
                    resultInformation.setText("Customer already exists. Please make sure to enter correct input!");
                    //clearing labels
                    nameInput.setText("");
                    surnameInput.setText("");
                    pnrInput.setText("");
                }
            } catch (NumberFormatException e) {
                // The input is not a valid integer
                resultInformation.setText("It seams that entered data was not correct. Please make sure to enter correct input!");
                //clearing labels
                nameInput.setText("");
                surnameInput.setText("");
                pnrInput.setText("");
            }
        });

        //creates a layout pane for the scene
        BorderPane root = new BorderPane();

        //add in the VBox adding heading, text description and grid(form)
        VBox centerContent = new VBox(pageHeading, pageDescription, grid, resultInformation);

        //vbox will be positioned on the top(menu)
        root.setTop(vbox);
        //heading, text description and grid(form) will be position in the center
        root.setCenter(centerContent);

        // Create the scene with the layout
        scene1 = new Scene(root, 800, 600);

        //adding css classes for elements
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        nameLabel.getStyleClass().add("labelsPage");
        surnameLabel.getStyleClass().add("labelsPage");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultInformation.getStyleClass().add("ResultLabelsPage");
        addCustomer.getStyleClass().add("button");
        nameInput.getStyleClass().add("input");
        surnameInput.getStyleClass().add("input");
        pnrInput.getStyleClass().add("input");

        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: SEE CUSTOMERS INFORMATION////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public void sceneseeCustInfo() {
        //clearing labels and grid
        resultInformation.setText("");
        resultLabelPnr.setText("");
        resultLabelName.setText("");
        resultLabelSurname.setText("");
        resultLabelAccNr.setText("");
        grid.getChildren().clear();

        //creates  VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        //creating GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10)); //padding
        grid.setVgap(8); //vertical gap
        grid.setHgap(10); //horisontal gap

        //Input field
        TextField pnrInput = new TextField();

        //specifying positioning the GridPane
        //second parameter is column index, third is row index
        GridPane.setConstraints(personalNrLabel, 0, 0);
        GridPane.setConstraints(pnrInput, 1, 0);

        //button to submit
        Button seeCustomer = new Button("See customer's info");
        GridPane.setConstraints(seeCustomer, 2, 0);

        //action handeler when user submits the form
        //program will try to call a method in banklogic using users input and if successful save that data into arraylist
        seeCustomer.setOnAction(event -> {
            //clearing labels
            resultInformation.setText("");
            resultLabelPnr.setText("");
            resultLabelName.setText("");
            resultLabelSurname.setText("");

            try {
                grid.getChildren().clear();
                //getting users input as integer type
                Integer pnr = Integer.valueOf(pnrInput.getText());
                //calling getCustomer() in BankLogic
                ArrayList<String> customer = bankLogic.getCustomer(String.valueOf(pnr));
                if(customer != null){
                    //information about customer that is stored in ArrayList<String> customer gets saved as array and elements are split by " "
                    String[] parts = customer.get(0).split(" ");
                    String partPnr = parts[0];
                    String partName = parts[1];
                    String partSurname = parts[2];
                    resultLabelPnr.setText("Personal number: " + partPnr);
                    resultLabelName.setText("Name: " + partName);
                    resultLabelSurname.setText("Surname: " + partSurname);

                    //using stringBuilder detting account details
                    StringBuilder accountsInfo = new StringBuilder();
                    for (int i = 1; i < customer.size(); i++) {
                        String[] accountParts = customer.get(i).split(" ");
                        String accountNumber = accountParts[0];
                        String balance = accountParts[1];
                        String accountType = accountParts[2];
                        String interestRate = accountParts[3];

                        String accountInfo = "Account number: " + accountNumber +
                                ", Balance: " + balance +
                                ", Account Type: " + accountType +
                                ", Interest Rate: " + interestRate;
                        accountsInfo.append(accountInfo).append("\n");
                    }
                    resultLabelAccNr.setText(accountsInfo.toString());
                    pageDescription.setText("Details about selected customer:");
                } else{
                    resultInformation.setText("No customer found. Please make sure to enter correct input!");
                    GridPane.setConstraints(resultInformation, 0, 0);
                    grid.getChildren().addAll(resultInformation);
                }
            } catch (NumberFormatException e) {
                resultInformation.setText("It seams that entered data was not correct. Please make sure to enter correct input!");
                GridPane.setConstraints(resultInformation, 0, 0);
                grid.getChildren().addAll(resultInformation);
            }
        });
        //adding elemets to the grid
        grid.getChildren().addAll(personalNrLabel, pnrInput, seeCustomer);

        //labels
        pageHeading.setText("See customer's information");
        pageDescription.setText("See customer's information by providing customer's personal number.");

        // Create a layout pane for the scene
        BorderPane root = new BorderPane();

        //in the VBox adding heading, text description and grid(form)
        VBox centerContent = new VBox(pageHeading, pageDescription, grid, resultLabelPnr, resultLabelName, resultLabelSurname, resultLabelAccNr );

        //vbox will be positioned on the top(menu)
        root.setTop(vbox);
        //heading, text description and grid(form) will be position in the center
        root.setCenter(centerContent);

        // Create the scene with the layout
        scene1 = new Scene(root, 800, 600);

        //adding css classes
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        nameLabel.getStyleClass().add("labelsPage");
        surnameLabel.getStyleClass().add("labelsPage");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultInformation.getStyleClass().add("ResultLabelsPage");
        resultLabelPnr.getStyleClass().add("ResultLabelsPage");
        resultLabelName.getStyleClass().add("ResultLabelsPage");
        resultLabelSurname.getStyleClass().add("ResultLabelsPage");
        nameInput.getStyleClass().add("input");
        surnameInput.getStyleClass().add("input");
        pnrInput.getStyleClass().add("input");
        seeCustomer.getStyleClass().add("button");
        resultLabelAccNr.getStyleClass().add("ResultLabelsPage");
        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: CHANGE CUSTOMER NAME////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public void sceneChangeName() {
        //clearing labels
        resultInformation.setText("");
        //those labels will be storing customers info values
        Label newNameLabel = new Label("New name");
        Label newSurnameLabel = new Label("New surname");

        //buttons
        Button getCustomer = new Button("Get customer");
        Button changeNameCustomer = new Button("Change name");

        //input fields
        TextField nameInput = new TextField();
        TextField surnameInput = new TextField();

        //labels
        pageHeading.setText("Change customer's name");
        pageDescription.setText("Change customers name by providing customer's personal number.");

        //creating GridPane
        grid.setPadding(new Insets(10, 10, 10, 10)); //padding
        grid.setVgap(8); //vertical gap
        grid.setHgap(10); //horisontal gap
        grid.getChildren().clear(); //it needs to be clear in order to update information that users sees after user clicks on buttons

        //creates  VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        //Input field
        pnrInput = new TextField();

        //clearing labels
        extraInfo.setText("");

        //adding elemets to the grid
        GridPane.setConstraints(personalNrLabel, 0, 0);
        GridPane.setConstraints(pnrInput, 1, 0);
        GridPane.setConstraints(getCustomer, 2, 0);
        grid.getChildren().addAll(personalNrLabel, pnrInput, getCustomer);

        //action handeler when user submits the form
        //program will check if user has entered correct data and provide input for to change name
        getCustomer.setOnAction(event -> {
            //handeling grid and its elements
            grid.getChildren().clear();
            GridPane.setConstraints(resultLabelPnr, 0, 0);
            GridPane.setColumnSpan(resultLabelPnr, 3);
            GridPane.setConstraints(newNameLabel, 0, 1);
            GridPane.setConstraints(nameInput, 1, 1);
            GridPane.setConstraints(newSurnameLabel, 0, 2);
            GridPane.setConstraints(surnameInput, 1, 2);
            GridPane.setConstraints(changeNameCustomer, 2, 2);
            grid.getChildren().addAll(resultLabelPnr, newNameLabel, nameInput, newSurnameLabel, surnameInput, changeNameCustomer);

            try {
                //getting users input
                Integer pnr = Integer.valueOf(pnrInput.getText());
                //calling createCustomer in BankLogic
                ArrayList<String> accountCustomer = bankLogic.getCustomer(String.valueOf(pnr));

                //if there's a match, customer exists
                if (accountCustomer != null && !accountCustomer.isEmpty()){
                    //clearing labels
                    resultInformation.setText("");
                    //splitting arraylist to get current values
                    String[] parts = accountCustomer.get(0).split(" ");
                    //assigning currentValues
                    String partPnr = parts[0];
                    String partName = parts[1];
                    String partSurname = parts[2];
                    //setting them into already declared Labels
                    resultLabelPnr.setText("Personal number: " + partPnr + ", current name: " + partName + " " + partSurname);
                    //button to submit
                    changeNameCustomer.setText("Change name");

                    //action handeler when user submits the second form
                    changeNameCustomer.setOnAction(event1 -> {
                        ///clearing labels
                        pageDescription.setText("");
                        String name = nameInput.getText();
                        String surname = surnameInput.getText();
                        Integer pnr1 = Integer.valueOf(pnrInput.getText());
                        //calling createCustomer in BankLogic
                        boolean changedName = bankLogic.changeCustomerName(name, surname, String.valueOf(pnr1));
                        //if name is changed
                        if (changedName == true) {
                            grid.getChildren().clear();//clearing grid
                            //adding info labels
                            resultInformation.setText("Success! Name changed for customer with personal number " + pnr1 + ".");
                            extraInfo.setText("New name: " + name + " " + surname);

                            GridPane.setConstraints(resultInformation, 0, 0);
                            GridPane.setConstraints(extraInfo, 0, 1);

                            grid.getChildren().addAll(resultInformation, extraInfo);
                        }
                    });
                }
                else if (accountCustomer == null){
                    grid.getChildren().clear();//clearing grid
                    resultInformation.setText("Customer doesn't exists. Make sure you enter correct data.");
                    grid.getChildren().clear();
                    GridPane.setConstraints(resultInformation, 0, 0);
                    grid.getChildren().addAll(resultInformation);
                }
            } catch (NumberFormatException e) {
                //clearing grid
                resultInformation.setText("There was a problem with your input. Make sure you enter correct data.");
                grid.getChildren().clear();
                GridPane.setConstraints(resultInformation, 0, 0);
                grid.getChildren().addAll(resultInformation);
            }
        });

        // Create a layout pane for the scene
        BorderPane root = new BorderPane();
        //in the VBox adding heading, text description and grid(form)
        VBox centerContent = new VBox(pageHeading, pageDescription, grid);
        //vbox will be positioned on the top(menu)
        root.setTop(vbox);
        //heading, text description and grid(form) will be position in the center
        root.setCenter(centerContent);

        // Create the scene with the layout
        scene1 = new Scene(root, 800, 600);

        //adding css classes
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultLabelPnr.getStyleClass().add("ResultLabelsPage");
        resultInformation.getStyleClass().add("ResultLabelsPage");
        newNameLabel.getStyleClass().add("labelsPage");
        newSurnameLabel.getStyleClass().add("labelsPage");
        pnrInput.getStyleClass().add("input");
        nameInput.getStyleClass().add("input");
        surnameInput.getStyleClass().add("input");
        getCustomer.getStyleClass().add("button");
        changeNameCustomer.getStyleClass().add("button");
        extraInfo.getStyleClass().add("ResultLabelsPage");

        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: DELETE CUSTOMER/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public void sceneDeleteCustomer() {
        //clearing labels
        resultInformation.setText("");

        //field for pnr input
        TextField pnrInput;

        //creating GridPane
        grid.setPadding(new Insets(10, 10, 10, 10)); //padding
        grid.setVgap(8); //vertical gap
        grid.setHgap(10); //horisontal gap
        grid.getChildren().clear(); //it needs to be clear in order to update information that users sees after user clicks on buttons

        //creates  VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        //Input field
        pnrInput = new TextField();

        //specifying positioning the GridPane
        GridPane.setConstraints(personalNrLabel, 0, 0);
        GridPane.setConstraints(pnrInput, 1, 0);

        //buttons to submit
        Button getCustomer = new Button("Get customer");
        GridPane.setConstraints(getCustomer, 2, 0);
        Button deleteCustomer = new Button("Delete customer?");

        //adding elemets to the grid
        grid.getChildren().addAll(personalNrLabel, pnrInput, getCustomer);

        //action handeler when user submits the form
        getCustomer.setOnAction(event -> {
            resultInformation.setText("");
            try {
                //getting users input
                Integer pnr = Integer.valueOf(pnrInput.getText());
                //calling createCustomer in BankLogic
                ArrayList<String> accountCustomer = bankLogic.getCustomer(String.valueOf(pnr));
                //if there's a match, customer exists
                if (accountCustomer != null && !accountCustomer.isEmpty()) {
                    //splitting arraylist to get current values
                    String[] parts = accountCustomer.get(0).split(" ");
                    //assigning currentValues
                    String partPnr = parts[0];
                    String partName = parts[1];
                    String partSurname = parts[2];
                    //setting them into already declared Labels
                    resultLabelPnr.setText("Personal number: " + partPnr);
                    resultLabelName.setText("Name: " + partName);
                    resultLabelSurname.setText("Surname: " + partSurname);
                    //specifying positioning the GridPane
                    GridPane.setConstraints(resultLabelPnr, 0, 0);
                    GridPane.setConstraints(resultLabelName, 0, 1);
                    GridPane.setConstraints(resultLabelSurname, 0, 2);
                    deleteCustomer.setText("Delete customer?");
                    GridPane.setConstraints(deleteCustomer, 0, 3);
                    grid.getChildren().clear(); //clearing grid before adding new values
                    //adding elemets to the grid
                    grid.getChildren().addAll(deleteCustomer, resultLabelPnr, resultLabelName, resultLabelSurname);
                    //action handeler when user submits the second form
                    deleteCustomer.setOnAction(event1 -> {
                        // Getting user's input
                        Integer pnr1 = Integer.valueOf(pnrInput.getText());
                        // Calling deleteCustomer in BankLogic
                        ArrayList<String> deleteCustomer1 = bankLogic.deleteCustomer(String.valueOf(pnr1));
                        // If the customer is deleted
                        if (!deleteCustomer1.isEmpty()) {
                            grid.getChildren().clear(); // Clearing grid
                            String[] removedCustomer = deleteCustomer1.get(0).split(" ");
                            // Assigning currentValues
                            String personalNumber = removedCustomer[0];
                            String removedCustomerName = removedCustomer[1];
                            String removedCustomerSurname = removedCustomer[2];
                            // Display information about the deleted customer
                            resultInformation.setText("Success! Customer with personal number " + personalNumber + ", " + removedCustomerName + " " + removedCustomerSurname + " removed.");
                            GridPane.setConstraints(resultInformation, 0, 0);
                            grid.getChildren().addAll(resultInformation);
                            // Display information about each deleted bank account (if available)
                            for (int i = 1; i < deleteCustomer1.size(); i++) {
                                String[] removedCustomersAccount = deleteCustomer1.get(i).split(" ");
                                // Assigning currentValues
                                String delAccNr = removedCustomersAccount[0];
                                String delAccBalance = removedCustomersAccount[1];
                                String delAccType = removedCustomersAccount[2];
                                String delAccInterest = removedCustomersAccount[3];
                                // Create a label to display the account information
                                pageDescription.setText("");
                                Label accountInfoLabelForDelAcc = new Label("Account number: " + delAccNr + ", balance: " + delAccBalance + ", account type: " + delAccType + ", interest rate: " + delAccInterest + ".");
                                GridPane.setConstraints(accountInfoLabelForDelAcc, 0, i); // Position the label in the grid
                                grid.getChildren().add(accountInfoLabelForDelAcc); // Add the label to the grid (do not use addAll here)
                                accountInfoLabelForDelAcc.getStyleClass().add("delAccLabel");
                            }
                        }
                    });
                }
                else {
                    resultInformation.setText("Customer not found. Please check the entered personal number.");
                    pnrInput.setText("");
                }
            } catch (NumberFormatException e) {
                // The input is not a valid integer
                resultInformation.setText("It seams that entered data was not correct. Please make sure to enter correct input!");
                pnrInput.setText("");
            }
        });
        //labels
        pageHeading.setText("Delete customer");
        pageDescription.setText("By providing customer's personal number you can delete an existing customer");
        // Create a layout pane for the scene
        BorderPane root = new BorderPane();
        //in the VBox adding heading, text description and grid(form)
        VBox centerContent = new VBox(pageHeading, pageDescription, grid, resultInformation);
        //vbox will be positioned on the top(menu)
        root.setTop(vbox);
        //heading, text description and grid(form) will be position in the center
        root.setCenter(centerContent);

        // Create the scene with the layout
        scene1 = new Scene(root, 800, 600);

        //adding css classes
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        nameLabel.getStyleClass().add("labelsPage");
        surnameLabel.getStyleClass().add("labelsPage");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultInformation.getStyleClass().add("ResultLabelsPage");
        resultLabelPnr.getStyleClass().add("labelsPageDetails");
        resultLabelName.getStyleClass().add("labelsPageDetails");
        resultLabelSurname.getStyleClass().add("labelsPageDetails");
        nameInput.getStyleClass().add("input");
        surnameInput.getStyleClass().add("input");
        pnrInput.getStyleClass().add("input");
        getCustomer.getStyleClass().add("button");
        deleteCustomer.getStyleClass().add("button");

        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: CREATE SAVING ACCOUNT/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public void sceneCreateSavingsAccount() {
        resultInformation.setText("");

        //creates  VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        //creating GridPane
        grid.setPadding(new Insets(10, 10, 10, 10)); //padding
        grid.setVgap(8); //vertical gap
        grid.setHgap(10); //horisontal gap
        grid.getChildren().clear();

        //Input field
        TextField pnrInput = new TextField();

        //specifying positioning the GridPane
        GridPane.setConstraints(personalNrLabel, 0, 0);
        GridPane.setConstraints(pnrInput, 1, 0);

        //button to submit
        Button getCustomer = new Button("Get customer");
        GridPane.setConstraints(getCustomer, 2, 0);
        Button createAccount = new Button("Create new savings account");

        //action handeler when user submits the form
        getCustomer.setOnAction(event -> {
            try {
                Integer pnr = Integer.valueOf(pnrInput.getText());
                //calling createCustomer in BankLogic
                ArrayList<String> accountCustomer = bankLogic.getCustomer(String.valueOf(pnr));
                //if there's a match, customer exists
                if (accountCustomer != null && !accountCustomer.isEmpty()) {
                    resultInformation.setText("");
                    //splitting arraylist to get current values
                    String[] parts = accountCustomer.get(0).split(" ");
                    //assigning currentValues
                    String partPnr = parts[0];
                    String partName = parts[1];
                    String partSurname = parts[2];
                    //setting them into already declared Labels
                    resultLabelPnr.setText("Personal number: " + partPnr);
                    resultLabelName.setText("Name: " + partName);
                    resultLabelSurname.setText("Surname: " + partSurname);
                    //specifying positioning the GridPane
                    GridPane.setConstraints(resultLabelPnr, 0, 0);
                    GridPane.setConstraints(resultLabelName, 0, 1);
                    GridPane.setConstraints(resultLabelSurname, 0, 2);
                    //button to submit
                    GridPane.setConstraints(createAccount, 0, 3);
                    grid.getChildren().clear(); //clearing grid before adding new values
                    //adding elemets to the grid
                    grid.getChildren().addAll(createAccount, resultLabelPnr, resultLabelName, resultLabelSurname);
                    //action handeler when user submits the second form
                    createAccount.setOnAction(event1 -> {
                        //getting users input
                        Integer pnr1 = Integer.valueOf(pnrInput.getText());
                        //calling createCustomer in BankLogic
                        int changedName = bankLogic.createSavingsAccount(String.valueOf(pnr1));
                        //if name is changed
                        if (changedName > 0) {
                            grid.getChildren().clear();//clearing grid
                            //adding info labels
                            resultInformation.setText("Success! New savings account created for customer with personal number " + partPnr + ", " + partName + " " + partSurname + ".");
                            extraInfo.setText("Account number: " + changedName + ".");
                            //specifying positioning the GridPane
                            GridPane.setConstraints(resultInformation, 0, 0);
                            GridPane.setConstraints(extraInfo, 0, 1);
                            grid.getChildren().addAll(resultInformation, extraInfo);
                        }
                    });
                }else{
                    pnrInput.setText("");
                    resultInformation.setText("No customer found.");
                }
            } catch (NumberFormatException e) {
                pnrInput.setText("");
                resultInformation.setText("There was a problem with your input. Make sure you insert correct data.");
            }
        });
        //adding elemets to the grid
        grid.getChildren().addAll(personalNrLabel, pnrInput, getCustomer);

        //labels
        pageHeading.setText("Create savings account");
        pageDescription.setText("Create savings account for existing customer by providing customers' personal number.");

        //creates a layout pane for the scene
        BorderPane root = new BorderPane();

        //add in the VBox adding heading, text description and grid(form)
        VBox centerContent = new VBox(pageHeading, pageDescription, grid, resultInformation);

        //vbox will be positioned on the top(menu)
        root.setTop(vbox);
        //heading, text description and grid(form) will be position in the center
        root.setCenter(centerContent);

        scene1 = new Scene(root, 800, 600);

        //adding css classes
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        nameLabel.getStyleClass().add("labelsPage");
        surnameLabel.getStyleClass().add("labelsPage");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultInformation.getStyleClass().add("ResultLabelsPage");
        extraInfo.getStyleClass().add("ResultLabelsPage");
        nameInput.getStyleClass().add("input");
        surnameInput.getStyleClass().add("input");
        pnrInput.getStyleClass().add("input");
        getCustomer.getStyleClass().add("button");
        createAccount.getStyleClass().add("button");
        resultLabelPnr.getStyleClass().add("ResultLabelsPage");
        resultLabelName.getStyleClass().add("ResultLabelsPage");
        resultLabelSurname.getStyleClass().add("ResultLabelsPage");

        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: CREATE CREDIT ACCOUNT/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public void sceneCreateCreditAccount() {
        resultInformation.setText("");

        //creates  VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        //creating GridPane
        grid.setPadding(new Insets(10, 10, 10, 10)); //padding
        grid.setVgap(8); //vertical gap
        grid.setHgap(10); //horisontal gap
        grid.getChildren().clear();

        //Input field
        TextField pnrInput = new TextField();

        //specifying positioning the GridPane
        GridPane.setConstraints(personalNrLabel, 0, 0);
        GridPane.setConstraints(pnrInput, 1, 0);

        //button to submit
        Button getCustomer = new Button("Get customer");
        GridPane.setConstraints(getCustomer, 2, 0);
        Button createAccount = new Button("Create new credit account");

        //action handeler when user submits the form
        getCustomer.setOnAction(event -> {
            try {
                resultInformation.setText("");
                //getting users input
                Integer pnr = Integer.valueOf(pnrInput.getText());
                //calling createCustomer in BankLogic
                ArrayList<String> accountCustomer = bankLogic.getCustomer(String.valueOf(pnr));
                //if there's a match, customer exists
                if (accountCustomer != null && !accountCustomer.isEmpty()) {
                    resultInformation.setText("");
                    //splitting arraylist to get current values
                    String[] parts = accountCustomer.get(0).split(" ");
                    //assigning currentValues
                    String partPnr = parts[0];
                    String partName = parts[1];
                    String partSurname = parts[2];
                    //setting them into already declared Labels
                    resultLabelPnr1.setText("Personal number: " + partPnr);
                    resultLabelName1.setText("Name: " + partName);
                    resultLabelSurname1.setText("Surname: " + partSurname);
                    //specifying positioning the GridPane
                    GridPane.setConstraints(resultLabelPnr, 0, 0);
                    GridPane.setConstraints(resultLabelName, 0, 1);
                    GridPane.setConstraints(resultLabelSurname, 0, 2);

                    GridPane.setConstraints(createAccount, 0, 3);
                    grid.getChildren().clear(); //clearing grid before adding new values
                    //adding elemets to the grid
                    grid.getChildren().addAll(createAccount, resultLabelPnr, resultLabelName, resultLabelSurname);
                    //action handeler when user submits the second form
                    createAccount.setOnAction(event1 -> {
                        //getting users input
                        Integer pnr1 = Integer.valueOf(pnrInput.getText());
                        //calling createCustomer in BankLogic
                        int accNr = bankLogic.createCreditAccount(String.valueOf(pnr1));
                        //if name is changed
                        if (accNr > 0) {
                            grid.getChildren().clear();//clearing grid
                            //adding info labels
                            resultInformation.setText("Success! New credit account created for customer with personal number " + partPnr + ", " + partName + " " + partSurname + ".");
                            extraInfo.setText("Account number: " + accNr + ".");
                            //specifying positioning the GridPane
                            GridPane.setConstraints(resultInformation, 0, 0);
                            GridPane.setConstraints(extraInfo, 0, 1);
                            grid.getChildren().addAll(resultInformation, extraInfo);
                        }
                    });
                } else{
                    pnrInput.setText("");
                    resultInformation.setText("No customer found.");
                }
            } catch (NumberFormatException e) {
                pnrInput.setText("");
                resultInformation.setText("There was a problem with your input. Make sure you insert correct data.");
            }
        });
        //adding elemets to the grid
        grid.getChildren().addAll(personalNrLabel, pnrInput, getCustomer);

        //labels
        pageHeading.setText("Create credit account");
        pageDescription.setText("Here you can create credit accounts for existing customers.");

        //creates a layout pane for the scene
        BorderPane root = new BorderPane();

        //add in the VBox adding heading, text description and grid(form)
        VBox centerContent = new VBox(pageHeading, pageDescription, grid, resultInformation);

        //vbox will be positioned on the top(menu)
        root.setTop(vbox);
        //heading, text description and grid(form) will be position in the center
        root.setCenter(centerContent);

        // Create the scene with the layout
        scene1 = new Scene(root, 800, 600);

        //adding css classes
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        nameLabel.getStyleClass().add("labelsPage");
        surnameLabel.getStyleClass().add("labelsPage");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultInformation.getStyleClass().add("ResultLabelsPage");
        extraInfo.getStyleClass().add("ResultLabelsPage");
        nameInput.getStyleClass().add("input");
        surnameInput.getStyleClass().add("input");
        pnrInput.getStyleClass().add("input");
        getCustomer.getStyleClass().add("button");
        createAccount.getStyleClass().add("button");
        resultLabelPnr1.getStyleClass().add("ResultLabelsPage");
        resultLabelName1.getStyleClass().add("ResultLabelsPage");
        resultLabelSurname1.getStyleClass().add("ResultLabelsPage");

        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: SEE ALL CUSTOMERS - TABLE////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public void sceneSeeAllCustomers() {
        //gridpane for the table
        GridPane tableGrid = new GridPane();
        tableGrid.setPadding(new Insets(20, 10, 10, 90)); //padding
        tableGrid.setVgap(8); //vertical gap
        tableGrid.setHgap(10); //horisontal gap

        resultInformation.setText("");

        //creating table
        TableView<String> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //to show only columns that exist
        tableView.getStyleClass().add("table");

        //creates columns for customer properties (personal number, name, surname)
        TableColumn<String, String> personalNumberColumn = new TableColumn<>("Personal Number");
        personalNumberColumn.setMinWidth(200);
        personalNumberColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().split(" ")[0]));

        TableColumn<String, String> nameColumn = new TableColumn<>("First Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().split(" ")[1]));

        TableColumn<String, String> lastNameColumn = new TableColumn<>("Surname");
        lastNameColumn.setMinWidth(200);
        lastNameColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().split(" ")[2]));

        //adds the columns to the table view
        tableView.getColumns().addAll(personalNumberColumn, nameColumn, lastNameColumn);

        //calling the getAllCustomers method to retrieve the data
        ArrayList<String> customerData = bankLogic.getAllCustomers();

        //adding the customer data to the table view
        tableView.getItems().addAll(customerData);
        tableGrid.getChildren().add(tableView);

        // creates VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        pageHeading.setText("See all customers");

        // creates a layout pane for the scene
        BorderPane root = new BorderPane();

        // add in the VBox adding heading, text description, and grid(form)
        VBox centerContent = new VBox(pageHeading,  tableGrid);

        // vbox will be positioned on the top(menu)
        root.setTop(vbox);
        // heading, text description, and grid(form) will be positioned in the center
        root.setCenter(centerContent);

        // Create the scene with the layout
        scene1 = new Scene(root, 800, 600);

        //adds css classes
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        nameLabel.getStyleClass().add("labelsPage");
        surnameLabel.getStyleClass().add("labelsPage");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultInformation.getStyleClass().add("ResultLabelsPage");
        nameInput.getStyleClass().add("input");
        surnameInput.getStyleClass().add("input");
        pnrInput.getStyleClass().add("input");

        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: SEE ACCOUNT INFORMATION////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public void sceneSeeAccountInfo() {
        clearAllCssClassLabels();

        //buttons
        Button seeAccounts = new Button();
        Button seeAccountInfo = new Button();

        //for dropdown, it will store combo box of strings
        ComboBox<String> accountDropdown = new ComboBox<>();

        //creates  VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        //creating GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10)); //padding
        grid.setVgap(8); //vertical gap
        grid.setHgap(10); //horisontal gap

        pageHeading.setText("Get account information");
        pageDescription.setText("See details about specific account by providing user's personal number.");

        //label for the input
        //Input field
        TextField pnrInput = new TextField();

        //specifying positioning the GridPane
        //second parameter is column index, third is row index
        GridPane.setConstraints(personalNrLabel, 0, 0);
        GridPane.setConstraints(pnrInput, 1, 0);

        //button to submit
        seeAccounts.setText("Get customer's accounts");
        GridPane.setConstraints(seeAccounts, 2, 0);

        //action handeler when user submits the form with users pnr
        seeAccounts.setOnAction(event -> {
            try {
        resultLabelPnr1.getStyleClass().add("delAccLabel");
        resultLabelName1.getStyleClass().add("delAccLabel");
        resultLabelSurname1.getStyleClass().add("delAccLabel");


                pageDescription.setText("Please select account you want to see.");
                //getting users input as integer type
                int pnr = Integer.parseInt(pnrInput.getText());
                //calling getCustomer() in BankLogic
                ArrayList<String> customer = bankLogic.getCustomer(String.valueOf(pnr));
                //information about customer that is stored in ArrayList<String> customer gets saved as array and elements are split by " "
                if (customer != null ) {
                    if(customer.size() < 2){
                        resultInformation.setText("No account.");
                        System.out.println(customer.size());
                    }else {
                        //spliting arraylist and saving individual items
                        resultInformation.setText("");
                        String[] parts = customer.get(0).split(" ");
                        String partPnr = parts[0];
                        String partName = parts[1];
                        String partSurname = parts[2];
                        customerH.setText("Customer details:");
                        resultLabelPnr1.setText("Personal number: " + partPnr);
                        resultLabelName1.setText("Name: " + partName);
                        resultLabelSurname1.setText("Surname: " + partSurname);
                        accoumtsH.setText("Customer's accounts:");

                        //button
                        seeAccountInfo.setText("See account information");
                        GridPane.setConstraints(seeAccountInfo, 2, 1);

                        //clearing dropdown
                        accountDropdown.getItems().clear();

                        //looping through customer arraylist to get account numbers for dropdown
                        for (int i = 1; i < customer.size(); i++) {
                            String[] accountParts = customer.get(i).split(" ");
                            String accountNumber = accountParts[0];

                            //adding each account number as a separate item in the dropdown
                            accountDropdown.getItems().add(accountNumber);

                            //clearing grid to update it with new content
                            grid.getChildren().clear();

                            //customer details data
                            GridPane.setConstraints(customerH, 0, 0);
                            GridPane.setConstraints(resultLabelPnr1, 0, 1);
                            GridPane.setConstraints(resultLabelName1, 0, 2);
                            GridPane.setConstraints(resultLabelSurname1, 0, 3);

                            //dropdown
                            GridPane.setConstraints(accoumtsH, 1, 0);
                            GridPane.setConstraints(accountDropdown, 1, 1);

                            //adding items to the grid
                            grid.getChildren().add(customerH);
                            grid.getChildren().add(resultLabelPnr1);
                            grid.getChildren().add(resultLabelName1);
                            grid.getChildren().add(resultLabelSurname1);
                            grid.getChildren().add(accountDropdown);
                            grid.getChildren().add(accoumtsH);
                            grid.getChildren().add(seeAccountInfo);
                        }

                        //checking if there are elements in accountDropdown and if yes, first item is default shown in dropdown
                        if (accountDropdown.getItems().size() > 0) {
                            accountDropdown.setValue(accountDropdown.getItems().get(0));
                        }

                        //action handeler when clicks on the second button, after account selection in dropdown
                        seeAccountInfo.setOnAction(SecondEvent -> {

                            //getting parameters in right data types
                            String personalNr = String.valueOf(Integer.valueOf(pnr));
                            int accountValue = Integer.valueOf(accountDropdown.getValue());
                            String accountInfo = bankLogic.getAccount(personalNr, accountValue);
                            String[] accountParts = accountInfo.split(" ");
                            String partAccNr = "Account number: " + accountParts[0];
                            String partBalance = "Balance: " + accountParts[1];
                            String partAccType = "Account type: " + accountParts[2];
                            String partIntRate = "Interest rate: " + accountParts[3];

                            //setting text in labels
                            resultLabelAccNr.setText(partAccNr);
                            resultLabelBalance.setText(partBalance);
                            resultLabelAccType.setText(partAccType);
                            resultLabelAccInterestRate.setText(partIntRate);

                            pageDescription.setText("Account details for selected account:");

                            extraInfo.setText("Customer " + partName + " " + partSurname + " with personal number " + partPnr + ".");

                            grid.getChildren().clear();

                            GridPane.setConstraints(resultLabelAccNr, 0, 0);
                            GridPane.setConstraints(resultLabelBalance, 0, 1);
                            GridPane.setConstraints(resultLabelAccType, 0, 2);
                            GridPane.setConstraints(resultLabelAccInterestRate, 0, 3);

                            //adding items to the grid
                            grid.getChildren().add(resultLabelAccNr);
                            grid.getChildren().add(resultLabelBalance);
                            grid.getChildren().add(resultLabelAccType);
                            grid.getChildren().add(resultLabelAccInterestRate);

                            resultLabelAccNr.getStyleClass().add("ResultLabelsPage");
                            resultLabelBalance.getStyleClass().add("ResultLabelsPage");
                            resultLabelAccType.getStyleClass().add("ResultLabelsPage");
                            resultLabelAccInterestRate.getStyleClass().add("ResultLabelsPage");
                        });
                    }
                } else if(customer == null){
                    pnrInput.setText("");
                    resultInformation.setText("No customer found.");
                }
            } catch (NumberFormatException e) {
                pnrInput.setText("");
                resultInformation.setText("There was a problem with your input. Make sure you insert correct data.");
            }
        });

        //adding elemets to the grid
        grid.getChildren().addAll(personalNrLabel, pnrInput, seeAccounts);

        // Create a layout pane for the scene
        BorderPane root = new BorderPane();

        //in the VBox adding heading, text description and grid(form)
        VBox centerContent = new VBox(pageHeading, pageDescription, grid, resultInformation);

        //vbox will be positioned on the top(menu)
        root.setTop(vbox);
        //heading, text description and grid(form) will be position in the center
        root.setCenter(centerContent);

        // Create the scene with the layout
        scene1 = new Scene(root, 800, 600);

        //adding css classes
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        nameLabel.getStyleClass().add("labelsPage");
        surnameLabel.getStyleClass().add("labelsPage");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultInformation.getStyleClass().add("ResultLabelsPage");

        pnrInput.getStyleClass().add("input");
        seeAccounts.getStyleClass().add("button");
        seeAccountInfo.getStyleClass().add("button");
        accountDropdown.getStyleClass().add("dropdown");
        customerH.getStyleClass().add("ResultLabelsPage");
        accoumtsH.getStyleClass().add("ResultLabelsPage");


        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: DEPOSIT MONEY///////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public void sceneDepositMoney() {
        resultInformation.setText("");

        clearAllCssClassLabels();

        //input field
        TextField amount = new TextField();
        Label amoountLabel = new Label();

        //buttons
        Button getAccounts = new Button();
        Button makeTransaction = new Button();

        //for dropdown, it will store combo box of strings
        ComboBox<String> accountDropdown = new ComboBox<>();

        //creates  VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        //creating GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10)); //padding
        grid.setVgap(8); //vertical gap
        grid.setHgap(10); //horisontal gap

        pageHeading.setText("Deposit money");
        pageDescription.setText("Deposit money to an account by providing user's personal number.");
        //label for the input
        //Input field
        TextField pnrInput = new TextField();
        //specifying positioning the GridPane
        //second parameter is column index, third is row index
        GridPane.setConstraints(personalNrLabel, 0, 0);
        GridPane.setConstraints(pnrInput, 1, 0);
        //button to submit
        getAccounts.setText("Get customer's accounts");
        GridPane.setConstraints(getAccounts, 2, 0);

        //action handeler when user submits the form with users pnr
        getAccounts.setOnAction(event -> {
            resultInformation.setText("");
            try {
                pageDescription.setText("If user's data is correct, please select account you want to deposit money to and enter amount.");
                //getting users input as integer type
                int pnr = Integer.parseInt(pnrInput.getText());
                //calling getCustomer() in BankLogic
                ArrayList<String> customer = bankLogic.getCustomer(String.valueOf(pnr));
                //user doenst have an account
                if (customer != null ) {
                    if(customer.size() < 2){
                        resultInformation.setText("No account.");
                        pnrInput.setText("");
                    }else {
                        //information about customer that is stored in ArrayList<String> customer gets saved as array and elements are split by " "
                        String[] parts = customer.get(0).split(" ");
                        String partPnr = parts[0];
                        String partName = parts[1];
                        String partSurname = parts[2];
                        customerH.setText("Customer details:");
                        resultLabelPnr1.setText("Personal number: " + partPnr);
                        resultLabelName1.setText("Name: " + partName);
                        resultLabelSurname1.setText("Surname: " + partSurname);
                        accoumtsH.setText("Customer's accounts:");
                        amoountLabel.setText("Amount");
                        //button
                        makeTransaction.setText("Deposit money");

                        //clearing dropdown
                        accountDropdown.getItems().clear();

                        //looping through customer arraylist to get account numbers for dropdown
                        for (int i = 1; i < customer.size(); i++) {
                            String[] accountParts = customer.get(i).split(" ");
                            String accountNumber = accountParts[0];

                            //adding each account number as a separate item in the dropdown
                            accountDropdown.getItems().add(accountNumber);

                            //clearing grid to update it with new content
                            grid.getChildren().clear();

                            //customer details data
                            GridPane.setConstraints(customerH, 0, 0);
                            GridPane.setConstraints(resultLabelPnr1, 0, 1);
                            GridPane.setConstraints(resultLabelName1, 0, 2);
                            GridPane.setConstraints(resultLabelSurname1, 0, 3);

                            //dropdown
                            GridPane.setConstraints(accoumtsH, 1, 0);
                            GridPane.setConstraints(accountDropdown, 1, 1);

                            GridPane.setConstraints(amoountLabel, 2, 0);
                            GridPane.setConstraints(amount, 2, 1);

                            GridPane.setConstraints(makeTransaction, 3, 1);

                            //adding items to the grid
                            grid.getChildren().add(customerH);
                            grid.getChildren().add(resultLabelPnr1);
                            grid.getChildren().add(resultLabelName1);
                            grid.getChildren().add(resultLabelSurname1);
                            grid.getChildren().add(accountDropdown);
                            grid.getChildren().add(accoumtsH);
                            grid.getChildren().add(makeTransaction);
                            grid.getChildren().add(amoountLabel);
                            grid.getChildren().add(amount);
                        }

                        //checking if there are elements in accountDropdown and if yes, first item is default shown in dropdown
                        if (accountDropdown.getItems().size() > 0) {
                            accountDropdown.setValue(accountDropdown.getItems().get(0));
                        }
                        //action handeler when clicks on the second button, after account selection in dropdown
                        makeTransaction.setOnAction(SecondEvent -> {
                            int amountMoney = Integer.parseInt(amount.getText());
                            //getting parameters in right data types
                            String personalNr = String.valueOf(Integer.valueOf(pnr));
                            int accountValue = Integer.valueOf(accountDropdown.getValue());
                            //calloing a method in banklogic
                            boolean accountInfo = bankLogic.deposit(personalNr, accountValue, amountMoney);
                            if (accountInfo) {
                                resultInformation.setText("Success! " + amountMoney + "kr has been deposited to account " + accountValue + ".");
                            } else {
                                resultInformation.setText("Transaction failed!");
                            }
                            grid.getChildren().clear();
                        });
                    }
                } else if(customer == null){
                    resultInformation.setText("No customer found.");
                    pnrInput.setText("");
                }
            } catch (NumberFormatException e) {
                resultInformation.setText("There was a problem with your input. Make sure you insert correct data.");
                pnrInput.setText("");
            }
        });

        //adding elemets to the grid
        grid.getChildren().addAll(personalNrLabel, pnrInput, getAccounts);

        // Create a layout pane for the scene
        BorderPane root = new BorderPane();

        //in the VBox adding heading, text description and grid(form)
        VBox centerContent = new VBox(pageHeading, pageDescription, grid, resultInformation);

        //vbox will be positioned on the top(menu)
        root.setTop(vbox);
        //heading, text description and grid(form) will be position in the center
        root.setCenter(centerContent);

        // Create the scene with the layout
        scene1 = new Scene(root, 800, 600);

        //adds css classes
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        nameLabel.getStyleClass().add("labelsPage");
        surnameLabel.getStyleClass().add("labelsPage");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultInformation.getStyleClass().add("ResultLabelsPage");
        nameInput.getStyleClass().add("input");
        amount.getStyleClass().add("input1");
        pnrInput.getStyleClass().add("input");
        getAccounts.getStyleClass().add("button");
        makeTransaction.getStyleClass().add("button");
        accountDropdown.getStyleClass().add("dropdown");
        customerH.getStyleClass().add("ResultLabelsPage");
        accoumtsH.getStyleClass().add("ResultLabelsPage");
        amoountLabel.getStyleClass().add("ResultLabelsPage");
        resultLabelPnr1.getStyleClass().add("labelsPage");
        resultLabelName1.getStyleClass().add("labelsPage");
        resultLabelSurname1.getStyleClass().add("labelsPage");
        resultLabelAccNr.getStyleClass().add("ResultLabelsPage");
        resultLabelBalance.getStyleClass().add("ResultLabelsPage");
        resultLabelAccType.getStyleClass().add("ResultLabelsPage");
        resultLabelAccInterestRate.getStyleClass().add("ResultLabelsPage");

        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: WITHDRAW MONEY///////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public void sceneWithdrawMoney() {

        clearAllCssClassLabels();

        resultInformation.setText("");
        TextField amount = new TextField();
        Label amoountLabel = new Label();

        //buttons
        Button getAccounts = new Button();
        Button makeTransaction = new Button();

        //for dropdown, it will store combo box of strings
        ComboBox<String> accountDropdown = new ComboBox<>();

        //creates  VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        //creating GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10)); //padding
        grid.setVgap(8); //vertical gap
        grid.setHgap(10); //horisontal gap

        pageHeading.setText("Withdraw money");
        pageDescription.setText("Withdraw money from an account by providing user's personal number.");
        //label for the input
        //Input field
        TextField pnrInput = new TextField();
        //specifying positioning the GridPane
        //second parameter is column index, third is row index
        GridPane.setConstraints(personalNrLabel, 0, 0);
        GridPane.setConstraints(pnrInput, 1, 0);
        //button to submit
        getAccounts.setText("Get customer's accounts");
        GridPane.setConstraints(getAccounts, 2, 0);

        //action handeler when user submits the form with users pnr
        getAccounts.setOnAction(event -> {
            resultInformation.setText("");
            try {
                pageDescription.setText("If user's data is correct, please select account you want to deposit money to and enter amount.");
                //getting users input as integer type
                int pnr = Integer.parseInt(pnrInput.getText());
                //calling getCustomer() in BankLogic
                ArrayList<String> customer = bankLogic.getCustomer(String.valueOf(pnr));
                if (customer != null ) {
                    if(customer.size() < 2){
                        resultInformation.setText("No registered account.");
                        pnrInput.setText("");
                    }else {
                        pageDescription.setText("If user's data is correct, please select account you want to withdraw money from and enter amount.");
                        //getting users input as integer type
                        //information about customer that is stored in ArrayList<String> customer gets saved as array and elements are split by " "
                        String[] parts = customer.get(0).split(" ");
                        String partPnr = parts[0];
                        String partName = parts[1];
                        String partSurname = parts[2];
                        customerH.setText("Customer details:");
                        resultLabelPnr1.setText("Personal number: " + partPnr);
                        resultLabelName1.setText("Name: " + partName);
                        resultLabelSurname1.setText("Surname: " + partSurname);
                        accoumtsH.setText("Customer's accounts:");
                        amoountLabel.setText("Amount");
                        //button
                        makeTransaction.setText("Withdraw money");
                        //clearing dropdown
                        accountDropdown.getItems().clear();
                        //looping through customer arraylist to get account numbers for dropdown
                        for (int i = 1; i < customer.size(); i++) {
                            String[] accountParts = customer.get(i).split(" ");
                            String accountNumber = accountParts[0];
                            //adding each account number as a separate item in the dropdown
                            accountDropdown.getItems().add(accountNumber);
                            //clearing grid to update it with new content
                            grid.getChildren().clear();

                            //customer details data
                            GridPane.setConstraints(customerH, 0, 0);
                            GridPane.setConstraints(resultLabelPnr1, 0, 1);
                            GridPane.setConstraints(resultLabelName1, 0, 2);
                            GridPane.setConstraints(resultLabelSurname1, 0, 3);

                            //dropdown
                            GridPane.setConstraints(accoumtsH, 1, 0);
                            GridPane.setConstraints(accountDropdown, 1, 1);

                            GridPane.setConstraints(amoountLabel, 2, 0);
                            GridPane.setConstraints(amount, 2, 1);

                            GridPane.setConstraints(makeTransaction, 3, 1);

                            //adding items to the grid
                            grid.getChildren().add(customerH);
                            grid.getChildren().add(resultLabelPnr1);
                            grid.getChildren().add(resultLabelName1);
                            grid.getChildren().add(resultLabelSurname1);
                            grid.getChildren().add(accountDropdown);
                            grid.getChildren().add(accoumtsH);
                            grid.getChildren().add(makeTransaction);
                            grid.getChildren().add(amoountLabel);
                            grid.getChildren().add(amount);
                        }
                        //checking if there are elements in accountDropdown and if yes, first item is default shown in dropdown
                        if (accountDropdown.getItems().size() > 0) {
                            accountDropdown.setValue(accountDropdown.getItems().get(0));
                        }
                        //action handeler when clicks on the second button, after account selection in dropdown
                        makeTransaction.setOnAction(SecondEvent -> {
                            int amountMoney = Integer.parseInt(amount.getText());
                            //getting parameters in right data types
                            String personalNr = String.valueOf(Integer.valueOf(pnr));
                            int accountValue = Integer.valueOf(accountDropdown.getValue());
                            boolean accountInfo = bankLogic.withdraw(personalNr, accountValue, amountMoney);
                            if(accountInfo){
                                resultInformation.setText("Success! " + amountMoney + "kr has been withdrawn from account " + accountValue +".");
                            } else{
                                resultInformation.setText("Transaction failed!");
                            }
                            grid.getChildren().clear();
                        });
                    }
                } else if(customer == null){
                    resultInformation.setText("No customer found.");
                    pnrInput.setText("");
                }
            } catch (NumberFormatException e) {
                resultInformation.setText("There was a problem with your input. Make sure you insert correct data.");
                pnrInput.setText("");
            }
        });

        //adding elemets to the grid
        grid.getChildren().addAll(personalNrLabel, pnrInput, getAccounts);

        // Create a layout pane for the scene
        BorderPane root = new BorderPane();

        //in the VBox adding heading, text description and grid(form)
        VBox centerContent = new VBox(pageHeading, pageDescription, grid, resultInformation);

        //vbox will be positioned on the top(menu)
        root.setTop(vbox);
        //heading, text description and grid(form) will be position in the center
        root.setCenter(centerContent);

        // Create the scene with the layout
        scene1 = new Scene(root, 800, 600);

        //css classes
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        nameLabel.getStyleClass().add("labelsPage");
        surnameLabel.getStyleClass().add("labelsPage");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultInformation.getStyleClass().add("ResultLabelsPage");
        nameInput.getStyleClass().add("input");
        amount.getStyleClass().add("input1");
        pnrInput.getStyleClass().add("input");
        getAccounts.getStyleClass().add("button");
        makeTransaction.getStyleClass().add("button");
        accountDropdown.getStyleClass().add("dropdown");
        customerH.getStyleClass().add("ResultLabelsPage");
        accoumtsH.getStyleClass().add("ResultLabelsPage");
        amoountLabel.getStyleClass().add("ResultLabelsPage");
        resultLabelPnr1.getStyleClass().add("labelsPage");
        resultLabelName1.getStyleClass().add("labelsPage");
        resultLabelSurname1.getStyleClass().add("labelsPage");
        resultLabelAccNr.getStyleClass().add("ResultLabelsPage");
        resultLabelBalance.getStyleClass().add("ResultLabelsPage");
        resultLabelAccType.getStyleClass().add("ResultLabelsPage");
        resultLabelAccInterestRate.getStyleClass().add("ResultLabelsPage");

        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: CLOSE ACCOUNT///////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public void sceneCloseAccount() {
        clearAllCssClassLabels();
        pnrInput.setText("");
        resultInformation.setText("");

        //buttons
        Button getAccounts = new Button();
        Button closeAccount = new Button();

        //for dropdown, it will store combo box of strings
        ComboBox<String> accountDropdown = new ComboBox<>();

        //creates  VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        //creating GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10)); //padding
        grid.setVgap(8); //vertical gap
        grid.setHgap(10); //horisontal gap

        pageHeading.setText("Close account");
        pageDescription.setText("Close an account by providing user's personal number.");
        //label for the input
        //Input field
        TextField pnrInput = new TextField();
        //specifying positioning the GridPane
        //second parameter is column index, third is row index
        GridPane.setConstraints(personalNrLabel, 0, 0);
        GridPane.setConstraints(pnrInput, 1, 0);
        //button to submit
        getAccounts.setText("Get customer's accounts");
        GridPane.setConstraints(getAccounts, 2, 0);

        //action handeler when user submits the form with users pnr
        getAccounts.setOnAction(event -> {
            resultInformation.setText("");
            try {
                pageDescription.setText("If user's data is correct, please select account you want to deposit money to and enter amount.");
                //getting users input as integer type
                int pnr = Integer.parseInt(pnrInput.getText());
                //calling getCustomer() in BankLogic
                ArrayList<String> customer = bankLogic.getCustomer(String.valueOf(pnr));
                if (customer != null ) {
                    if(customer.size() < 2){
                        resultInformation.setText("No account.");
                        System.out.println(customer.size());
                    }else {
                        //information about customer that is stored in ArrayList<String> customer gets saved as array and elements are split by " "
                        String[] parts = customer.get(0).split(" ");
                        String partPnr = parts[0];
                        String partName = parts[1];
                        String partSurname = parts[2];
                        customerH.setText("Customer details:");
                        resultLabelPnr1.setText("Personal number: " + partPnr);
                        resultLabelName1.setText("Name: " + partName);
                        resultLabelSurname1.setText("Surname: " + partSurname);
                        accoumtsH.setText("Customer's accounts:");
                        //button
                        closeAccount.setText("Close Account");
                        //clearing dropdown
                        accountDropdown.getItems().clear();
                        //looping through customer arraylist to get account numbers for dropdown
                        for (int i = 1; i < customer.size(); i++) {
                            String[] accountParts = customer.get(i).split(" ");
                            String accountNumber = accountParts[0];
                            //adding each account number as a separate item in the dropdown
                            accountDropdown.getItems().add(accountNumber);
                            //clearing grid to update it with new content
                            grid.getChildren().clear();
                            //customer details data
                            GridPane.setConstraints(customerH, 0, 0);
                            GridPane.setConstraints(resultLabelPnr1, 0, 1);
                            GridPane.setConstraints(resultLabelName1, 0, 2);
                            GridPane.setConstraints(resultLabelSurname1, 0, 3);
                            //dropdown
                            GridPane.setConstraints(accoumtsH, 1, 0);
                            GridPane.setConstraints(accountDropdown, 1, 1);

                            GridPane.setConstraints(closeAccount, 3, 1);

                            //adding items to the grid
                            grid.getChildren().add(customerH);
                            grid.getChildren().add(resultLabelPnr1);
                            grid.getChildren().add(resultLabelName1);
                            grid.getChildren().add(resultLabelSurname1);
                            grid.getChildren().add(accountDropdown);
                            grid.getChildren().add(accoumtsH);
                            grid.getChildren().add(closeAccount);
                        }
                        //checking if there are elements in accountDropdown and if yes, first item is default shown in dropdown
                        if (accountDropdown.getItems().size() > 0) {
                            accountDropdown.setValue(accountDropdown.getItems().get(0));
                        }

                        //action handeler when clicks on the second button, after account selection in dropdown
                        closeAccount.setOnAction(SecondEvent -> {
                            //getting parameters in right data types
                            String personalNr = String.valueOf(Integer.valueOf(pnr));
                            int accountValue = Integer.valueOf(accountDropdown.getValue());
                            String accountInfo = bankLogic.closeAccount(personalNr, accountValue);
                            if(!accountInfo.isEmpty()){
                                //spliting arraylist and assigning individual values
                                String[] accountParts = accountInfo.split(" ");
                                String accountNumber = accountParts[0];
                                String accountBalanceFinal = accountParts[1];
                                String accountType = accountParts[2];
                                String accountInterest = accountParts[3];
                                resultInformation.setText("Success! Account " + accountNumber + " has been closed. Final balance: " + accountBalanceFinal + ", account type: " + accountType + ", total interest: " + accountInterest + ".");
                            } else{
                                resultInformation.setText("Transaction failed!");
                            }
                            grid.getChildren().clear();
                        });
                    }
                } else if(customer == null){
                    resultInformation.setText("No customer found.");
                }
            } catch (NumberFormatException e) {
                resultInformation.setText("There was a problem with your input. Make sure you insert correct data.");
            }
        });
        //adding elemets to the grid
        grid.getChildren().addAll(personalNrLabel, pnrInput, getAccounts);

        // Create a layout pane for the scene
        BorderPane root = new BorderPane();

        //in the VBox adding heading, text description and grid(form)
        VBox centerContent = new VBox(pageHeading, pageDescription, grid, resultInformation);

        //vbox will be positioned on the top(menu)
        root.setTop(vbox);
        //heading, text description and grid(form) will be position in the center
        root.setCenter(centerContent);

        // Create the scene with the layout
        scene1 = new Scene(root, 800, 600);

        //adding css
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        nameLabel.getStyleClass().add("labelsPage");
        surnameLabel.getStyleClass().add("labelsPage");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultInformation.getStyleClass().add("ResultLabelsPage");
        nameInput.getStyleClass().add("input");
        //amount.getStyleClass().add("input");
        pnrInput.getStyleClass().add("input");
        getAccounts.getStyleClass().add("button");
        //makeTransaction.getStyleClass().add("button");
        accountDropdown.getStyleClass().add("dropdown");
        customerH.getStyleClass().add("ResultLabelsPage");
        accoumtsH.getStyleClass().add("ResultLabelsPage");
        // amoountLabel.getStyleClass().add("ResultLabelsPage");
        resultLabelPnr1.getStyleClass().add("labelsPage");
        resultLabelName1.getStyleClass().add("labelsPage");
        resultLabelSurname1.getStyleClass().add("labelsPage");
        resultLabelAccNr.getStyleClass().add("ResultLabelsPage");
        resultLabelBalance.getStyleClass().add("ResultLabelsPage");
        resultLabelAccType.getStyleClass().add("ResultLabelsPage");
        resultLabelAccInterestRate.getStyleClass().add("ResultLabelsPage");

        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: SEE ALL TRANSACTIONS///////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public void sceneSeeAllTransactions() {

        clearAllCssClassLabels();


        pnrInput.setText("");
        resultInformation.setText("");

        //cereating new table
        TableView<String> transactions = new TableView<>();
        transactions.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);//awoiding empty columns
        transactions.getStyleClass().add("table");

        //buttons
        Button getAccounts = new Button();
        Button seeTransactions = new Button();

        //for dropdown, it will store combo box of strings
        ComboBox<String> accountDropdown = new ComboBox<>();

        //creates  VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        //creating GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10)); //padding
        grid.setVgap(8); //vertical gap
        grid.setHgap(10); //horisontal gap

        GridPane tableGrid = new GridPane();
        tableGrid.setPadding(new Insets(-50, 10, 10, 90)); //padding
        tableGrid.setVgap(8); //vertical gap
        tableGrid.setHgap(10); //horisontal gap

        pageHeading.setText("See transactions");
        pageDescription.setText("See an account's transactions by providing user's personal number.");
        //label for the input
        //Input field
        TextField pnrInput = new TextField();
        //specifying positioning the GridPane
        //second parameter is column index, third is row index
        GridPane.setConstraints(personalNrLabel, 0, 0);
        GridPane.setConstraints(pnrInput, 1, 0);
        //button to submit
        getAccounts.setText("Get customer's accounts");
        GridPane.setConstraints(getAccounts, 2, 0);

        //action handeler when user submits the form with users pnr
        getAccounts.setOnAction(event -> {
            try {
                pageDescription.setText("If user's data is correct, please select account you want to see transactions for.");
                //getting users input as integer type
                int pnr = Integer.parseInt(pnrInput.getText());
                //calling getCustomer() in BankLogic
                ArrayList<String> customer = bankLogic.getCustomer(String.valueOf(pnr));
                if (customer != null ) {
                    if(customer.size() < 2){
                        resultInformation.setText("No account.");
                        System.out.println(customer.size());
                    }else {
                        //information about customer that is stored in ArrayList<String> customer gets saved as array and elements are split by " "
                        String[] parts = customer.get(0).split(" ");
                        String partPnr = parts[0];
                        String partName = parts[1];
                        String partSurname = parts[2];
                        customerH.setText("Customer details:");
                        resultLabelPnr1.setText("Personal number: " + partPnr);
                        resultLabelName1.setText("Name: " + partName);
                        resultLabelSurname1.setText("Surname: " + partSurname);
                        accoumtsH.setText("Customer's accounts:");

                        //button
                        seeTransactions.setText("Get transactions");

                        //clearing dropdown
                        accountDropdown.getItems().clear();

                        //looping through customer arraylist to get account numbers for dropdown
                        for (int i = 1; i < customer.size(); i++) {
                            String[] accountParts = customer.get(i).split(" ");
                            String accountNumber = accountParts[0];

                            //adding each account number as a separate item in the dropdown
                            accountDropdown.getItems().add(accountNumber);

                            //clearing grid to update it with new content
                            grid.getChildren().clear();

                            //customer details data
                            GridPane.setConstraints(customerH, 0, 0);
                            GridPane.setConstraints(resultLabelPnr1, 0, 1);
                            GridPane.setConstraints(resultLabelName1, 0, 2);
                            GridPane.setConstraints(resultLabelSurname1, 0, 3);

                            //dropdown
                            GridPane.setConstraints(accoumtsH, 1, 0);
                            GridPane.setConstraints(accountDropdown, 1, 1);

                            GridPane.setConstraints(seeTransactions, 3, 1);

                            //adding items to the grid
                            grid.getChildren().add(customerH);
                            grid.getChildren().add(resultLabelPnr1);
                            grid.getChildren().add(resultLabelName1);
                            grid.getChildren().add(resultLabelSurname1);
                            grid.getChildren().add(accountDropdown);
                            grid.getChildren().add(accoumtsH);
                            grid.getChildren().add(seeTransactions);
                        }

                        //checking if there are elements in accountDropdown and if yes, first item is default shown in dropdown
                        if (accountDropdown.getItems().size() > 0) {
                            accountDropdown.setValue(accountDropdown.getItems().get(0));
                        }

                        //action handeler when clicks on the second button, after account selection in dropdown
                        seeTransactions.setOnAction(SecondEvent -> {
                            //adding data in columns
                            TableColumn<String, String> dateAndTime = new TableColumn<>("Date and time");
                            dateAndTime.setMinWidth(200);

                            TableColumn<String, String> amount = new TableColumn<>("Transaction");
                            amount.setMinWidth(200);

                            TableColumn<String, String> balance = new TableColumn<>("Balance");
                            balance.setMinWidth(200);

                            transactions.getColumns().addAll(dateAndTime, amount, balance);

                            //getting parameters in right data types
                            String personalNr = String.valueOf(Integer.valueOf(pnr));
                            int accountValue = Integer.valueOf(accountDropdown.getValue());
                            ArrayList<String> accountInfo = bankLogic.getTransactions(personalNr, accountValue);

                            //getting individual values from arraylist
                            for (String item : accountInfo) {
                                String[] transactionss = item.split(", ");
                                for (String transaction : transactionss) {
                                    String[] partss = transaction.split(" ");
                                    if (partss.length >= 5) {
                                        String date = partss[0];
                                        String time = partss[1];
                                        String transactionValue = partss[2];
                                        String saldo = partss[4];
                                        transactions.getItems().add(date + ", " + time + ", " + transactionValue + ", " + saldo);
                                    }
                                }
                            }
                            //putting date and time together
                            dateAndTime.setCellValueFactory(data -> {
                                String[] splitData = data.getValue().split(", ");
                                String concatenatedValue = splitData[0] + ", " + splitData[1];
                                return new ReadOnlyStringWrapper(concatenatedValue);
                            });
                            amount.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().split(", ")[2]));
                            balance.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().split(", ")[3]));

                            transactions.setItems(transactions.getItems());

                            grid.getChildren().clear();
                            pageDescription.setText("Transactions for selected account: ");
                            tableGrid.getChildren().addAll(transactions);
                        });
                    }
                } else if(customer == null){
                    resultInformation.setText("No customer found.");
                }
            } catch (NumberFormatException e) {
                resultInformation.setText("There was a problem with your input. Make sure you insert correct data.");
            }
        });

        //adding elemets to the grid
        grid.getChildren().addAll(personalNrLabel, pnrInput, getAccounts);

        // Create a layout pane for the scene
        BorderPane root = new BorderPane();

        //in the VBox adding heading, text description and grid(form)
        VBox centerContent = new VBox(pageHeading, pageDescription, grid, resultInformation, tableGrid);

        //vbox will be positioned on the top(menu)
        root.setTop(vbox);
        //heading, text description and grid(form) will be position in the center
        root.setCenter(centerContent);

        // Create the scene with the layout
        scene1 = new Scene(root, 800, 600);

        //adding css classes
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        nameLabel.getStyleClass().add("labelsPage");
        surnameLabel.getStyleClass().add("labelsPage");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultInformation.getStyleClass().add("ResultLabelsPage");
        nameInput.getStyleClass().add("input");
        surnameInput.getStyleClass().add("input");
        pnrInput.getStyleClass().add("input");
        getAccounts.getStyleClass().add("button");
        seeTransactions.getStyleClass().add("button");
        accountDropdown.getStyleClass().add("dropdown");
        customerH.getStyleClass().add("ResultLabelsPage");
        accoumtsH.getStyleClass().add("ResultLabelsPage");
        resultLabelPnr1.getStyleClass().add("labelsPage");
        resultLabelName1.getStyleClass().add("labelsPage");
        resultLabelSurname1.getStyleClass().add("labelsPage");
        resultLabelAccNr.getStyleClass().add("ResultLabelsPage");
        resultLabelBalance.getStyleClass().add("ResultLabelsPage");
        resultLabelAccType.getStyleClass().add("ResultLabelsPage");
        resultLabelAccInterestRate.getStyleClass().add("ResultLabelsPage");

        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }



    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: Export data/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public void sceneExportData() {
        //clearing css classes
        clearAllCssClassLabels();

        resultInformationFileHandeling.setText("");

        //creates  VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        //creating GridPane
        grid.setPadding(new Insets(10, 10, 10, 10)); //padding
        grid.setVgap(8); //vertical gap
        grid.setHgap(10); //horisontal gap
        grid.getChildren().clear();

        //labels
        pageHeading.setText("Export Data");
        pageHeading.getStyleClass().add("mainPageH1");
        pageDescription.setText("Export bank data into binary format");

        //button to submit
        Button button = new Button("Export data");
        GridPane.setConstraints(button, 0, 0);

        //adding items into the grid
        grid.getChildren().addAll(button);

        //action handeler when user submits the form
        //when user clicks on the button, event will be started.
        button.setOnAction(event -> {
            //try
            //creating new objects fileHandler and data
            //data object gets assigned to values stored in BankLogic object bankLogic
            //exporting data
            FileHandler fileHandler = new FileHandler();
            BankData data = new BankData(bankLogic.getAllAccounts(), bankLogic.getAllCustomers(), bankLogic.getAllAccountCustomersPairs(), bankLogic.getAllTransactions());
            fileHandler.exportData(data);
            resultInformationFileHandeling.setText("Success. Bank data exported!");
        });
        //creates a layout pane for the scene
        BorderPane root = new BorderPane();

        //add in the VBox adding heading, text description and grid(form)
        VBox centerContent = new VBox(pageHeading, pageDescription, grid, resultInformationFileHandeling);

        //vbox will be positioned on the top(menu)
        root.setTop(vbox);
        //heading, text description and grid(form) will be position in the center
        root.setCenter(centerContent);

        // Create the scene with the layout
        scene1 = new Scene(root, 800, 600);

        //adding css classes for elements
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        nameLabel.getStyleClass().add("labelsPage");
        surnameLabel.getStyleClass().add("labelsPage");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultInformationFileHandeling.getStyleClass().add("ResultLabelsPage");
        button.getStyleClass().add("button");
        nameInput.getStyleClass().add("input");
        surnameInput.getStyleClass().add("input");
        pnrInput.getStyleClass().add("input");

        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }



    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: Import data/////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public void sceneImportData() {
        //clearing css classes
        clearAllCssClassLabels();

        resultInformationFileHandeling.setText("");

        //creates  VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        //creating GridPane
        grid.setPadding(new Insets(10, 10, 10, 10)); //padding
        grid.setVgap(8); //vertical gap
        grid.setHgap(10); //horisontal gap
        grid.getChildren().clear();

        //labels
        pageHeading.setText("Import Data");
        pageHeading.getStyleClass().add("mainPageH1");
        pageDescription.setText("Import new bank data in binary format");

        //button to submit
        Button button = new Button("Import data");
        GridPane.setConstraints(button, 0, 0);

        //adding items into the grid
        grid.getChildren().addAll(button);

        //action handeler when user submits the form
        //when user clicks on the button, event will be started.
        button.setOnAction(event -> {
            //creating new instance of FileHandler
            //importing data into BankData data
            FileHandler fileHandler = new FileHandler();
            BankData data = fileHandler.importData(bankLogic);
            //if there was data in the imported file
            if (data != null) {
                //calling importDataIntoClasses inside BankLogic
                bankLogic.importDataIntoClasses(data);
                resultInformationFileHandeling.setText("Success. Bank data imported!");
                //error handler
            } else {
                resultInformationFileHandeling.setText(fileHandler.getErrorMessage());
            }
        });

        //creates a layout pane for the scene
        BorderPane root = new BorderPane();

        //add in the VBox adding heading, text description and grid(form)
        VBox centerContent = new VBox(pageHeading, pageDescription, grid, resultInformationFileHandeling);

        //vbox will be positioned on the top(menu)
        root.setTop(vbox);
        //heading, text description and grid(form) will be position in the center
        root.setCenter(centerContent);

        // Create the scene with the layout
        scene1 = new Scene(root, 800, 600);

        //adding css classes for elements
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        nameLabel.getStyleClass().add("labelsPage");
        surnameLabel.getStyleClass().add("labelsPage");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultInformationFileHandeling.getStyleClass().add("ResultLabelsPage");
        nameInput.getStyleClass().add("input");
        surnameInput.getStyleClass().add("input");
        pnrInput.getStyleClass().add("input");

        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();
    }


    ////////////////////////////////////////////////////////////////////////////
    ////////////////////SCENE: Export transactions//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    public void sceneExportTransactions() {

        clearAllCssClassLabels();

        pnrInput.setText("");
        resultInformation.setText("");

        Label resultInformation1 = new Label();

        //buttons
        Button getAccounts = new Button();
        Button seeTransactions = new Button();

        //for dropdown, it will store combo box of strings
        ComboBox<String> accountDropdown = new ComboBox<>();

        //creates  VBox for menuBar
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(menuBar);

        //creating GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10)); //padding
        grid.setVgap(8); //vertical gap
        grid.setHgap(10); //horisontal gap

        pageHeading.setText("Export transactions");
        pageDescription.setText("Export transactions into a text file.");
        //label for the input
        //Input field
        TextField pnrInput = new TextField();
        //specifying positioning the GridPane
        //second parameter is column index, third is row index
        GridPane.setConstraints(personalNrLabel, 0, 0);
        GridPane.setConstraints(pnrInput, 1, 0);
        //button to submit
        getAccounts.setText("Get accounts");
        GridPane.setConstraints(getAccounts, 2, 0);

        //action handeler when user submits the form with users pnr
        getAccounts.setOnAction(event -> {
            try {
                pageDescription.setText("If user's data is correct, please select account you want to see transactions for.");
                //getting users input as integer type
                int pnr = Integer.parseInt(pnrInput.getText());
                //calling getCustomer() in BankLogic
                ArrayList<String> customer = bankLogic.getCustomer(String.valueOf(pnr));
                if (customer != null ) {
                    if(customer.size() < 2){
                        resultInformation.setText("No account.");
                        System.out.println(customer.size());
                    }else {
                        //information about customer that is stored in ArrayList<String> customer gets saved as array and elements are split by " "
                        String[] parts = customer.get(0).split(" ");
                        String partPnr = parts[0];
                        String partName = parts[1];
                        String partSurname = parts[2];
                        customerH.setText("Customer details:");
                        resultLabelPnr1.setText("Personal number: " + partPnr);
                        resultLabelName1.setText("Name: " + partName);
                        resultLabelSurname1.setText("Surname: " + partSurname);
                        accoumtsH.setText("Customer's accounts:");

                        //button
                        seeTransactions.setText("Export");

                        //clearing dropdown
                        accountDropdown.getItems().clear();

                        //looping through customer arraylist to get account numbers for dropdown
                        for (int i = 1; i < customer.size(); i++) {
                            String[] accountParts = customer.get(i).split(" ");
                            String accountNumber = accountParts[0];

                            //adding each account number as a separate item in the dropdown
                            accountDropdown.getItems().add(accountNumber);

                            //clearing grid to update it with new content
                            grid.getChildren().clear();

                            //customer details data
                            GridPane.setConstraints(customerH, 0, 0);
                            GridPane.setConstraints(resultLabelPnr1, 0, 1);
                            GridPane.setConstraints(resultLabelName1, 0, 2);
                            GridPane.setConstraints(resultLabelSurname1, 0, 3);

                            //dropdown
                            GridPane.setConstraints(accoumtsH, 1, 0);
                            GridPane.setConstraints(accountDropdown, 1, 1);

                            GridPane.setConstraints(seeTransactions, 3, 1);

                            //adding items to the grid
                            grid.getChildren().add(customerH);
                            grid.getChildren().add(resultLabelPnr1);
                            grid.getChildren().add(resultLabelName1);
                            grid.getChildren().add(resultLabelSurname1);
                            grid.getChildren().add(accountDropdown);
                            grid.getChildren().add(accoumtsH);
                            grid.getChildren().add(seeTransactions);
                        }

                        //checking if there are elements in accountDropdown and if yes, first item is default shown in dropdown
                        if (accountDropdown.getItems().size() > 0) {
                            accountDropdown.setValue(accountDropdown.getItems().get(0));
                        }

                        //action handeler when clicks on the second button, after account selection in dropdown
                        seeTransactions.setOnAction(SecondEvent -> {
                            //getting the number of selected account
                            int selectedAccount = Integer.parseInt(accountDropdown.getValue());
                            //creating a new instance of FileHandler
                            FileHandler fileHandler = new FileHandler();
                            //calling the method to save data in a text file
                            fileHandler.makeAccountStatementFile(partPnr, selectedAccount);
                            resultInformationFileHandeling.setText("Success. Bank data exported!");
                            grid.getChildren().clear();
                            grid.getChildren().addAll(resultInformationFileHandeling);
                        });
                    }
                } else if(customer == null){
                    resultInformation1.setText("No customer found.");
                }
            } catch (NumberFormatException e) {
                resultInformation1.setText("There was a problem with your input. Make sure you insert correct data.");
            }
        });

        //adding elemets to the grid
        grid.getChildren().addAll(personalNrLabel, pnrInput, getAccounts);

        // Create a layout pane for the scene
        BorderPane root = new BorderPane();

        //in the VBox adding heading, text description and grid(form)
        VBox centerContent = new VBox(pageHeading, pageDescription, grid, resultInformation1);

        //vbox will be positioned on the top(menu)
        root.setTop(vbox);
        //heading, text description and grid(form) will be position in the center
        root.setCenter(centerContent);

        // Create the scene with the layout
        scene1 = new Scene(root, 800, 600);

        //adding css classes
        pageHeading.getStyleClass().add("heading");
        pageDescription.getStyleClass().add("pageDescClass");
        nameLabel.getStyleClass().add("labelsPage");
        surnameLabel.getStyleClass().add("labelsPage");
        personalNrLabel.getStyleClass().add("labelsPage");
        resultInformation.getStyleClass().add("ResultLabelsPage");
        pnrInput.getStyleClass().add("input");
        accountDropdown.getStyleClass().add("dropdown");
        customerH.getStyleClass().add("ResultLabelsPage");
        accoumtsH.getStyleClass().add("ResultLabelsPage");
        resultLabelPnr1.getStyleClass().add("delAccLabel");
        resultLabelName1.getStyleClass().add("delAccLabel");
        resultLabelSurname1.getStyleClass().add("delAccLabel");

        scene1.getStylesheets().add("bankManager.css");

        // Set the scene on the stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Bank Manager App");
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}


