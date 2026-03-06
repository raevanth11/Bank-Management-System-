public class Account {
    private static int accCounter = 1001;
    private int accountNumber;
    private String holderName;
    private double balance;
    private double loanAmount;

    public Account(String holderName, double initialDeposit) {
        this.accountNumber = accCounter++;
        this.holderName = holderName;
        this.balance = initialDeposit;
        this.loanAmount = 0.0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void applyLoan(double amount) {
        loanAmount += amount;
        balance += amount;
    }

    public void repayLoan(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance to repay loan.");
        } else if (amount > loanAmount) {
            System.out.println("Repaying more than loan. Adjusting to loan amount.");
            balance -= loanAmount;
            loanAmount = 0;
        } else {
            balance -= amount;
            loanAmount -= amount;
        }
    }
}
