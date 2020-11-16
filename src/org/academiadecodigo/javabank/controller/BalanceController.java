package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.authenticate.AuthenticateService;
import org.academiadecodigo.javabank.view.BalanceView;

/**
 * The {@link BalanceView} controller
 */
public class BalanceController extends AbstractController {

    private AuthenticateService authenticateService;

    public void setAuthenticateService(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    public Customer getLoginCustomer() {
        return authenticateService.getAccessingCustomer();
    }
}
