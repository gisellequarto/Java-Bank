package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.dao.DaoAccount;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.services.AccountService;

import javax.persistence.EntityManagerFactory;

/**
 * A JPA {@link AccountService} implementation
 */
public class JpaAccountService extends AbstractJpaService<Account> implements AccountService {

    private DaoAccount daoAccount;
    /**
     * Initializes a new {@code JPA Service} instance given an entity manager factory and a model type
     *
     * @param emf       the entity manager factory
     * @param modelType the model type
     */


    public JpaAccountService(EntityManagerFactory emf, Class<Account> modelType) {
        super(emf, modelType);
    }

    public JpaAccountService() {
    }

    /**
     * @see AccountService#deposit(Integer, double)
     */
    @Override
    public void deposit(Integer id, double amount) {

        transactionManager.beginWrite();

        Account account = daoAccount.getAccount(id);

        if (account == null) {
            transactionManager.rollback();
        }

        if (account.canCredit(amount)) {
            account.credit(amount);
        }

        if (!daoAccount.saveAccount(account)) {
            transactionManager.rollback();
        }

        transactionManager.commit();

    }

    /**
     * @see AccountService#withdraw(Integer, double)
     */
    @Override
    public void withdraw(Integer id, double amount) {

        transactionManager.beginWrite();

        Account account = daoAccount.getAccount(id);

        if (account == null) {
            transactionManager.rollback();
        }

        if (account.canDebit(amount)) {
            account.debit(amount);
        }

        if (!daoAccount.saveAccount(account)) {
            transactionManager.rollback();
        }

        transactionManager.commit();

    }

    /**
     * @see AccountService#transfer(Integer, Integer, double)
     */
    @Override
    public void transfer(Integer srcId, Integer dstId, double amount) {

        transactionManager.beginWrite();

        Account accountSrc = daoAccount.getAccount(srcId);
        Account accountDst = daoAccount.getAccount(dstId);

        if (accountSrc == null || accountDst == null) {
            transactionManager.rollback();
        }

        if (accountSrc.canDebit(amount) && accountDst.canCredit(amount)) {
            accountSrc.debit(amount);
            accountDst.credit(amount);
        }

        if ((!daoAccount.saveAccount(accountSrc)) || (!daoAccount.saveAccount(accountDst))) {
            transactionManager.rollback();
        }

        transactionManager.commit();
    }

    public void setDaoAccount(DaoAccount daoAccount) {
        this.daoAccount = daoAccount;
    }

}
