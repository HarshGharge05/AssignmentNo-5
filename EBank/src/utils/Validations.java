package utils;

import exceptions.Exceptions;
import library.Account;

public class Validations {
    public static void validateAccount(Account account) throws Exceptions.InvalidAccountException {
        if (account.getName().length() <= 5) {
            throw new Exceptions.InvalidAccountException("Name should be more than 5 characters.");
        }
        if (account.getBalance() <= 100) {
            throw new Exceptions.InvalidAccountException("Balance should be more than 100.");
        }
        if (!account.getEmail().contains("@") || !account.getEmail().contains(".")) {
            throw new Exceptions.InvalidAccountException("Email should contain '.' and '@'.");
        }
        if (account.getPhone().length() != 10) {
            throw new Exceptions.InvalidAccountException("Phone number should be of 10 digits.");
        }
    }
}