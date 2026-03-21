import java.util.List;
public class BankAccount {

    enum transactionType{
        DEPOSIT,
        WITHDRAW,
        TRANSFER
    }

    public record Transaction(transactionType type, double amount, String timestamp, String description){}

    private String accountNumber;
    private String ownerName;
    private double balance;
    private List<Transaction> transactionHistory;

}
