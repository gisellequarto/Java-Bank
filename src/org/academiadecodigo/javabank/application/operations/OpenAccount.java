package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;

public class OpenAccount implements Operation {

    private Prompt prompt = new Prompt(System.in, System.out);
    private Bank bank;
    private int customerId;


    @Override
    public void makeOperation(Bank bank, int customerId) {

        this.bank = bank;
        this.customerId = customerId;

        if (customerId != 0) {
            oldClient();
            return;
        }

        newClient();
    }


    private AccountType newAccountType() {
        String[] options = new String[]{"Checking Account", "Savings Account"};

        MenuInputScanner scannerAccountType = new MenuInputScanner(options);

        scannerAccountType.setMessage("Please choose your account type: ");
        scannerAccountType.setError("This isn't a valid option");

        int customerOption = prompt.getUserInput(scannerAccountType);

        if (customerOption == 1) {
            return AccountType.CHECKING;
        }
        return AccountType.SAVINGS;
    }


    private void newClient() {
        System.out.println("\nCome to Javabank!");

        StringInputScanner scannerName = new StringInputScanner();
        scannerName.setMessage("Please, insert you name: ");

        String nameInput = prompt.getUserInput(scannerName);

        Customer customer = new Customer();
        customer.setName(nameInput);
        bank.addCustomer(customer);

        AccountType type = newAccountType();

        int accountId = customer.openAccount(type);
        customerId = customer.getCustomerId();

        System.out.println("\nYour customer ID is " + customerId + ". Please, keep it safe!");

        System.out.println(type.toString() + " Balance: " + customer.getBalance(accountId) + " €.");


    }


    private void oldClient() {
        System.out.println("You are already our customer. We are glad you decided to open another account with us.");
        AccountType type = newAccountType();

        int accountId = bank.getCustomer(customerId).openAccount(type);

        System.out.println("\nYour customer ID is the same: " + customerId + ". Please, keep it safe!");

        System.out.println("\n" + type.toString() + " Balance: " + bank.getCustomer(customerId).getBalance(accountId) + " €.");
        System.out.println("Total Balance: " + bank.getCustomer(customerId).getBalance() + " €.");
    }

}
