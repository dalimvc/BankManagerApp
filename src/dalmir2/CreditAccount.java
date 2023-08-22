package dalmir2;

//student:Dalibor Mirkovic
//ltu-id: dalmir2


//subclass of Account class
public class CreditAccount extends Account {

    //class attribute creditLimit
    double creditLimit;

    //constructor for class SavingsAccount
    //it will inherit all class attributes from parent class Account (via super key word)
    //account type will be by default Kreditkonto
    //no attributes will be manualy assigned when new credit account is made; balance will be 0 by default, interest rate 0.5
    //attribute creditLimit that is specific for CreditAccount gets default value 5000
    public CreditAccount() {
        super(0, 0.5, "Kreditkonto");
        this.creditLimit = 5000;
    }

    //abstract method for withdraw money; it has some special features that differs from withdraw from other account types
    public void withdraw(String pNo, int accountId, int amount) {
        //if balance is higher than negative value of the credit limit and amount to be withdrawn is higher than 0
        if(getAccountBalance() >= - creditLimit && amount > 0){
            int testSaldo = (int) (getAccountBalance() - amount); //calculating new saldo
            //if new saldo is higher than negative value of credit limit, transaction will be done and balance adjusted
            if(testSaldo >= - creditLimit){
                setNewBalance(testSaldo);
                saveTransaction(pNo, accountId, -amount); //saving transaction
            }
        }
    }

    //credit account has special feature, if accounts balance is negative, interest rate is 7, otherwise is default
    public double setInterestrateForAccount() {
        if(getAccountBalance() < 0){
            setInterestRate(7);
        } else {
            setInterestRate(getInterestRate());
        }
        return getInterestRate();
    }

}
