package library;

import java.util.List;
import java.util.Scanner;

import exceptions.Exceptions;

public class Tester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountUtils accountUtils = new AccountUtils();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Account");
            System.out.println("2. Display an Account");
            System.out.println("3. Display All Accounts");
            System.out.println("4. Remove an Account");
            System.out.println("5. Withdraw");
            System.out.println("6. Deposit");
            System.out.println("7. Transfer");
            System.out.println("8. Search Account by Name");
            System.out.println("9. Search Account by Email");
            System.out.println("10. Search Account by Phone");
            System.out.println("11. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.println("Enter name:");
                        String name = scanner.nextLine();
                        System.out.println("Enter email:");
                        String email = scanner.nextLine();
                        System.out.println("Enter phone:");
                        String phone = scanner.nextLine();
                        System.out.println("Enter initial balance:");
                        double balance = scanner.nextDouble();
                        scanner.nextLine();  // Consume newline
                        
                        Account newAccount = new Account(name, email, phone, balance);
                        accountUtils.addAccount(newAccount);
                        break;
                    
                    case 2:
                        System.out.println("Enter account number:");
                        String accNumber = scanner.nextLine();
                        Account account = accountUtils.displayAccount(accNumber);
                        System.out.println(account);
                        break;

                    case 3:
                        List<Account> accounts = accountUtils.displayAllAccounts();
                        if (accounts.isEmpty()) {
                            System.out.println("No accounts found.");
                        } else {
                            for (Account acc : accounts) {
                                System.out.println(acc);
                                System.out.println("---------------");
                            }
                        }
                        break;

                    case 4:
                        System.out.println("Enter account number to remove:");
                        String accNumToRemove = scanner.nextLine();
                        accountUtils.removeAccount(accNumToRemove);
                        break;

                    case 5:
                        System.out.println("Enter account number:");
                        String accNumWithdraw = scanner.nextLine();
                        System.out.println("Enter amount to withdraw:");
                        double amountWithdraw = scanner.nextDouble();
                        scanner.nextLine();  // Consume newline
                        accountUtils.withdraw(accNumWithdraw, amountWithdraw);
                        break;

                    case 6:
                        System.out.println("Enter account number:");
                        String accNumDeposit = scanner.nextLine();
                        System.out.println("Enter amount to deposit:");
                        double amountDeposit = scanner.nextDouble();
                        scanner.nextLine();  // Consume newline
                        accountUtils.deposit(accNumDeposit, amountDeposit);
                        break;

                    case 7:
                        System.out.println("Enter from account number:");
                        String fromAccount = scanner.nextLine();
                        System.out.println("Enter to account number:");
                        String toAccount = scanner.nextLine();
                        System.out.println("Enter amount to transfer:");
                        double amountTransfer = scanner.nextDouble();
                        scanner.nextLine();  // Consume newline
                        accountUtils.transfer(fromAccount, toAccount, amountTransfer);
                        break;

                    case 8:
                        System.out.println("Enter name to search:");
                        String searchName = scanner.nextLine();
                        List<Account> nameResults = accountUtils.searchAccountByName(searchName);
                        if (nameResults.isEmpty()) {
                            System.out.println("No accounts found with the name: " + searchName);
                        } else {
                            for (Account acc : nameResults) {
                                System.out.println(acc);
                                System.out.println("---------------");
                            }
                        }
                        break;

                    case 9:
                        System.out.println("Enter email to search:");
                        String searchEmail = scanner.nextLine();
                        List<Account> emailResults = accountUtils.searchAccountByEmail(searchEmail);
                        if (emailResults.isEmpty()) {
                            System.out.println("No accounts found with the email: " + searchEmail);
                        } else {
                            for (Account acc : emailResults) {
                                System.out.println(acc);
                                System.out.println("---------------");
                            }
                        }
                        break;

                    case 10:
                        System.out.println("Enter phone to search:");
                        String searchPhone = scanner.nextLine();
                        List<Account> phoneResults = accountUtils.searchAccountByPhone(searchPhone);
                        if (phoneResults.isEmpty()) {
                            System.out.println("No accounts found with the phone: " + searchPhone);
                        } else {
                            for (Account acc : phoneResults) {
                                System.out.println(acc);
                                System.out.println("---------------");
                            }
                        }
                        break;

                    case 11:
                        System.out.println("Exiting... Thank you for using EBank!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
                
                System.out.println("==================================================================================================");
            } catch (Exceptions.InvalidAccountException | Exceptions.AccountNotFoundException | Exceptions.InsufficientBalanceException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}