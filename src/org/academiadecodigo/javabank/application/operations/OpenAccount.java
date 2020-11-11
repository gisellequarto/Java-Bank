package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;

public class OpenAccount implements Operation {

    protected static Customer customer;
    protected static int actualId;
    protected static AccountType type;
    protected static AccountManager manager;

    @Override
    public void makeOperation() {
        customer = new Customer();
        manager = new AccountManager();
        customer.setAccountManager(manager);

        type = accountType();
        actualId = customer.openAccount(type);

        System.out.println("\nTotal Balance: " + customer.getBalance(actualId) + " €.");
    }

    private AccountType accountType() {
        String[] options = new String[]{"Checking Account", "Savings Account"};
        Prompt prompt = new Prompt(System.in, System.out);

        MenuInputScanner scannerAccountType = new MenuInputScanner(options);

        scannerAccountType.setMessage("Please choose your account type: ");
        scannerAccountType.setError("This isn't a valid option");

        int customerOption = prompt.getUserInput(scannerAccountType);

        if(customerOption == 1) {
            return AccountType.CHECKING;
        }
        return AccountType.SAVINGS;
    }

}
