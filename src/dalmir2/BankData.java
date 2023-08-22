package dalmir2;

//student:Dalibor Mirkovic
//ltu-id: dalmir2

import java.io.*;
import java.util.ArrayList;

//Class BankData, being  Serializable means that it's instances can be converted into a byte stream and oposite
//it will be used to store data in binary form
//it will be used to "get" all the data we need for import/export
//it will be used to save data into binary form when we want to export data as a file and when we want to import file
//that is in binary form and translate it into data types used in banklogic
//logic for that is in FileHandler class, however logic implementation is done through BankLogic class and gui in Gui class
public class BankData implements Serializable {

    //class attributes
    private ArrayList<String> accounts;
    private ArrayList<String> customers;
    private ArrayList<String> pairs;
    private ArrayList<String> transactions;

    //constructor
    public BankData(ArrayList<String> accounts, ArrayList<String> customers, ArrayList<String> pairs, ArrayList<String> transactions) {
        this.accounts = accounts;
        this.customers = customers;
        this.pairs = pairs;
        this.transactions = transactions;
    }

    //getters
    public ArrayList<String> getAccounts() {
        return accounts;
    }

    public ArrayList<String> getCustomers() {
        return customers;
    }

    public ArrayList<String> getPairs() {
        return pairs;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }
}
