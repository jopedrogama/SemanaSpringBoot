package com.api.logisticaapi.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.api.logisticaapi.Models.Customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping("/customers")
    public List<Customer> listar() {

        /*
         * THERE ARE MANY WAYS TO DO THE RETURN.
         * FIRST ONE IS AS COMMENT BELLOW
         */

        // List<Customer> listCustomers = new ArrayList<>();

        // listCustomers.add(new Customer(
        // (long) 1, "Joao", "jopedrogama@gmail.com", "62981613459"));

        // listCustomers.add(new Customer(
        // (long) 2, "Thiago", "thiago_fel@gmail.com", "62982329100"));

        // return listCustomers;

        var customer1 = new Customer((long) 1, "Joao", "jopedrogama@gmail.com", "62981613459");
        var customer2 = new Customer((long) 2, "Thiago", "thiago_fel@gmail.com", "62982329100");

        return Arrays.asList(customer1, customer2);
    }
}
