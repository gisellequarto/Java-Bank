package org.academiadecodigo.javabank.application;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.HashSet;
import java.util.Set;

public class App {

    public static void main(String[] args) {

        AccountManager manager = new AccountManager();
        Bank javaBank = new Bank(manager);

        Customer c1 = new Customer();
        Customer c2 = new Customer();

        c1.setName("Rosa Parks");
        c2.setName("Winnie Mandela");

        javaBank.addCustomer(c1);
        javaBank.addCustomer(c2);

        BankApplication bankApplication = new BankApplication(javaBank);



    }
}
