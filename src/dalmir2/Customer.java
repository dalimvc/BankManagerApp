package dalmir2;

//student:Dalibor Mirkovic
//ltu-id: dalmir2

import java.util.ArrayList;

public class Customer {

    //class attributes
    private String firstName;
    private String lastName;
    private String personalNumber;

    //constructor for class
    public Customer(String firstName, String lastName, String personalNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        //when an instance on class is made, it will be saved as Customer arraylist in BankLogic
        BankLogic.addCustomer(this);
    }

    //getter for accountNr
    public String getCustomerPnr(){
        return this.personalNumber;
    }

    //getter for customers surname
    public String getCustomerSurname(){
        return this.firstName;
    }

    //getter for customers lastname
    public  String getCustomerLastname(){
        return this.lastName;
    }

    //setter for surname
    public void setCustomerFirstName(String name) {
        this.firstName = name;
    }

    //setter for last name
    public void setCustomerLastName(String name) {
        this.lastName = name;
    }

}
