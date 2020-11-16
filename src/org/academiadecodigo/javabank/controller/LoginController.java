package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.service.authenticate.AuthenticateService;
import org.academiadecodigo.javabank.service.customer.CustomerService;
import org.academiadecodigo.javabank.view.LoginView;

import java.util.Set;

/**
 * The {@link LoginView} controller
 */
public class LoginController extends AbstractController {

    private Controller nextController;

    private AuthenticateService authenticateService;
    private CustomerService customerService;
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

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Identifies the logged in customer
     *
     * @param id the customer id
     */
    public void onLogin(int id) {
        authenticateService.authenticate(id);
        nextController.init();
    }


    public Set<Integer> getCustomerIds() {
        return customerService.getCustomerIds();
    }



}
