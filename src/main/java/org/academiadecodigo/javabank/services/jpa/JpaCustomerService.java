package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.dao.DaoCustomer;
import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.services.CustomerService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A JPA {@link CustomerService} implementation
 */
public class JpaCustomerService extends AbstractJpaService<Customer> implements CustomerService {

    private DaoCustomer daoCustomer;

    /**
     * @see AbstractJpaService#AbstractJpaService(EntityManagerFactory, Class)
     */
    public JpaCustomerService(EntityManagerFactory emf) {
        super(emf, Customer.class);
    }

    public JpaCustomerService() {
    }

    /**
     * @see CustomerService#getBalance(Integer)
     */
    @Override
    public double getBalance(Integer id) {

        transactionManager.beginRead();

        Customer customer = daoCustomer.getCustomer(id);

        if (customer == null) {
            transactionManager.rollback();
        }

        Double balance = customer.getAccounts().stream()
                .mapToDouble(Account::getBalance)
                .sum();

        transactionManager.commit();
        return balance;

    }

    /**
     * @see CustomerService#listCustomerAccountIds(Integer)
     */
    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        transactionManager.beginRead();

        Customer customer = daoCustomer.getCustomer(id);

        if (customer == null) {
            transactionManager.rollback();
        }

        Set<Integer> accountsIds = customer.getAccounts().stream()
                .map(AbstractModel::getId)
                .collect(Collectors.toSet());

        transactionManager.commit();
        return accountsIds;

    }

    public void setDaoCustomer(DaoCustomer daoCustomer) {
        this.daoCustomer = daoCustomer;
    }
}
