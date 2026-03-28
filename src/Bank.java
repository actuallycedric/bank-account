import java.util.HashMap;
import java.util.Map;

public class Bank {

    public Map<String, BankAccount> accounts = new HashMap<>();

    public void createAccount(String accNumber, String ownerName, double initial) throws InvalidAmountException {

        if(isValidAccount(accNumber)) throw new AccountNotFoundException("There's already an account associated with this number!");
        else if(initial < 0) throw new InvalidAmountException("You can't contribute an initial amount less than zero!");

        BankAccount b = new BankAccount(accNumber, ownerName, initial);

        accounts.put(accNumber, b);
        System.out.println("Your account has been created! The initial deposit is $" + initial + " and your account number is " + accNumber);
    }


    public void listAllAccounts(){
        String accNumbers = "";

        for(BankAccount b : accounts.values()){
            accNumbers += b.getAccountNumber();
            accNumbers += " ";
        }

        System.out.println(accNumbers);
    }

    public void listAllAccounts(String omit){
        String accNumbers = "";

        for(BankAccount b : accounts.values()){
            if(!b.getAccountNumber().equals(omit)){
                accNumbers += b.getAccountNumber();
            }
            accNumbers += " ";
        }

        System.out.println(accNumbers);
    }

    public void listAllStatements(){
        for(BankAccount b : accounts.values()){

            System.out.println("For account #" + b.getAccountNumber());
            System.out.println("Current balance: $" + b.getBalance());
            b.getStatement();
        }
    }

    public boolean isValidAccount(String accNumber){
        return accounts.containsKey(accNumber);
    }

    public void closeAccount(String accNumber) throws AccountNotFoundException{

        if(!isValidAccount(accNumber)) throw new AccountNotFoundException("Invalid account!");
        if(getAccount(accNumber).getBalance() != 0) throw new IllegalOperationException("Invalid operation!");
        System.out.println("Sorry to see you go!");

        accounts.remove(accNumber);
    }

    public BankAccount getAccount(String accNumber) throws AccountNotFoundException{

        if(!accounts.containsKey(accNumber)) throw new AccountNotFoundException("Invalid account!");

        return accounts.get(accNumber);
    }



}
