package library;

import java.util.List;

import exceptions.Exceptions;

public interface Operations {
    void addAccount(Account account) throws Exceptions.InvalidAccountException;
    Account displayAccount(String accountNumber) throws Exceptions.AccountNotFoundException;
    List<Account> displayAllAccounts();
    void removeAccount(String accountNumber) throws Exceptions.AccountNotFoundException;
    void withdraw(String accountNumber, double amount) throws Exceptions.AccountNotFoundException, Exceptions.InsufficientBalanceException;
    void deposit(String accountNumber, double amount) throws Exceptions.AccountNotFoundException;
    void transfer(String fromAccount, String toAccount, double amount) throws Exceptions.AccountNotFoundException, Exceptions.InsufficientBalanceException;
    List<Account> searchAccountByName(String name);
    List<Account> searchAccountByEmail(String email);
    List<Account> searchAccountByPhone(String phone);
}