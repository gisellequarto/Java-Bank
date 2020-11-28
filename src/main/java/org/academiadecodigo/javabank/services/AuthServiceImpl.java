package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

import javax.persistence.EntityManager;

/**
 * An {@link AuthService} implementation
 */
public class AuthServiceImpl implements AuthService {

    private CustomerService customerService;
    private Customer accessingCustomer;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * @see AuthService#authenticate(Integer)
     */
    @Override
    public boolean authenticate(Integer id) {
        accessingCustomer = customerService.get(id);
        return accessingCustomer != null;
    }

    /**
     * @see AuthService#getAccessingCustomer()
     */
    @Override
    public Customer getAccessingCustomer() {
        return accessingCustomer;
    }
}
