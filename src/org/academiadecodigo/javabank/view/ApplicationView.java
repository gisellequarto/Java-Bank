package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.controller.BankApplication;
import org.academiadecodigo.javabank.controller.operations.Operation;
import org.academiadecodigo.javabank.model.domain.Bank;

import java.util.Map;

public class ApplicationView {

    private Prompt prompt;
    private MenuInputScanner mainMenu;
    private Map<Integer, Operation> operationsMap;
    private BankApplication bankApplication;

    public ApplicationView(Bank bank) {
        bankApplication = new BankApplication(bank, this);
        prompt = new Prompt(System.in, System.out);
    }

    /**
     * Gets the prompt used for the UI
     *
     * @return the prompt
     */
    public Prompt getPrompt() {
        return prompt;
    }

    public void start() {
        mainMenu = buildMainMenu();
        bankApplication.start();
    }

    public int getUserChoice() {

        int userChoice = prompt.getUserInput(mainMenu);

        if (userChoice == UserOptions.QUIT.getOption()) {
            System.exit(0);
        }
        return userChoice;
    }

    public int scanCustomerId() {

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bankApplication.getCustomerIds());
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);

        return prompt.getUserInput(scanner);
    }


    private MenuInputScanner buildMainMenu() {

        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

        return mainMenu;
    }

}
