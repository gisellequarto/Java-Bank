package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.controller.AbstractController;
import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.account.AccountService;
import org.academiadecodigo.javabank.service.account.ConcreteAccountService;
import org.academiadecodigo.javabank.service.authenticate.AuthenticateService;
import org.academiadecodigo.javabank.service.customer.CustomerService;

import java.util.List;
import java.util.Set;

/**
 * A generic account transaction controller to be used as a base for concrete transaction controller implementations
 * @see AbstractController
 * @see AccountTransactionController
 */
public abstract class AbstractAccountTransactionController extends AbstractController implements AccountTransactionController {

    protected AccountService accountService;
    protected CustomerService customerService;
    protected AuthenticateService authenticateService;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setAuthenticateService(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @Override
    public Set<Integer> getCustomerIds() {
        return customerService.listCustomerAccountIds(authenticateService.getAccessingCustomer().getId());
    }

    @Override
    public Customer getLoginCustomer() {
        return authenticateService.getAccessingCustomer();
    }

}
