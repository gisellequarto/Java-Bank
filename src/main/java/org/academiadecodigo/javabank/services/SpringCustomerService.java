package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.persistence.dao.jpa.SpringCustomerDao;
import org.academiadecodigo.javabank.persistence.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringCustomerService {

    private SpringCustomerDao springCustomerDao;

    @Autowired
    public void setSpringCustomerDao(SpringCustomerDao springCustomerDao) {
        this.springCustomerDao = springCustomerDao;
    }

    public List<Customer> getAllCustomers() {
        return springCustomerDao.getAllCustomers();
    }

}
