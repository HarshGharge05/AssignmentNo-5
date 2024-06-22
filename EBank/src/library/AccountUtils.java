package library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.Exceptions;
import utils.Validations;

public class AccountUtils implements Operations {
    private List<Account> accounts;
    private static final String FILE_PATH = "NEW_Balance.txt";

    public AccountUtils() {
        accounts = new ArrayList<>();
        loadAccounts();
    }

//   persisting data in file
    private void loadAccounts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                accounts.add(Account.fromTextFormat(line));
            }
        } catch (IOException e) {
            // File might not exist initially
        }
    }

    private void saveAccounts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Account account : accounts) {
                bw.write(account.toTextFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//   methods::::
    
    @Override
    public void addAccount(Account account) throws Exceptions.InvalidAccountException {
        Validations.validateAccount(account);
        if (accounts.stream().anyMatch(acc -> acc.getAccountNumber().equals(account.getAccountNumber()))) {
            throw new Exceptions.InvalidAccountException("Account already exists.");
        }
        accounts.add(account);
        saveAccounts();
        System.out.println("Account added successfully. Account Number: " + account.getAccountNumber());
    }

    @Override
    public Account displayAccount(String accountNumber) throws Exceptions.AccountNotFoundException {
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new Exceptions.AccountNotFoundException("Account not found."));
    }

    @Override
    public List<Account> displayAllAccounts() {
        return accounts;
    }

    @Override
    public void removeAccount(String accountNumber) throws Exceptions.AccountNotFoundException {
        Account account = displayAccount(accountNumber);
        accounts.remove(account);
        saveAccounts();
        System.out.println("Account removed successfully.");
    }

    @Override
    public void withdraw(String accountNumber, double amount) throws Exceptions.AccountNotFoundException, Exceptions.InsufficientBalanceException {
        Account account = displayAccount(accountNumber);
        if (account.getBalance() < amount) {
            throw new Exceptions.InsufficientBalanceException("Insufficient balance.");
        }
        account.setBalance(account.getBalance() - amount);
        saveAccounts();
        System.out.println("Withdrawal successful. New balance: " + account.getBalance());
    }

    @Override
    public void deposit(String accountNumber, double amount) throws Exceptions.AccountNotFoundException {
        Account account = displayAccount(accountNumber);
        account.setBalance(account.getBalance() + amount);
        saveAccounts();
        System.out.println("Deposit successful. New balance: " + account.getBalance());
    }

    @Override
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) throws Exceptions.AccountNotFoundException, Exceptions.InsufficientBalanceException {
        withdraw(fromAccountNumber, amount);
        deposit(toAccountNumber, amount);
        System.out.println("Transfer successful.");
    }

    @Override
    public List<Account> searchAccountByName(String name) {
        return accounts.stream()
                .filter(account -> account.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> searchAccountByEmail(String email) {
        return accounts.stream()
                .filter(account -> account.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> searchAccountByPhone(String phone) {
        return accounts.stream()
                .filter(account -> account.getPhone().equals(phone))
                .collect(Collectors.toList());
    }
}