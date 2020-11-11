package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.javabank.domain.Bank;

public class Quit implements Operation {
    @Override
    public void makeOperation(Bank bank, int customerId) {
        System.out.println("\nBye " + bank.getCustomer(customerId).getName() + ". Hope to see you soon!");
    }
}
