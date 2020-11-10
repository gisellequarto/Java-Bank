package org.academiadecodigo.javabank.application;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class BankMenu {

    private Prompt prompt;
    private String[] options;
    private int userOption;
    private static int customerId;
    private BankApplication bankApplication;

    public BankMenu(BankApplication bankApplication) {
        this.bankApplication = bankApplication;
        prompt = new Prompt(System.in, System.out);
        options = new String[]{"View Balance", "Make Deposit", "Make Withdraw", "Open Account", "Quit"};
        loadBankMenu();
    }

    public void loadBankMenu() {
        IntegerInputScanner scannerInt = new IntegerInputScanner();
        MenuInputScanner scannerMenu = new MenuInputScanner(options);

        scannerInt.setMessage("Please insert your customer number: ");
        customerId = prompt.getUserInput(scannerInt);

        scannerMenu.setMessage("Welcome to the incredible JAVABANK!");
        while (userOption != 5) {
            scannerMenu.setError("This isn't a valid option");
            userOption = prompt.getUserInput(scannerMenu);
            bankApplication.makeOperation(userOption);
        }
    }


}
