package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.operations.*;
import org.academiadecodigo.javabank.model.domain.Bank;
import org.academiadecodigo.javabank.view.ApplicationView;
import org.academiadecodigo.javabank.view.UserOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The bank application
 */
public class BankApplication {


    private ApplicationView applicationView;
    private Map<Integer, Operation> operationsMap;

    private Bank bank;
    private int accessingCustomerId;

    /**
     * Creates a new instance of a {@code BankApplication}, initializes it with the given {@link Bank}
     *
     * @param bank the bank instance
     */
    public BankApplication(Bank bank, ApplicationView applicationView) {
        this.bank = bank;
        this.applicationView = applicationView;
    }

    /**
     * Gets the bank used by this application
     *
     * @return the bank
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * Gets the id of the customer using the Bank Application
     *
     * @return the customer id
     */
    public int getAccessingCustomerId() {
        return accessingCustomerId;
    }

    /**
     * Starts the bank application
     */
    public void start() {

        accessingCustomerId = applicationView.scanCustomerId();
        operationsMap = buildOperationsMap();

        menuLoop();
    }

    private void menuLoop() {

        int userChoice = applicationView.getUserChoice();

        operationsMap.get(userChoice).execute();

        menuLoop();
    }

    public Prompt getPrompt() {
        return applicationView.getPrompt();
    }

    public Set<Integer> getCustomerIds() {
        return bank.getCustomerIds();
    }


    private Map<Integer, Operation> buildOperationsMap() {

        Map<Integer, Operation> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), new BalanceOperation(this));
        map.put(UserOptions.DEPOSIT.getOption(), new DepositOperation(this));
        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawOperation(this));
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountOperation(this));

        return map;
    }

}
