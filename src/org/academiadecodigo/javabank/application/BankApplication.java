package org.academiadecodigo.javabank.application;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.application.operations.*;
import org.academiadecodigo.javabank.domain.Bank;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

public class BankApplication {

    private Prompt prompt;
    private Map<Integer, Operation> operationMap;
    private Bank bank;
    private int customerId;

    public BankApplication(Bank bank) {
        prompt = new Prompt(System.in, System.out);
        operationMap = new TreeMap<>();
        initializeMap();
        this.bank = bank;
        entryOptions();
        //   loadAppMenu();
    }

    private void initializeMap() {
        operationMap.put(1, new ViewBalance());
        operationMap.put(2, new Deposit());
        operationMap.put(3, new Withdraw());
        operationMap.put(4, new OpenAccount());
        operationMap.put(5, new Quit());
    }

    public void makeOperation(int userOption) {
        operationMap.get(userOption).makeOperation(bank, customerId);
    }


    public void start() {

        int userOption = entryOptions();

        if(userOption = )



    }


    private void loadAppMenu() {
        int userOption = -1;

        while (userOption != 5) {

            if (bankLogin()) {

                String[] options = new String[]{"View Balance", "Make Deposit", "Make Withdraw", "Open Account", "Quit"};
                MenuInputScanner scannerMenu = new MenuInputScanner(options);

                scannerMenu.setMessage("This is JAVABANK!");
                while (userOption != 5) {
                    scannerMenu.setError("This isn't a valid option");
                    userOption = prompt.getUserInput(scannerMenu);
                    makeOperation(userOption);
                }
            }

            makeOperation(4);
        }

    }

    private boolean bankLogin() {

        IntegerInputScanner scannerInt = new IntegerInputScanner();

        scannerInt.setMessage("Please insert your customer number: ");

        int inputId = prompt.getUserInput(scannerInt);

        if (bank.checkCustomer(inputId)) {
            customerId = inputId;
            System.out.println("Welcome, " + bank.getCustomer(customerId).getName() + ". Happy to see you here again!");
            return true;
        }
        return false;
    }

    private int entryOptions() {

        int answer = 3;

        while (answer == 3) {
            String[] options = {"Login",
                    "Register"};
            answer = JOptionPane.showOptionDialog(null,
                    "How can we help you today?",
                    "JAVABANK",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,     //do not use a custom Icon
                    options,  //the titles of buttons
                    options[0]); //default button title

        }
        System.out.println(answer);
            return answer;
    }

}
