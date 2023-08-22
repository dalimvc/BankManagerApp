package dalmir2;

//student:Dalibor Mirkovic
//ltu-id: dalmir2

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


//Account is Abstract as
public abstract class Account {

    private static int lastAssignedNumber = 1000;
    //class attributes
    private Integer accountNr;
    private double balance;
    private double interestRate;
    private String accountType;

    private static ArrayList<String> transactions = new ArrayList<String>();//ArrayList; it contains transaction details

    //constructor for class Account; it cointains abstract methods
    public Account(double balance, double interestRate, String accountType){
        //every time when we use constructor to create new object, value lastAssignedNumber will get up by 1
        lastAssignedNumber++;
        //accountNr gets assigned value of lastAssignedNumber. First assigned value will be 1001(line 8 (+ 1))
        this.accountNr = lastAssignedNumber;
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountType = accountType;
        //when an instance on class is made, it will be saved as Account arraylist in BankLogic
        BankLogic.addAccount(this);
    }


    //getter for accountNr
    public  int getAccountNr(){
        return this.accountNr;
    }

    //getter for balance
    public  double getAccountBalance(){
        return this.balance;
    }

    //getter for interest rate
    public  double getInterestRate(){
        return this.interestRate;
    }

    //getter for account type
    public  String getAccountType(){
        return this.accountType;
    }

    //getter for transactions
    public static   ArrayList<String> getTransactions(){
        return transactions;
    }

    //setter 2, for setting balance into account after withdraw
    public void setNewBalance(int amount) {
        this.balance = amount;
    }

    //setter for interest rate; it will be used in children classes
    public void setInterestRate(double intRate){
        this.interestRate = intRate;
    }

    //method to save transactions. in children classes is used in their methods
    //as method will be invoked in BankLogic class, that class stores pairs customer id and bank account id and BankLogic will check to make sure that parameters match
    public void saveTransaction(String pNo, int accountId, int amount ){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String strDate = sdf.format(date);
        //get balance in swedish crowns format
        String amountStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(amount);
        String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(balance);
        transactions.add(String.valueOf(pNo + " " + accountId + " " + strDate + " " + amountStr + " Saldo: " + balanceStr));
    }

    //method to get transactions for a specific customer and specific account
    public String showTransactions(String pNo, int accountId) {
        ArrayList<String> customerTransactions = new ArrayList<String>();//arraylist to be returned
        //looping through transactions arraylist
        for (String transaction : transactions) {
            // spliting transactions arraylist into its parts
            String[] parts = transaction.split(" ");
            String personalNumber = parts[0]; //personal number
            String accountNumber = parts[1]; //account number
            String date = parts[2] + " " + parts[3]; //datum and time
            String amount = parts[4]; //withdrawn amount
            String saldo = parts[5] + " " + parts[6]; //balance
            //if there is a match (personal number - account number)
            if (personalNumber.equals(pNo) && accountNumber.equals(Integer.toString(accountId))) {
                //each match will be added into customerTransactions(account number and customer personal number will not be added)
                customerTransactions.add(date + " " + amount + " " + saldo);
            }
        }
        return customerTransactions.toString();
    }

    //abstract method withdraw; in parent class there is method signature, it will be defined in subclasses
    public abstract void withdraw(String pNo, int accountId, int amount);

    //method for depositing money, it is the same for all children classes
    public void deposit(String pNo, int accountId, int amount) {
        int newSaldo= (int) (getAccountBalance() + amount);
        setNewBalance(newSaldo);
        saveTransaction(pNo, accountId,amount); //saving transaction
    }

    //abstract method setInterestrateForAccount; in parent class there is method signature, it will be defined in subclasses
    //it uses setter setInterestRate() as well
    public abstract double setInterestrateForAccount();

    //saving transactions from a file into arraylist transactions, it will be implemented in banklogic
    public static void saveTransactionFromAFile(String pNo, int accountId, String strDate, String amountStr, String balanceStr){
        transactions.add(String.valueOf(pNo + " " + accountId + " " + strDate + " " + amountStr + " Saldo: " + balanceStr));
    }














}

