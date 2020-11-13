package org.academiadecodigo.javabank.service.account;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;

import java.util.Map;

public class ConcreteAccountService implements AccountService {

    private Map<Integer, Account> accountMap;
    private Controller controller;

    public void add(Account account) {
        accountMap.put(account.getId(), account);
    }

    @Override
    public void deposit(int id, double amount) {

    }

    @Override
    public void withdraw(int id, double amount) {

    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}
