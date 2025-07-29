import java.sql.*;
import java.util.*;
import java.lang.reflect.Field;

public class BankingSystem {
    private static Scanner sc = new Scanner(System.in);
    private static Map<Integer, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        loadAccountsFromDB();
        int choice;
        do {
            System.out.println("\n--- Banking System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Apply for Loan");
            System.out.println("6. Repay Loan");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> checkBalance();
                case 5 -> applyLoan();
                case 6 -> repayLoan();
                case 7 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice");
            }
        } while (choice != 7);
    }

    private static void createAccount() {
        System.out.print("Enter name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter initial deposit: ");
        double deposit = sc.nextDouble();
        Account acc = new Account(name, deposit);
        accounts.put(acc.getAccountNumber(), acc);
        System.out.println("Account created! Account Number: " + acc.getAccountNumber());

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO accounts VALUES (?, ?, ?, ?)");) {
            stmt.setInt(1, acc.getAccountNumber());
            stmt.setString(2, acc.getHolderName());
            stmt.setDouble(3, acc.getBalance());
            stmt.setDouble(4, acc.getLoanAmount());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error inserting into DB: " + e.getMessage());
        }
    }

    private static void deposit() {
        Account acc = getAccountById();
        if (acc != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = sc.nextDouble();
            acc.deposit(amount);
            updateBalance(acc);
        }
    }

    private static void withdraw() {
        Account acc = getAccountById();
        if (acc != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            acc.withdraw(amount);
            updateBalance(acc);
        }
    }

    private static void checkBalance() {
        Account acc = getAccountById();
        if (acc != null) {
            System.out.println("Balance: " + acc.getBalance());
            System.out.println("Loan Amount: " + acc.getLoanAmount());
        }
    }

    private static void applyLoan() {
        Account acc = getAccountById();
        if (acc != null) {
            System.out.print("Enter loan amount: ");
            double amount = sc.nextDouble();
            acc.applyLoan(amount);
            updateLoan(acc);
        }
    }

    private static void repayLoan() {
        Account acc = getAccountById();
        if (acc != null) {
            System.out.print("Enter amount to repay: ");
            double amount = sc.nextDouble();
            acc.repayLoan(amount);
            updateBalance(acc);
            updateLoan(acc);
        }
    }

    private static Account getAccountById() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        return accounts.getOrDefault(accNo, null);
    }

    private static void updateBalance(Account acc) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE accounts SET balance = ? WHERE account_number = ?")) {
            stmt.setDouble(1, acc.getBalance());
            stmt.setInt(2, acc.getAccountNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Failed to update balance: " + e.getMessage());
        }
    }

    private static void updateLoan(Account acc) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE accounts SET loan_amount = ? WHERE account_number = ?")) {
            stmt.setDouble(1, acc.getLoanAmount());
            stmt.setInt(2, acc.getAccountNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Failed to update loan: " + e.getMessage());
        }
    }

    private static void loadAccountsFromDB() {
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM accounts")) {

            while (rs.next()) {
                Account acc = new Account(
                        rs.getString("holder_name"),
                        rs.getDouble("balance")
                );

                Field field = Account.class.getDeclaredField("accountNumber");
                field.setAccessible(true);
                field.set(acc, rs.getInt("account_number"));

                Field loanField = Account.class.getDeclaredField("loanAmount");
                loanField.setAccessible(true);
                loanField.set(acc, rs.getDouble("loan_amount"));

                accounts.put(acc.getAccountNumber(), acc);
            }
        } catch (Exception e) {
            System.out.println("❌ Error loading from DB: " + e.getMessage());
        }
    }
}
