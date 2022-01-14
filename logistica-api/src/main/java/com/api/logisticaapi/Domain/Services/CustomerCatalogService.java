package com.api.logisticaapi.Domain.Services;

import javax.transaction.Transactional;

import com.api.logisticaapi.Domain.Exceptions.DomainException;
import com.api.logisticaapi.Domain.Models.Customer;
import com.api.logisticaapi.Domain.Repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCatalogService {

    @Autowired
    private CustomerRepository customerRepository;

    // A anotação transactional, cria a transação no BD. Ou vai ter commit no final,
    // ou rollback
    @Transactional
    public Customer save(Customer customer) {

        boolean hasCustomerWithThisEmail = customerRepository.findByEmail(customer.getEmail())
                .stream()
                .anyMatch(queriedCustomer -> !queriedCustomer.equals(customer));

        if (hasCustomerWithThisEmail) {
            throw new DomainException("E-mail already registered!");
        }
        return customerRepository.save(customer);
    }

    @Transactional
    public void delete(Long customerId) {
        customerRepository.deleteById(customerId);
    }

}
