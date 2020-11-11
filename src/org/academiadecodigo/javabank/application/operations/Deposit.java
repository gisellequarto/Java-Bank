package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;

import java.util.Map;
import java.util.Set;

public class Deposit implements Operation {

    private Bank bank;
    private int customerId;
    Prompt prompt = new Prompt(System.in, System.out);

    @Override
    public void makeOperation(Bank bank, int customerId) {

        this.bank = bank;
        this.customerId = customerId;


        if (customerId == 0) {
            System.out.println("You are not our customer yet. Please, open your account with us.");
            return;
        }

        int accountId = chooseAccount();
        double amount = inputAmount();

        AccountType type = bank.getCustomer(customerId).getAccounts().get(accountId).getAccountType();

        bank.getCustomer(customerId).getAccountManager().deposit(accountId, amount);

        System.out.println(type.toString() + " Balance: " + bank.getCustomer(customerId).getBalance(accountId) + " €.");

        System.out.println("\nTotal balance: " + bank.getCustomer(customerId).getBalance() + " €.");
    }

    private double inputAmount() {
        DoubleInputScanner scanner = new DoubleInputScanner();
        scanner.setMessage("\nDeposit amount: ");

        double amount = prompt.getUserInput(scanner);
        return amount;
    }

    private int chooseAccount() {
        Set<Integer> accountsId = bank.getCustomer(customerId).getAccounts().keySet();
        Map<Integer, Account> customerAccounts = bank.getCustomer(customerId).getAccounts();

        String[] options = new String[accountsId.size()];
        System.out.println(options.length);
        int counter = 0;

        for (Integer i : accountsId) {
            options[counter] = customerAccounts.get(i).getAccountType().toString() + "(Account Id: " + i + ")";
            System.out.println(options[counter]);
            counter++;
        }

        MenuInputScanner scannerMenu = new MenuInputScanner(options);

        scannerMenu.setMessage("Choose your account to deposit: ");
        scannerMenu.setError("This isn't a valid option");

        int accountId = prompt.getUserInput(scannerMenu);
        return accountId;
    }


}
