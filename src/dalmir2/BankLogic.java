package dalmir2;

//student:Dalibor Mirkovic
//ltu-id: dalmir2

import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Date;


public class BankLogic {

    private static ArrayList<Account> accounts = new ArrayList<Account>();//ArrayList with all Account objects
    private static ArrayList<Customer> customers = new ArrayList<Customer>();//ArrayList all Customer objects
    private static ArrayList<String> accountCustomer = new ArrayList<String>();//ArrayList; it contains pairs account number and customers personal number


    //help method; when new account is made in class Account it is automaticly added to array list accounts; method is called in class Account, in constructor
    public static void addAccount(Account account) {
        accounts.add(account);
    }

    //help method; when new customer object is made in class Customer it is automaticly added to array list customers; method is called in class Customer, in constructor
    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }


    //method to assign customer to account
    public static ArrayList assignAcountToCustomer(String personalNumber, Integer accountNumber) {
        ArrayList<String> nestedAccountCustomer = new ArrayList<String>();//temporary arraylist
        //double loop; for each account in arraylist accounts program will loop through each customer in arraylist customers
        for (Account acc : accounts) {
            for (Customer cust : customers) {
                //if there is an account number in accounts, that is identical to method parameter
                //and if there is personal number in customers that is identical to metod parameter
                if (acc.getAccountNr() == accountNumber && personalNumber.equals(cust.getCustomerPnr())) {
                    nestedAccountCustomer.add(String.valueOf(acc.getAccountNr()));//account number is added to nestedAccountCustomer
                    nestedAccountCustomer.add(cust.getCustomerPnr());//personal number is added to nestedAccountCustomer
                    accountCustomer.add(String.valueOf(nestedAccountCustomer));//string value of nestedAccountCustomer is added into accountCustomer
                }
            }
        }
        return accountCustomer;
    }


    //method to print out all customers
    public ArrayList<String> getAllCustomers() {
        ArrayList<String> allCustomers = new ArrayList<String>();//temporary arraylist
        //for each customer in arraylist Customers, personal number, first name and last name will be aded to allCustomers
        for (Customer cust : customers) {
            allCustomers.add(cust.getCustomerPnr() + " " + cust.getCustomerSurname() + " " + cust.getCustomerLastname());
        }
        return allCustomers;
    }

    //methos to create new customer
    public boolean createCustomer(String name, String surname, String pNo) {
        boolean addCust = true; //initialy addCust(a new customer is added) is true
        //loop through arraylist customers; if specific customer already exists addCust becomes false and loop stops(break comand)
        for (Customer cust : customers) {
            if (cust.getCustomerPnr().equals(pNo)) {
                addCust = false;
                break;
            }
        }
        //if customer doesn't exist in arraylist customers(addCust is true), new instance of Customer will be made
        if (addCust) {
            Customer newCustomer = new Customer(name, surname, pNo);
        }
        return addCust;
    }


    //method, when personal number is provided, data about customer and their accounts will be presented
    public ArrayList<String> getCustomer(String pNo) {
        double interest;
        ArrayList<String> customersInfo = new ArrayList<>();//arraylist, it will contain customers personal information
        ArrayList<String> customersAcounts = new ArrayList<>();//arraylist, it will contain all customers account numbers
        //for provided personal number, program will loop through arraylist customers and add customers first name, last name and personal number into customersInfo
        for (Customer custNr : customers) {
            if (custNr.getCustomerPnr().equals(pNo)) {
                customersInfo.add(custNr.getCustomerPnr() + " " + custNr.getCustomerSurname() + " " + custNr.getCustomerLastname());
            }
        }
        //getting numbers of all customers account
        //for each customer, account pair in accountCustomer they will be saved into array pairsAcountCustomer, line 91
        for (String accCust : accountCustomer) {
            String[] pairsAcountCustomer = accCust.replaceAll("[\\[\\]]", "").split(", "); //saving arraylist as array and removing "[ ] and ,"
            String pnr = pairsAcountCustomer[1]; //customers personal number has index 1 in pairsAcountCustomer; that gets assigned to string pnr
            //if personal number entered into method parameter is the same as value in String pnr, account number will be added into customersAcounts
            if (pnr.equals(pNo)) {
                customersAcounts.add(pairsAcountCustomer[0]);
            }
        }
        //double loop, for each account in accounts and customers account number in customersAcounts
        for (Account acc : accounts) {
            for (String custAccounts : customersAcounts) {
                //if account number in customersAcounts is the same as account number in accounts
                if (String.valueOf(acc.getAccountNr()).equals(custAccounts)) {
                    //account balance gets specific format(swedish crown)
                    String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(acc.getAccountBalance());
                    //next 3 line will be used to get % format
                    NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv", "SE"));
                    percentFormat.setMaximumFractionDigits(1); // specifying nr of decimals, in this case 1
                    //different accounts have different interest rates; that depends on if balance is higher than 0, method setInterestrateForAccount() in Account makes calculation
                    interest = acc.setInterestrateForAccount();
                    //interest rate in format ex. 1.3 %
                    String percentStr = percentFormat.format(interest / 100);
                    //adding into customersInfo arraylist data abouth specific bank accoun
                    customersInfo.add(String.valueOf(acc.getAccountNr()) + " " + balanceStr + " " + acc.getAccountType() + " " + percentStr);
                }
            }
        }
        //if there are no customers with specific personal number
        if (customersInfo.isEmpty()) {
            return null;
        } else {
            return customersInfo;
        }

    }


    //almost exactly the same as previous method getCustomer(). Difference is in "interest". Previous method presents interest in %. When closing account
    //interest should be presented in crowns
    public ArrayList<String> getCustomerWhenClosingAccount(String pNo) {
        ArrayList<String> customersInfo = new ArrayList<>();//arraylist, it will contain customers personal information
        ArrayList<String> customersAcounts = new ArrayList<>();//arraylist, it will contain all customers account numbers
        //for provided personal number, program will loop through arraylist customers and add customers first name, last name and personal number into customersInfo
        for (Customer custNr : customers) {
            if (custNr.getCustomerPnr().equals(pNo)) {
                customersInfo.add(custNr.getCustomerPnr() + " " + custNr.getCustomerSurname() + " " + custNr.getCustomerLastname());
            }
        }
        //getting numbers of all customers account
        //for each customer, account pair in accountCustomer they will be saved into array pairsAcountCustomer, line 91
        for (String entry : accountCustomer) {
            String[] pairsAcountCustomer = entry.replaceAll("[\\[\\]]", "").split(", ");//saving arraylist as array and removing "[ ] and ,"
            String pnr = pairsAcountCustomer[1];//customers personal number has index 1 in pairsAcountCustomer; that gets assigned to string pnr
            //if personal number entered into method parameter is the same as value in String pnr, account number will be added into customersAcounts
            if (pnr.equals(pNo)) {
                customersAcounts.add(pairsAcountCustomer[0]);
            }
        }
        //double loop, for each account in accounts and customers account number in customersAcounts
        for (Account acc : accounts) {
            for (String custAccounts : customersAcounts) {
                //if account number in customersAcounts is the same as account number in accounts
                if (String.valueOf(acc.getAccountNr()).equals(custAccounts)) {
                    //account balance gets specific format(swedish crown)
                    String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(acc.getAccountBalance());
                    //next line will be used to get % format
                    String interest = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(acc.getAccountBalance() * acc.getInterestRate() / 100);
                    //adding into customersInfo arraylist data abouth specific bank accoun
                    customersInfo.add(String.valueOf(acc.getAccountNr()) + " " + balanceStr + " " + acc.getAccountType() + " " + interest);
                }
            }
        }
        //if there are no customers with specific personal number
        if (customersInfo.isEmpty()) {
            return null;
        } else {
            return customersInfo;
        }
    }

    //method to change customers name
    public boolean changeCustomerName(String name, String surname, String pNo) {
        boolean nameChanged = false;
        //loop through all customers
        for (Customer cust : customers) {
            if (cust.getCustomerPnr().equals(pNo)) { //if provided pNo exists in customers
                if (name.isEmpty() && surname.isEmpty()) { //if parameters first name and last name are both empty
                    nameChanged = false;
                } else if (name.isEmpty()) { //if only first name is empty
                    cust.setCustomerLastName(surname); //last name gets changed
                    nameChanged = true;
                    break;
                } else if (surname.isEmpty()) { //if only lastname is empty
                    cust.setCustomerFirstName(name); //firstname gets changed
                    nameChanged = true;
                    break;
                } else {
                    //else, both get changed
                    cust.setCustomerFirstName(name);
                    cust.setCustomerLastName(surname);
                    nameChanged = true;
                    break;
                }
            }
        }
        return nameChanged;
    }


    //method to create savings account
    public int createSavingsAccount(String pNo) {
        ArrayList<String> nestedAccountCustomer = new ArrayList<String>(); //arraylist to be used in the method
        int acountNr = -1; //if account doesn't get created, program should return -1 (default value)
        //loop through all customers
        for (Customer cust : customers) {
            //if provided pNo exists in customers
            if (cust.getCustomerPnr().equals(pNo)) {
                //new savings account will be created
                Account savingsAccount = new SavingsAccount(0, 1.2);
                acountNr = savingsAccount.getAccountNr(); //gets value of account number for created savings account
                //account number and personal number will be added into nestedAccountCustomer
                nestedAccountCustomer.add(String.valueOf(savingsAccount.getAccountNr()));
                nestedAccountCustomer.add(pNo);
                //nestedAccountCustomer is added as nested arraylist in accountCustomer
                accountCustomer.add(String.valueOf(nestedAccountCustomer));
                break;
            }
        }
        return acountNr;
    }


    //method to create credit account
    public int createCreditAccount(String pNo) {
        ArrayList<String> nestedAccountCustomer = new ArrayList<String>(); //arraylist to be used in the method
        int acountNr = -1; //if account doesn't get created, program should return -1 (default value)
        //loop through all customers
        for (Customer cust : customers) {
            //if provided pNo exists in customers
            if (cust.getCustomerPnr().equals(pNo)) {
                //new credit account will be created
                Account creditAccount = new CreditAccount();
                acountNr = creditAccount.getAccountNr(); //gets value of account number for created credit account
                //account number and personal number will be added into nestedAccountCustomer
                nestedAccountCustomer.add(String.valueOf(creditAccount.getAccountNr()));
                nestedAccountCustomer.add(pNo);
                //nestedAccountCustomer is added as nested arraylist in accountCustomer
                accountCustomer.add(String.valueOf(nestedAccountCustomer));
                break;
            }
        }
        return acountNr;
    }


    //for provided personal number and account number, account details will be presented
    public String getAccount(String pNo, int accountId) {
        double interest;
        String customersAccount = null; //default value, it will stay null if there is no match
        //loop through accountCustomer
        //only to check if there customer has specific bank account
        for (String accCust : accountCustomer) {
            //array pairsAcountCustomer gets copy of array list accountCustomer without symbols "[ ] and ," next line
            String[] pairsAcountCustomer = accCust.replaceAll("[\\[\\]]", "").split(", ");
            String acc = pairsAcountCustomer[0]; //getting account number
            String pnr = pairsAcountCustomer[1]; // getting personal number
            //if pair in method parameters matches pair in pairsAcountCustomer
            if (pNo.equals(pnr) && acc.equals(String.valueOf(accountId))) {
                //if customer-account exists loop through accounts
                for (Account account : accounts) {
                    //if account number in account is the same as account number provided in parameter
                    if (account.getAccountNr() == accountId) {
                        //get balance in swedish crowns format
                        String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(account.getAccountBalance());
                        //next 3 line to format percentage
                        NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv", "SE"));
                        percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
                        //different accounts have different interest rates; that depends on if balance is higher than 0; method setInterestrateForAccount() in Account makes calculation
                        interest = account.setInterestrateForAccount();
                        String percentStr = percentFormat.format(interest / 100);
                        //add details about account to customersAccount
                        customersAccount = String.valueOf(account.getAccountNr() + " " + balanceStr + " " + account.getAccountType() + " " + percentStr);
                    }
                }
                break;
            }
        }
        return customersAccount;
    }


    //method to add deposit into account
    public boolean deposit(String pNo, int accountId, int amount) {
        boolean addDeposit = false; //initaily it's false(no deposit is added)
        //loop through accountCustomer
        for (String accCust : accountCustomer) {
            //array pairsAcountCustomer gets copy of array list accountCustomer without symbols "[ ] and ," next line
            String[] pairsAcountCustomer = accCust.replaceAll("[\\[\\]]", "").split(", ");
            String acc = pairsAcountCustomer[0]; //getting account number
            String pnr = pairsAcountCustomer[1]; // getting personal number
            //if pair in method parameters matches pair in pairsAcountCustomer
            if (pNo.equals(pnr) && acc.equals(String.valueOf(accountId))) {
                //loop through accounts
                for (Account account : accounts) {
                    //if specific account number is the same as the one in  parameter and amount is higher then 0
                    if (account.getAccountNr() == accountId && amount > 0) {
                        //using setter from class Account to add amount to the balance
                        account.deposit(pNo, accountId, amount);
                        addDeposit = true; //deposit is added
                        //get balance in swedish crowns format
                        break;
                    }
                }
            }
        }
        return addDeposit;
    }


    //method to withdraw money from bank account
    public boolean withdraw(String pNo, int accountId, int amount) {
        boolean withdrawMoney = false; //initialy no money is withdrawn
        //loop through accountCustomer
        for (String accCust : accountCustomer) {
            //array pairsAcountCustomer gets copy of array list accountCustomer without symbols "[ ] and ," next line
            String[] pairsAcountCustomer = accCust.replaceAll("[\\[\\]]", "").split(", ");
            String acc = pairsAcountCustomer[0]; //getting account number
            String pnr = pairsAcountCustomer[1]; // getting personal number
            //if pair in method parameters matches pair in pairsAcountCustomer
            if (pNo.equals(pnr) && acc.equals(String.valueOf(accountId))) {
                //loop through accounts
                for (Account account : accounts) {
                    //if specific account number is the same as the one in  parameter abstract method withdraw will be invoked
                    if (account.getAccountNr() == accountId) {
                        //balanceBefore is balance before transaction
                        //balanceAfter is balance after transaction
                        double balanceBefore = account.getAccountBalance();
                        account.withdraw(pNo, accountId, amount);
                        double balanceAfter = account.getAccountBalance();
                        //if there is a difference in balance before and after, money is withdrawn
                        if (balanceBefore != balanceAfter) {
                            //sometimes amount that is withdrawn is higher than amount user wanted(taxes);
                            //when saving details about transactions it is important to have amount that is withdrawn with the tax (withdrawnAmount)
                            withdrawMoney = true; // money is withdrawn

                            break;
                        }
                        break;
                    }
                }
            }
        }
        return withdrawMoney;
    }


    //method to close an account
    //Iterator removes elements from accountCustomer while iterating over it
    //Then loop to iterate over "accounts" to find the account with the specified accountId
    //that helps to avoid modifying accountCustomer which causes the ConcurrentModificationException error.
    public String closeAccount(String pNo, int accountId) {
        double interest;
        String closedAccount = null; //initialy is null, no account is closed
        Iterator<String> iterator = accountCustomer.iterator(); //Iterator removes elements from accountCustomer while iterating over it
        //loop through accountCustomer using iterator
        while (iterator.hasNext()) {
            //while there is next(another) element, accCust gets assigned its value
            String accCust = iterator.next();
            //array pairsAcountCustomer gets copy of array list accountCustomer without symbols "[ ] and ," next line
            String[] pairsAcountCustomer = accCust.replaceAll("[\\[\\]]", "").split(", ");
            String acc = pairsAcountCustomer[0]; //getting account number
            String pnr = pairsAcountCustomer[1]; // getting personal number
            //if personal number in accountCustomer is equal as the one in parameter
            if (pNo.equals(pnr) && acc.equals(String.valueOf(accountId))) {
                //stop iterate
                iterator.remove();
                //loop through accounts
                for (Account account : accounts) {
                    //if account number in accounts is the same as parameter value
                    if (account.getAccountNr() == accountId) {
                        //getting balance into specific format(swedish kr)
                        String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(account.getAccountBalance());
                        interest = account.getAccountBalance() * account.setInterestrateForAccount() / 100; //calling method in Account
                        String interestStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(interest);
                        //saving data abouth closed account in closedAccount
                        closedAccount = String.valueOf(account.getAccountNr() + " " + balanceStr + " " + account.getAccountType() + " " + interestStr);
                        //removing account
                        accounts.remove(account);
                        break;
                    }
                }
            }
        }
        return closedAccount;
    }


    //method to delete customer; all account connected to specific customer will
    public ArrayList<String> deleteCustomer(String pNo) {
        ArrayList<String> customersInfo = new ArrayList<>(); //arraylist to save details about deleted customer
        BankLogic test = new BankLogic(); // creating new class
        //on previously created class invoking method getCustomerWhenClosingAccount and saving result at customersInfo
        customersInfo = test.getCustomerWhenClosingAccount(pNo);
        //Iterator removes elements from accountCustomer while iterating over it
        Iterator<String> iterator = accountCustomer.iterator();
        while (iterator.hasNext()) {
            String accCust = iterator.next();
            String[] pairsAcountCustomer = accCust.replaceAll("[\\[\\]]", "").split(", ");
            String pnr = pairsAcountCustomer[1];
            if (pNo.equals(pnr)) {
                //removes pair customer- account from accountCustomer
                iterator.remove();
            }
        }
        //loop through customers using iterator
        Iterator<Customer> customerIterator = customers.iterator();
        //while there is next(another) customerIterator element, customer gets that value
        while (customerIterator.hasNext()) {
            Customer customer = customerIterator.next();
            //if customers personal number is the same as parameters personal number
            if (customer.getCustomerPnr().equals(pNo)) {
                //removes customer
                customerIterator.remove();
            }
        }
        return customersInfo;
    }


    //method to get transaction for a specific customer and specific account
    public ArrayList<String> getTransactions(String pNo, int accountId) {
        ArrayList<String> customerTransactions = new ArrayList<String>();//arraylist to be returned
        String strTransactions = ""; //string where customers transactions will be saved
        String trimmedTransaction = ""; //string where brackets will be removed "[]"
        //looping through accounts object arraylist
        //here it will be called method showTransactions from Account class
        for (Account account : accounts) {
            //to add each transaction only once
            if (!strTransactions.contains(account.showTransactions(pNo, accountId))) {
                //adding matching transactions
                strTransactions = strTransactions + account.showTransactions(pNo, accountId);
                //removing brackets "[]"
                trimmedTransaction = strTransactions.substring(1, strTransactions.length() - 1);
            }
        }
        //invoking method getAccount(); it checks if customer has an account
        //if not, this method will return null
        if (trimmedTransaction.isEmpty()) {
            if (getAccount(pNo, accountId) == null) {
                return null;
            }
        }
        //customer has an account
        customerTransactions.add(trimmedTransaction);
        return customerTransactions;
    }


    //method to get all accounts(getter)
    public ArrayList<String> getAllAccounts() {
        ArrayList<String> allAccounts = new ArrayList<String>();//temporary arraylist
        //for each accounts in arraylist Accounts, data will be aded to allAccounts
        for (Account acc : accounts) {
            allAccounts.add(acc.getAccountNr() + " " + acc.getAccountType() + " " + acc.getAccountBalance() + " " + acc.getInterestRate());
        }
        return allAccounts;
    }

    //getter for arraylist accountCustomer
    public ArrayList<String> getAllAccountCustomersPairs() {
        return accountCustomer;
    }

    //getter for arraylist transactions
    public ArrayList<String> getAllTransactions() {
        ArrayList<String> transactions = Account.getTransactions();
        return transactions;
    }

    //metod to import data from a file
    //after a data is imported in FileHandler class, this method will be in charge to store that data in BankLogic
    public void importDataIntoClasses(BankData bankData) {
        BankLogic newBalnkLogic = new BankLogic();

        accounts.clear();
        customers.clear();
        accountCustomer.clear();

        //personal number will be used when some methods are called
        String personalNumber = null;

        //importing customers
        //getting arraylist with imported customers from bankData
        for (String customerData : bankData.getCustomers()) {
            //splitting arraylist to get elements
            String[] customerAttributes = customerData.split("\\s+");
            //checks if the customerData is valid and contains all required attributes
            if (customerAttributes.length == 3) {
                String firstName = customerAttributes[1];
                String lastName = customerAttributes[2];
                personalNumber = customerAttributes[0];

                //creating a new customer using that data
                Customer newCustomer = new Customer(firstName, lastName, personalNumber);
            } else {
                //handle invalid customerData if necessary
                System.err.println("Invalid customer data: " + customerData);
            }
        }
        //importing accounts
        //getting arraylist with imported accounts from bankData
        //when an account is created in Account it is automaticly created in BankLogic
        for (String accountsData : bankData.getAccounts()) {
            //splitting arraylist to get elements
            String[] accountAttributes = accountsData.split("\\s+");
            int accountNr = Integer.parseInt(accountAttributes[0]);
            String accountType = accountAttributes[1];
            double balance = Double.parseDouble(accountAttributes[2]);
            int balanceCredAcc = (int) balance;
            double interestRate = Double.parseDouble(accountAttributes[3]);

            //if account type is savings account
            if (accountType.equals("Sparkonto")) {
                //creating new savings account
                Account newAccount = new SavingsAccount(0, 0);
                //checking the diffrence between account number in created account and the one in imported file
                int numberOfAccountsToCreate = accountNr - newAccount.getAccountNr();
                //priogram will create as many accoun as necessary untill account number is the same
                //other account will not be asssigned to a customer and not in use
                for (int i = 0; i < numberOfAccountsToCreate; i++) {
                    Account newAccount55 = new SavingsAccount(balance, interestRate);
                }
            }
            //if account type is credit account
            //here it's used same logic as when creating savings account
            else if (accountType.equals("Kreditkonto")) {
                Account newAccount = new CreditAccount();
                newAccount.setNewBalance(balanceCredAcc);
                int numberOfAccountsToCreate = accountNr - newAccount.getAccountNr();
                for (int i = 0; i < numberOfAccountsToCreate; i++) {
                    Account newAccount55 = new CreditAccount();
                    newAccount.setNewBalance(balanceCredAcc);
                }
            }
            else {
                // Handle invalid customerData if necessary
                System.err.println("Invalid account data: " + accountsData);
            }
        }

        //importing accounts-customer pairs
        //getting arraylist with imported account-customer pairs from bankData
        for (String pairs : bankData.getPairs()) {
            //splitting arraylist to get elemets
            String[] accountCustomerPairsAttributes = pairs.split("\\s+");
            //checks if the customerData is ok
            if (accountCustomerPairsAttributes.length == 2) {
                //getting elements
                String accountNr = accountCustomerPairsAttributes[0];
                String custPnr = accountCustomerPairsAttributes[1];
                accountNr = accountNr.substring(1, accountNr.length() - 1);
                custPnr = custPnr.substring(0, custPnr.length() - 1);
                //assigning customer to an account
                BankLogic.assignAcountToCustomer(custPnr, Integer.valueOf(accountNr));
            } else {
                //handle errors
                System.err.println("Invalid account data: " + pairs);
            }
        }

        //import transactions from imported file
        for (String allTransactions : bankData.getTransactions()) {
            //splliting arraylist and saving it as a array
            String[] allTransactionsArray = allTransactions.split("\\s+");
            //if there are 7 elemts assigning elements
            if (allTransactionsArray.length == 7) {
                String pnr = allTransactionsArray[0];
                int accNr = Integer.parseInt(allTransactionsArray[1]);
                String date = allTransactionsArray[2];
                String time = allTransactionsArray[3];
                String amount = allTransactionsArray[4];
                String saldoText = allTransactionsArray[5];
                String saldo = allTransactionsArray[6];
                String dateTime =date + " " + time;
                //saving transactionts into transactions arraylist
                Account.saveTransactionFromAFile(pnr, accNr, dateTime,  amount, saldo);
            } else {
                //erore
                System.err.println("Invalid account data: " + allTransactionsArray);
            }
        }
    }





























}
