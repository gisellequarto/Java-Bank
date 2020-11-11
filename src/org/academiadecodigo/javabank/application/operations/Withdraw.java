package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;

public class Withdraw implements Operation{

    @Override
    public void makeOperation() {

        if(OpenAccount.customer == null) {
            System.out.println("You are not our customer yet. Please, open your account with us.");
            return;
        }

        double amount = inputAmount();
        OpenAccount.manager.withdraw(OpenAccount.actualId, amount);

        System.out.println("\n" + OpenAccount.type.toString() + " balance: " + OpenAccount.customer.getBalance(OpenAccount.actualId) + " €.");

        System.out.println("\nTotal Balance: " + OpenAccount.customer.getBalance(OpenAccount.actualId) + " €.");
    }

    private double inputAmount() {
        Prompt prompt = new Prompt(System.in, System.out);
        DoubleInputScanner scanner = new DoubleInputScanner();
        scanner.setMessage("\nWithdraw amount: ");

        double amount = prompt.getUserInput(scanner);
        return amount;
    }


}
