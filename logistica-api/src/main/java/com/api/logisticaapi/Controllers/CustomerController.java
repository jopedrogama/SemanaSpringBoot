package com.api.logisticaapi.Controllers;

// import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.api.logisticaapi.Domain.Models.Customer;
import com.api.logisticaapi.Domain.Repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping()
    public List<Customer> list() {
        return customerRepository.findAll();
        // return customerRepository.findByEmail("jopedrogama@gmail.com");
        // return customerRepository.findByEmailContaining("@gmail.com");
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getById(@PathVariable long customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (!customer.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(customer.get());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> update(@Valid @PathVariable Long customerId, @RequestBody Customer customer) {

        if (!customerRepository.existsById(customerId)) {
            return ResponseEntity.notFound().build();
        }
        customer.setId(customerId);
        customerRepository.save(customer);

        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable Long customerId) {

        if (!customerRepository.existsById(customerId)) {
            return ResponseEntity.notFound().build();
        }

        customerRepository.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }

    /*
     * Metodo 2
     */

    // @PersistenceContext
    // private EntityManager manager;

    // @GetMapping("/customers")
    // public List<Customer> list() {
    // // JPQL
    // return manager.createQuery("from Customer", Customer.class).getResultList();
    // }

    // @GetMapping("/customers")
    // public List<Customer> listar() {

    // /* METODO 1
    // * THERE ARE MANY WAYS TO DO THE RETURN.
    // * FIRST ONE IS AS COMMENT BELLOW
    // */

    // // List<Customer> listCustomers = new ArrayList<>();

    // // listCustomers.add(new Customer(
    // // (long) 1, "Joao", "jopedrogama@gmail.com", "62981613459"));

    // // listCustomers.add(new Customer(
    // // (long) 2, "Thiago", "thiago_fel@gmail.com", "62982329100"));

    // // return listCustomers;

    // var customer1 = new Customer((long) 1, "Joao", "jopedrogama@gmail.com",
    // "62981613459");
    // var customer2 = new Customer((long) 2, "Thiago", "thiago_fel@gmail.com",
    // "62982329100");

    // return Arrays.asList(customer1, customer2);
    // }
}
