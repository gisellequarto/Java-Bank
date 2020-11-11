package org.academiadecodigo.javabank.domain;

import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * The bank entity
 */
public class Bank {

    private AccountManager accountManager;
    private Set<Customer> customers = new HashSet<>();

    /**
     * Creates a new instance of Bank and initializes it with the given account manager
     *
     * @param accountManager the account manager
     */
    public Bank(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Adds a new customer to the bank
     *
     * @param customer the new bank customer
     * @see Customer#setAccountManager(AccountManager)
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.setCustomerId(customers.size());
        customer.setAccountManager(accountManager);
    }

    /**
     * Gets the total balance of the bank
     *
     * @return the bank total balance
     */
    public double getBalance() {

        double balance = 0;

        for (Customer customer : customers) {
            balance += customer.getBalance();
        }

        return balance;
    }

    public boolean checkCustomer(int inputId) {
        for(Customer c : customers) {
            if (c.getCustomerId() == inputId) {
                return true;
            }
        }
        return false;
    }

    public Customer getCustomer(int customerId) {
        for(Customer c : customers) {
            if (c.getCustomerId() == customerId) {
                return c;
            }
        }
        throw new NoSuchElementException();
    }


}
