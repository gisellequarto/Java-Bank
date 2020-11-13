package org.academiadecodigo.javabank.service.authenticate;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.customer.CustomerService;

public class ConcreteAuthenticateService implements AuthenticateService {

    private int loginCustomer;
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean authenticate(Integer id) {
        if(customerService.get(id) == null){
         return false;
        }
        loginCustomer = id;
        return true;
    }

    @Override
    public Customer getAccessingCustomer() {
        return customerService.get(loginCustomer);
    }

}
