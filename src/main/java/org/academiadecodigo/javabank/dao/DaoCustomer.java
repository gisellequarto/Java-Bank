package org.academiadecodigo.javabank.dao;

import org.academiadecodigo.javabank.model.Customer;

import javax.persistence.EntityManager;

public class DaoCustomer {

    private SessionManager sessionManager;

    public Customer getCustomer(Integer id) {

        EntityManager em = sessionManager.getCurrentSession();

        Customer customer = em.find(Customer.class, id);

        sessionManager.stopSession();

        return customer;

    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

}
