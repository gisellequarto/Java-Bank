package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;

public class OpenAccount implements Operation {

    private static Customer customer;
    private static AccountManager manager;

    @Override
    public void makeOperation() {
        customer = new Customer();
        manager = new AccountManager();
        customer.setAccountManager(manager);

        int actualId = customer.openAccount(AccountType.CHECKING);
        System.out.println("\nTotal Balance: " + customer.getBalance(actualId) + " €.");
    }

    public static Customer getCustomer() {
        return customer;
    }

    public static AccountManager getManager() {
        return manager;
    }



}
