package model;

public class ChequeAccount extends Account {
    private String employer;

    public ChequeAccount(String accountNumber, double initialDeposit, String employer) {
        super(accountNumber, initialDeposit);
        this.employer = employer;
    }

    public String getEmployer() { return employer; }
}
