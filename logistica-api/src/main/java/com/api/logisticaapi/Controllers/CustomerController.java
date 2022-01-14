package com.api.logisticaapi.Controllers;

// import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.api.logisticaapi.Domain.Models.Customer;
import com.api.logisticaapi.Domain.Repositories.CustomerRepository;
import com.api.logisticaapi.Domain.Services.CustomerCatalogService;

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

    @Autowired
    private CustomerCatalogService customerCatalogService;

    @GetMapping()
    public List<Customer> list() {
        return customerRepository.findAll();
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
        return customerCatalogService.save(customer);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> update(@Valid @PathVariable Long customerId, @RequestBody Customer customer) {

        if (!customerRepository.existsById(customerId)) {
            return ResponseEntity.notFound().build();
        }
        customer.setId(customerId);
        customerCatalogService.save(customer);

        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable Long customerId) {

        if (!customerRepository.existsById(customerId)) {
            return ResponseEntity.notFound().build();
        }

        customerCatalogService.delete(customerId);
        return ResponseEntity.noContent().build();
    }

}
