package library;

import java.io.Serializable;
import java.util.Random;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accountNumber;
    private String name;
    private String email;
    private String phone;
    private double balance;

    // Constructor for creating a new account
    public Account(String name, String email, String phone, double balance) {
        this.accountNumber = generateAccountNumber();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
    }

    // Constructor for loading an existing account from file
    public Account(String accountNumber, String name, String email, String phone, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
    }

    public static String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nPhone: " + phone +
                "\nBalance: " + balance;
    }

    public String toTextFormat() {
        return accountNumber + "," + name + "," + email + "," + phone + "," + balance;
    }

    public static Account fromTextFormat(String text) {
        String[] parts = text.split(",");
        return new Account(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4]));
    }

    // Getters and setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}