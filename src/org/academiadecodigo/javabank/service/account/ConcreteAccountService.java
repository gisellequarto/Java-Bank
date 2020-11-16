package org.academiadecodigo.javabank.service.account;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.service.authenticate.AuthenticateService;

import java.util.HashMap;
import java.util.Map;

public class ConcreteAccountService implements AccountService {

    private Map<Integer, Account> accountMap = new HashMap<>();

    public void add(Account account) {
        accountMap.put(account.getId(), account);
    }

    @Override
    public void deposit(int id, double amount) {
        if (accountMap.get(id).canCredit(amount)) {
            accountMap.get(id).credit(amount);
        }
    }

    @Override
    public void withdraw(int id, double amount) {
        if (accountMap.get(id).canDebit(amount)) {
            accountMap.get(id).debit(amount);
        }
    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {
        if(accountMap.get(srcId).canDebit(amount)) {
            if(accountMap.get(dstId).canCredit(amount)) {
                accountMap.get(srcId).debit(amount);
                accountMap.get(dstId).credit(amount);
            }
        }
    }


}
