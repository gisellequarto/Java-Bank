package org.academiadecodigo.javabank.dao;

import org.academiadecodigo.javabank.model.account.Account;

import javax.persistence.EntityManager;

public class DaoAccount {

    private SessionManager sessionManager;

    public Account getAccount(Integer id) {

        EntityManager em = sessionManager.getCurrentSession();

        Account account = em.find(Account.class, id);

        sessionManager.stopSession();

        return account;
    }

    public boolean saveAccount(Account account) {

        EntityManager em = sessionManager.getCurrentSession();

        try {
            em.merge(account);
        } catch (IllegalArgumentException e) {
            sessionManager.stopSession();
            return false;
        }
        sessionManager.stopSession();
        return true;
    }
    
    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

}
