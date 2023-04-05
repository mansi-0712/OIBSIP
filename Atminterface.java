import java.util.Scanner;

class Account {
    private int id;
    private int pin;
    private double balance;
    
    public Account(int id, int pin, double balance) {
        this.id = id;
        this.pin = pin;
        this.balance = balance;
    }
    
    public boolean verify(int id, int pin) {
        return (this.id == id && this.pin == pin);
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        balance += amount;
    }
    
    public void withdraw(double amount) {
        balance -= amount;
    }
}

class Transaction {
    private int id;
    private double amount;
    private String type;
    
    public Transaction(int id, double amount, String type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
    }
    
    public String toString() {
        return "Transaction " + id + ": " + type + " " + amount;
    }
}

class ATM {
    private Account account;
    private Transaction[] transactions;
    private int nextTransactionId;
    
    public ATM(Account account) {
        this.account = account;
        this.transactions = new Transaction[100];
        this.nextTransactionId = 1;
    }
    
    public void deposit(double amount) {
        account.deposit(amount);
        transactions[nextTransactionId - 1] = new Transaction(nextTransactionId, amount, "Deposit");
        nextTransactionId++;
    }
    
    public void withdraw(double amount) {
        if (amount <= account.getBalance()) {
            account.withdraw(amount);
            transactions[nextTransactionId - 1] = new Transaction(nextTransactionId, amount, "Withdrawal");
            nextTransactionId++;
        } else {
            System.out.println("Insufficient balance");
        }
    }
    
    public void showTransactionHistory() {
        System.out.println("Transaction history:");
        for (int i = 0; i < nextTransactionId - 1; i++) {
            System.out.println(transactions[i]);
        }
    }
}

public class Atminterface {
    public static void main(String[] args) {
        Account account = new Account(1234, 5678, 1000.0);
        ATM atm = new ATM(account);
        Scanner scanner = new Scanner(System.in);
        int id, pin;
        
        System.out.println("Welcome to the ATM");
        System.out.print("Enter user ID: ");
        id = scanner.nextInt();
        System.out.print("Enter PIN: ");
        pin = scanner.nextInt();
        
        if (account.verify(id, pin)) {
            System.out.println("Login successful");
            int choice;
            do {
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Show transaction history");
                System.out.println("4. Quit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double amount = scanner.nextDouble();
                        atm.deposit(amount);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        amount = scanner.nextDouble();
                        atm.withdraw(amount);
                        break;
                    case 3:
                        atm.showTransactionHistory();
                        break;
                    case 4:
                        System.out.println("Goodbye");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } while (choice != 4);
        } else {
            System.out.println("Invalid user ID or PIN");
               
                }
       }
}

