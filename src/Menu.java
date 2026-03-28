import java.util.*;

public class Menu {

    public void start() throws InvalidAmountException {
        Scanner input = new Scanner(System.in);
        Bank bank = new Bank();

        System.out.print("Welcome to JavaBank! What's your name? ");
        String name = input.nextLine();

        while(true){
            try {
                prompt();
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("What's your initial deposit for the account? ");
                        String initialDeposit = input.nextLine();

                        String accNumber = String.valueOf((int) (Math.random() * 5000));

                        while (bank.isValidAccount(accNumber)) {
                            accNumber = String.valueOf((int) (Math.random() * 5000));
                        }

                        bank.createAccount(accNumber, name, Double.parseDouble(initialDeposit));
                        break;
                    case 2:
                        System.out.println("Choose an account to deposit to: ");
                        bank.listAllAccounts();

                        String accountToDeposit = input.nextLine();

                        System.out.println("How much would you like to deposit? ");

                        String depositAmount = input.nextLine();

                        bank.getAccount(accountToDeposit).deposit(Double.parseDouble(depositAmount));
                        break;
                    case 3:
                        System.out.println("Choose an account to withdraw: ");
                        bank.listAllAccounts();

                        String accountToWithdraw = input.nextLine();

                        System.out.println("How much would you like to withdraw? ");

                        String withdrawAmount = input.nextLine();

                        bank.getAccount(accountToWithdraw).withdraw(Double.parseDouble(withdrawAmount));
                        break;
                    case 4:
                        System.out.println("What account would you like to transfer from? ");
                        bank.listAllAccounts();

                        String withdrawNum = input.nextLine();

                        if(!bank.isValidAccount(withdrawNum)) throw new AccountNotFoundException("Invalid!");
                        BankAccount sender = bank.getAccount(withdrawNum);

                        System.out.println("What account would you like to receive the money into? ");
                        bank.listAllAccounts(withdrawNum);

                        String depositNum = input.nextLine();

                        if(withdrawNum.equals(depositNum) || !bank.isValidAccount(depositNum)) throw new AccountNotFoundException("Invalid!");
                        BankAccount receiver = bank.getAccount(depositNum);



                        System.out.println("How much would you like to transfer? ");
                        String transferAmount = input.nextLine();

                        sender.transfer(receiver, Double.parseDouble(transferAmount));

                        break;
                    case 5:
                        System.out.println("For which account would you like to view the statement? ");

                        bank.listAllAccounts();

                        String statementNum = input.nextLine();

                        bank.getAccount(statementNum).getStatement();
                        break;
                    case 6:
                        System.out.println("Which account would you like to close? ");
                        bank.listAllAccounts();
                        String accountToClose = input.nextLine();

                        bank.closeAccount(accountToClose);
                        break;
                    case 7:
                        bank.listAllStatements();
                        break;
                    case 8:
                        System.exit(0);

                }

            }
            catch(InputMismatchException e){
                System.out.println("Invalid input! Type a number between 1 and 8");
                input.nextLine();
            }
            catch(InvalidAmountException e){
                System.out.println("Invalid amount! The operation has been cancelled!");
            }
            catch(AccountNotFoundException e){
                System.out.println("The account you're trying to access is invalid! The operation has been cancelled!");
            }
            catch(IllegalOperationException e){
                System.out.println("You can't do that!");
            }
        }
    }

    public void prompt(){
        System.out.println("\n++++++++++++++++++++");
        System.out.println("Welcome to JavaBank!");

        System.out.println("1 - Create Account");
        System.out.println("2 - Deposit");
        System.out.println("3 - Withdraw");
        System.out.println("4 - Transfer");
        System.out.println("5 - View Statement");
        System.out.println("6 - Close Account");
        System.out.println("7 - View All Accounts");
        System.out.println("8 - Exit");
        System.out.println("Choose your prompt: ");
    }
}
