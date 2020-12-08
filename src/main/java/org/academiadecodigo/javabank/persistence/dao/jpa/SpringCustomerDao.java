package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.academiadecodigo.javabank.persistence.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SpringCustomerDao  extends GenericJpaDao<Customer> implements CustomerDao {

    /**
     * @see GenericJpaDao#GenericJpaDao(Class)
     */
    public SpringCustomerDao() {
        super(Customer.class);
    }

    /**
     * @see CustomerDao#getCustomerIds()
     */
    public List<Customer> getAllCustomers() {

        List<Customer> customerList = new ArrayList<>();
        List<Integer> customerIds = getCustomerIds();

        for (Integer i : customerIds) {
            Customer customer = em.createQuery("select " + i + " from Customer", Customer.class).getSingleResult();
            customerList.add(customer);
        }

        return customerList;
    }

    @Override
    public List<Integer> getCustomerIds() {
        return em.createQuery("select id from Customer", Integer.class)
                .getResultList();
    }

}
