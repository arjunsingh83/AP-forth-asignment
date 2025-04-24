// Base class
class Account {
    protected String accountNumber;
    protected String accountHolder;
    protected double balance;

    public Account(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void displayBalance() {
        System.out.println("Account Balance: $" + balance);
    }
}

// Derived class for SavingsAccount
class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.04; // 4% annual interest
    private static final double MIN_BALANCE = 500.0;

    public SavingsAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    public void calculateInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.println("Interest added: $" + interest);
    }

    public void withdraw(double amount) {
        if (amount > 0 && (balance - amount) >= MIN_BALANCE) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Withdrawal denied. Maintain minimum balance of $" + MIN_BALANCE);
        }
    }
}

// Derived class for CurrentAccount
class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 1000.0;

    public CurrentAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    public void withdraw(double amount) {
        if (amount > 0 && (balance - amount) >= -OVERDRAFT_LIMIT) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Withdrawal denied. Overdraft limit exceeded.");
        }
    }
}

// Main class to test the functionality
public class BankDemo {
    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount("SA123", "Alice", 1000.0);
        CurrentAccount current = new CurrentAccount("CA123", "Bob", 500.0);

        System.out.println("\n-- Savings Account --");
        savings.deposit(200);
        savings.withdraw(300);
        savings.calculateInterest();
        savings.displayBalance();

        System.out.println("\n-- Current Account --");
        current.deposit(500);
        current.withdraw(900);
        current.withdraw(200); // Overdraft should apply
        current.displayBalance();
    }
}
