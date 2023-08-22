package dalmir2;

//student:Dalibor Mirkovic
//ltu-id: dalmir2

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//Class that handles data translation from/to binary form
//data is stored in BankData
public class FileHandler {

    //storring error messages
    private String errorMessage;

    //location for the file to be imported/exported into binary form
    String relativeFilePath = "dalmir2_files/BankData.bin";

    //location for the text file, when we export transactions
    String filePath = "dalmir2_files/accountStatement.txt";

    //method for exporting data into a binary type file
    //it takes BankData object as a parameter and transforms it into binary form and saves it at location: relativeFilePath
    //trr... catch block is used to handle errors
    public void exportData(BankData data) {
        try (FileOutputStream fos = new FileOutputStream(relativeFilePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //getter for error messages
    public String getErrorMessage() {
        return errorMessage;
    }

    //this method will be used to take in BankLogic object into binary form and saves it as in its original data format
    //it will be called in GUI and further logic it will be implemented in BankLogic
    //same as previous method it uses a series of try...catch blocks to handle errors
    public BankData importData(BankLogic bankLogic) {
        BankData dataWrapper = null;
        try (FileInputStream fis = new FileInputStream(relativeFilePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            dataWrapper = (BankData) ois.readObject();
        } catch (FileNotFoundException e) {
            errorMessage = "Error: File not found. The specified file does not exist.";
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage = "Error importing data: " + e.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: BankData class not found.");
        } catch (SecurityException e) {
            errorMessage = "Error: The file is protected or access is denied.";
        }
        return dataWrapper;
    }


    //method to create a file that prints out account statement for specific account and customer
    //it will be called in GUI
    public void makeAccountStatementFile(String personalNumber, int accountId) {
        try {
            //FileWriter is an object in charge to add text into a text file
            FileWriter statement = new FileWriter(filePath);

            //getting current date and adding it to FileWriter statement
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = dateFormat.format(new Date());
            statement.write("Account Statement - " + currentDate + "\n\n");

            //getting transactions from array list wit transactions using a method getTransactions in bankLogic
            BankLogic bankLogic = new BankLogic();
            ArrayList<String> transactions = bankLogic.getTransactions(personalNumber, accountId);

            //last element in the arraylist stores current balance
            //getting that value
            String lastElement = transactions.get(transactions.size() - 1);
            String[] parts = lastElement.split(" Saldo: ");
            String lastBalanceValue="";
            //adding transactions into FileWriter object
            if (transactions != null && !transactions.isEmpty()) {
                statement.write("Recent Transactions:\n");
                for (String transaction : transactions) {
                    statement.write(transaction + "\n");
                    lastBalanceValue = parts[parts.length - 1];
                }
                //no transactions case
            } else {
                statement.write("No transactions found for the account.\n");
            }
            //writing account balance
            statement.write("\nAccount Balance: " + lastBalanceValue + " SEK\n");

            statement.close();
            //catching error
        } catch (IOException e) {
            errorMessage = "Error writing account statement to file: " + e.getMessage();
        }
    }
}

