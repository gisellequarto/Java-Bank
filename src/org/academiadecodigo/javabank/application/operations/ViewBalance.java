package org.academiadecodigo.javabank.application.operations;

public class ViewBalance implements Operation{


    @Override
    public void makeOperation() {

        if(OpenAccount.customer == null) {
            System.out.println("You are not our customer yet. Please, open your account with us.");
            return;
        }
        
        System.out.println("\n" + OpenAccount.type.toString() + " balance: " + OpenAccount.customer.getBalance(OpenAccount.actualId) + " €.");

        System.out.println("\nTotal Balance: " + OpenAccount.customer.getBalance(OpenAccount.actualId) + " €.");
    }
}
