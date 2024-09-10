import java.util.Scanner;

// BankAccount class to manage account operations
class BankAccount {
    private double balance;

    // Constructor to initialize the balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Deposit amount should be greater than zero.");
        }
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawal successful. New balance: " + balance);
            } else {
                System.out.println("Insufficient funds. Withdrawal failed.");
            }
        } else {
            System.out.println("Withdrawal amount should be greater than zero.");
        }
    }

    // Method to check the current balance
    public double checkBalance() {
        return balance;
    }
}

// ATM class to provide user interface and operations
public class ATM {
    private BankAccount account;
    private Scanner scanner;

    // Constructor to initialize ATM with a bank account
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Method to display menu and handle user options
    public void displayMenu() {
        int choice = 0;
        while (choice != 4) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        performDeposit();
                        break;
                    case 2:
                        performWithdrawal();
                        break;
                    case 3:
                        checkBalance();
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
            }
        }
    }

    // Method to perform deposit operation
    private void performDeposit() {
        System.out.print("Enter amount to deposit: ");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Invalid input. Amount should be a number.");
            scanner.next(); // consume the invalid input
        }
    }

    // Method to perform withdrawal operation
    private void performWithdrawal() {
        System.out.print("Enter amount to withdraw: ");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Invalid input. Amount should be a number.");
            scanner.next(); // consume the invalid input
        }
    }

    // Method to check account balance
    private void checkBalance() {
        double balance = account.checkBalance();
        System.out.println("Current balance: " + balance);
    }

    // Main method to run the ATM application
    public static void main(String[] args) {
        // Create a bank account with initial balance of $1000
        BankAccount bankAccount = new BankAccount(1000.0);

        // Create an ATM instance with the bank account
        ATM atm = new ATM(bankAccount);

        // Display the ATM menu and start interaction
        atm.displayMenu();
    }
}
