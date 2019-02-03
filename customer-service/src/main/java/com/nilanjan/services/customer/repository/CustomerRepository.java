package com.nilanjan.services.customer.repository;

import com.nilanjan.services.customer.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Nilanjan Roy
 */
public class CustomerRepository  {

    private List<Customer> customers = new ArrayList<>();

    public Customer add(Customer customer) {
        customer.setId((long) (customers.size()+1));
        customers.add(customer);
        return customer;
    }

    public Customer update(Customer account) {
        customers.set(account.getId().intValue() - 1, account);
        return account;
    }

    public Customer findById(Long id) {
        Optional<Customer> account = customers.stream().filter(a -> a.getId().equals(id)).findFirst();
        if (account.isPresent())
            return account.get();
        else
            return null;
    }

    public void delete(Long id) {
        customers.remove(id.intValue());
    }

    public List<Customer> find(List<Long> ids) {
        return customers.stream().filter(a -> ids.contains(a.getId())).collect(Collectors.toList());
    }

    public List<Customer> findByCustomer(Long customerId) {
        return customers.stream().filter(a -> a.getCustomerId().equals(customerId)).collect(Collectors.toList());
    }
}
