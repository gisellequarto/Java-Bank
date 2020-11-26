package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * An {@link AccountService} implementation
 */
public class AccountServiceImpl implements AccountService {

    /**
     * @see AccountService#add(Account)
     */
    public void add(Account account) {

        EntityManager em = ConnectionManager.getEm();

        em.getTransaction().begin();

        em.merge(account);

        em.getTransaction().commit();

        em.close();
    }

    /**
     * @see AccountService#deposit(int, double)
     */
    public void deposit(int id, double amount) {

        EntityManager em = ConnectionManager.getEm();
        em.getTransaction().begin();

        Account account = em.merge(em.find(Account.class, id));
        account.credit(amount);

        em.getTransaction().commit();
        em.close();

    }

    /**
     * @see AccountService#withdraw(int, double)
     */
    public void withdraw(int id, double amount) {

        EntityManager em = ConnectionManager.getEm();
        em.getTransaction().begin();

        Account account = em.merge(em.find(Account.class, id));

        if (account.canWithdraw()) {
            account.credit(amount);
        }

        em.getTransaction().commit();
        em.close();
    }

    /**
     * @see AccountService#transfer(int, int, double)
     */
    public void transfer(int srcId, int dstId, double amount) {

        EntityManager em = ConnectionManager.getEm();
        em.getTransaction().begin();

        Account srcAccount = em.merge(em.find(Account.class, srcId));
        Account dstAccount = em.merge(em.find(Account.class, dstId));

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }

        em.getTransaction().commit();
        em.close();
    }
}
