package dalmir2;

//student:Dalibor Mirkovic
//ltu-id: dalmir2


import java.util.Calendar;
import java.util.ArrayList;

//subclass of Account class
public class SavingsAccount extends Account {

    //class attribute transactionsCounter; array will have 2 elements, one will store year of the last withdraw adn second number of withdraws that year
    private int[] transactionsCounter =new int[2];

    //constructor for class SavingsAccount
    //it will inherit all class attributes from parent class Account (via super key word)
    //account type will be by default Sparkonto
    //transactionsCounter would be assigned 2 values [current_year, 0]; 0 means there were 0 transactions made by that account
    public SavingsAccount(double balance, double interestRate) {
        super(balance, interestRate, "Sparkonto");
        transactionsCounter[0] = Calendar.getInstance().get(Calendar.YEAR);
        transactionsCounter[1] = 0;
    }

    //abstract method for withdraw money; it has some special features that differs from withdraw for other account types
    //this method will later be implemented inside another method in BankLogic(other method will have more functionality)
    public void withdraw(String pNo, int accountId, int amount) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR); //assigning current year
        //if amount on the account is higher than 0 and higher than the amount user wants to withdraw...
        if(amount <= getAccountBalance() && amount > 0){
            //situation 1: current year is higher than the year when customer did last withdraw
            if(currentYear > transactionsCounter[0]){
                double newBalance = getAccountBalance() - amount; //doing actual withdraw calculation
                setNewBalance((int) newBalance); //adjusting balance
                saveTransaction(pNo, accountId, -amount); //saving transaction
                transactionsCounter[0] = currentYear; //current year gets updated
                transactionsCounter[1] = 1; //number of transaction per current year in now 1(more than 0 per year)
                //Situation 2: current year and the year customer did last withdraw is the same
            } else if(currentYear == transactionsCounter[0]) {
                //case first withdraw of the year
                if(transactionsCounter[1] == 0){
                    double newBalance = getAccountBalance() - amount; //doing actual withdraw calculation
                    setNewBalance((int) newBalance); //adjusting balance
                    saveTransaction(pNo, accountId, -amount); //saving transaction
                    transactionsCounter[1] = 1; //number of transaction per current year in now 1(more than 0 per year)
                    //case, not first transaction of the year
                } else if(transactionsCounter[1] == 1){
                    double newBalance = getAccountBalance() - amount - amount * 0.02; //doing actual withdraw calculation
                    if (newBalance >= 0) { //withdraw will be done only if total amount that should be withdrawn( with tax) is higher then balance
                        setNewBalance((int) newBalance); //adjusting balance
                        int withdrawnAmount = (int) (amount + amount * 0.02);
                        saveTransaction(pNo, accountId, -withdrawnAmount); //saving transaction
                    }
                }
            }
        }
    }


    //method for setting interest rate; for savings account, at the moment, that is default value
    public double setInterestrateForAccount() {
        setInterestRate(getInterestRate());
        return getInterestRate();
    }

}
