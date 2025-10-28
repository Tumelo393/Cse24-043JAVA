package controller;

import model.Account;
import dao.AccountDAO;

public class AccountController {
    public void deposit(Account acc, double amount) {
        acc.deposit(amount);
        // persist only the latest transaction
        try {
            if(!acc.getTransactions().isEmpty()){
                AccountDAO.saveTransaction(acc.getAccountNumber(), acc.getTransactions().get(acc.getTransactions().size()-1));
            }
        } catch(Exception ex){ ex.printStackTrace(); }
    }

    public void withdraw(Account acc, double amount) {
        acc.withdraw(amount);
        try {
            if(!acc.getTransactions().isEmpty()){
                AccountDAO.saveTransaction(acc.getAccountNumber(), acc.getTransactions().get(acc.getTransactions().size()-1));
            }
        } catch(Exception ex){ ex.printStackTrace(); }
    }
}
