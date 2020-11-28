package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.account.Account;

import javax.persistence.EntityManager;

/**
 * An {@link AccountService} implementation
 */
public class AccountServiceImpl implements AccountService {

    private AuthService authService;
    /**
     * @see AccountService#add(Account)
     */
    public Account add(Account account) {

        authService.getAccessingCustomer().addAccount(account);

        EntityManager em = ConnectionManager.getEm();

        em.getTransaction().begin();

        Account accountCopy = em.merge(account);

        em.getTransaction().commit();

        em.close();

        return accountCopy;
    }

    /**
     * @see AccountService#deposit(int, double)
     */
    public void deposit(int id, double amount) {

        EntityManager em = ConnectionManager.getEm();
        em.getTransaction().begin();

        Account account = em.find(Account.class, id);
        account.credit(amount);
        em.merge(account);

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

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

}
