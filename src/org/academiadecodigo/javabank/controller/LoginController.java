package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.service.authenticate.AuthenticateService;
import org.academiadecodigo.javabank.view.LoginView;

/**
 * The {@link LoginView} controller
 */
public class LoginController extends AbstractController {

    private Controller nextController;

    private Bank bank;

    private AuthenticateService authenticateService;
    /**
     * Sets the next controller
     *
     * @param nextController the next controller to be set
     */
    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    public void setAuthenticateService(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    /**
     * Sets the bank
     *
     * @param bank the bank to be set
     */

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * Identifies the logged in customer
     *
     * @param id the customer id
     */
    public void onLogin(int id) {
        bank.setLoginCustomer(id);
        nextController.init();
    }

}
