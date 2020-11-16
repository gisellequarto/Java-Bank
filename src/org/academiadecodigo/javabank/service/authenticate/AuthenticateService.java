package org.academiadecodigo.javabank.service.authenticate;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.service.customer.CustomerService;

public interface AuthenticateService {

   boolean authenticate(Integer id);
    Customer getAccessingCustomer();

    void setCustomerService(CustomerService customerService);

}
