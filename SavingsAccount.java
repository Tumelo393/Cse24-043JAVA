package model;

public class SavingsAccount extends Account implements InterestPayable {
    private static final double MONTHLY_RATE = 0.0005; // 0.05% monthly

    public SavingsAccount(String accountNumber, double initialDeposit) {
        super(accountNumber, initialDeposit);
    }

    @Override
    public void payInterest() {
        double interest = balance * MONTHLY_RATE;
        if(interest > 0){
            balance += interest;
            transactions.add(new Transaction(java.util.UUID.randomUUID().toString(), interest, "INTEREST"));
        }
    }
}
