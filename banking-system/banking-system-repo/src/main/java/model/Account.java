package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected String branch;
    protected List<Transaction> transactions = new ArrayList<>();

    public Account(String accountNumber, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
    }

    public String getAccountNumber(){ return accountNumber; }
    public double getBalance(){ return balance; }
    public List<Transaction> getTransactions(){ return transactions; }

    public void deposit(double amount){
        if(amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        balance += amount;
        transactions.add(new Transaction(java.util.UUID.randomUUID().toString(), amount, "DEPOSIT"));
    }

    public void withdraw(double amount){
        if(amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        if(amount > balance) throw new IllegalArgumentException("Insufficient funds");
        balance -= amount;
        transactions.add(new Transaction(java.util.UUID.randomUUID().toString(), amount, "WITHDRAW"));
    }
}
