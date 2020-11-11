package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.account.Account;

import java.util.Map;
import java.util.Set;

public class ViewBalance implements Operation{


    @Override
    public void makeOperation(Bank bank, int customerId) {

        Set<Integer> accountsId = bank.getCustomer(customerId).getAccounts().keySet();
        Map<Integer, Account> customerAccounts = bank.getCustomer(customerId).getAccounts();
        bank.getCustomer(customerId);

        for(Integer i : accountsId) {
            String type = customerAccounts.get(i).getAccountType().toString();
            System.out.print("\n" + type +" Balance: " + bank.getCustomer(customerId).getBalance(i) + " €.");
        }

        System.out.println("\nTotal Balance: " + bank.getCustomer(customerId).getBalance() + " €.");

    }

}
