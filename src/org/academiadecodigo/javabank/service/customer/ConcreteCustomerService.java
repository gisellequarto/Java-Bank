package org.academiadecodigo.javabank.service.customer;

import org.academiadecodigo.javabank.model.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConcreteCustomerService implements CustomerService{

    protected Map<Integer, Customer> customersMap = new HashMap<>();

    @Override
    public Customer get(Integer id) {
        return customersMap.get(id);
    }

    @Override
    public List<Customer> list() {
        return null;
    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {
        return customersMap.get(id).getAccountIds();
    }

    @Override
    public double getBalance(int customerId) {
        return customersMap.get(customerId).getBalance();
    }

    @Override
    public void add(Customer customer) {
        customersMap.put(customer.getId(), customer);
    }

    @Override
    public Set<Integer> getCustomerIds() {
        return customersMap.keySet();
    }

}
