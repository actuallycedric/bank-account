import java.util.*;
import java.time.*;

public class BankAccount {

    enum transactionType{
        DEPOSIT,
        WITHDRAW,
        TRANSFER
    }

    public record Transaction(transactionType type, double amount, String timestamp, String description){}

    private final String accountNumber;
    private final String ownerName;
    private double balance;
    private List<Transaction> transactionHistory;

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public BankAccount(String accountNumber, String ownerName, double initial){
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        balance = initial;
        transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public void deposit(double amount) throws InvalidAmountException{

        if(amount <= 0) throw new InvalidAmountException("You can't deposit an amount less than or equal to zero!");

        String time = LocalDateTime.now().toString();

        System.out.print(ownerName + ", your balance was previously $" + balance);
        balance += amount;
        System.out.print(" and is now $" + balance + "\n");


        Transaction t = new Transaction(transactionType.DEPOSIT, amount, time, "Deposited $" + amount + " at " + time);
        transactionHistory.add(t);
    }

    public void withdraw(double amount) throws InvalidAmountException{
        if(amount <= 0 || balance < amount) throw new InvalidAmountException("You can't withdraw that amount!");

        String time = LocalDateTime.now().toString();

        System.out.print(ownerName + ", your balance was previously $" + balance);
        balance -= amount;
        System.out.print(" and is now $" + balance);


        Transaction t = new Transaction(transactionType.WITHDRAW, amount, time, "Withdrew $" + amount + " at " + time);
        transactionHistory.add(t);
    }

    public void transfer(BankAccount b, double amount) throws InvalidAmountException{
        String time = LocalDateTime.now().toString();


        b.setBalance(b.getBalance() - amount);
        balance -= amount;

        String message = "Transferred $" + amount + " at " + time + " from " + accountNumber + " to " + b.getAccountNumber();

        System.out.println(message);

        Transaction t = new Transaction(transactionType.TRANSFER, amount, time, message);
        transactionHistory.add(t);
    }

    public void getStatement(){
        for(Transaction t : transactionHistory){
            System.out.println(t.description());
        }
    }

}
