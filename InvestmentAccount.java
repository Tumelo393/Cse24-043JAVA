package model;

public class InvestmentAccount extends Account implements InterestPayable {
    private static final double MONTHLY_RATE = 0.05; // 5% monthly

    public InvestmentAccount(String accountNumber, double initialDeposit) {
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
