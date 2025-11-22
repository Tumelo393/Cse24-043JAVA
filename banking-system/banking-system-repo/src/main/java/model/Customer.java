package model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private boolean isEmployed;
    private String employer;
    private List<Account> accounts = new ArrayList<>();

    public Customer(String id, String firstName, String lastName, boolean isEmployed, String employer) {
        this.id = id; this.firstName = firstName; this.lastName = lastName;
        this.isEmployed = isEmployed; this.employer = employer;
    }

    public void addAccount(Account a){ accounts.add(a); }
    public List<Account> getAccounts(){ return accounts; }
    // getters & setters
    public String getId(){return id;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public boolean isEmployed(){return isEmployed;}
    public String getEmployer(){return employer;}
}
