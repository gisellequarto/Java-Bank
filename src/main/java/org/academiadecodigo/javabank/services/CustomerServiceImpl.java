package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * An {@link CustomerService} implementation
 */
public class CustomerServiceImpl implements CustomerService {

    /**
     * @see CustomerService#get(Integer)
     */
    @Override
    public Customer get(Integer id) {
        EntityManager em = ConnectionManager.getEm();
        em.getTransaction().begin();

        Customer customer = em.merge(em.find(Customer.class, id));

        em.getTransaction().commit();
        em.close();

        return customer;
    }

    /**
     * @see CustomerService#list()
     */
    @Override
    public List<Customer> list() {
        List<Customer> customerList = new LinkedList<>();

        EntityManager em = ConnectionManager.getEm();

        em.getTransaction().begin();

        TypedQuery<Customer> query =
                em.createQuery("SELECT customer FROM Customer customer", Customer.class);

        customerList = query.getResultList();

        em.getTransaction().commit();
        em.close();

        return customerList;
    }

    /**
     * @see CustomerService#listCustomerAccountIds(Integer)
     */
    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        Set<Integer> accountIds = new HashSet<>();
        List<Account> accountList = new LinkedList<>();

        EntityManager em = ConnectionManager.getEm();

        em.getTransaction().begin();

        TypedQuery<Account> query =
                em.createQuery("SELECT accounts FROM Accounts accounts WHERE accounts.customer.id = :id", Account.class);
        query.setParameter("id", id);
        accountList = query.getResultList();

        for (Account account : accountList) {
            accountIds.add(account.getId());
        }

        em.getTransaction().commit();
        em.close();

        return accountIds;
    }

    /**
     * @see CustomerService#getBalance(int)
     */
    @Override
    public double getBalance(int id) {

        Set<Integer> accountIds = listCustomerAccountIds(id);
        double balance = 0;

        EntityManager em = ConnectionManager.getEm();
        em.getTransaction().begin();

        for (Integer i : accountIds) {
            Account account = em.merge(em.find(Account.class, i));
            balance += account.getBalance();
        }

        em.getTransaction().commit();
        em.close();

        return balance;
    }

    /**
     * @see CustomerService#add(Customer)
     */
    @Override
    public void add(Customer customer) {

        EntityManager em = ConnectionManager.getEm();
        em.getTransaction().begin();

        em.persist(customer);

        em.getTransaction().commit();
        em.close();

    }
}
