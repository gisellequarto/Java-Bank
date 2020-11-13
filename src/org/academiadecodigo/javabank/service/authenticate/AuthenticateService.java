package org.academiadecodigo.javabank.service.authenticate;

import org.academiadecodigo.javabank.model.Customer;

public interface AuthenticateService {

    boolean authenticate(Integer id);
    Customer getAccessingCustomer();

}
