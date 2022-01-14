package com.api.logisticaapi.Domain.Repositories;

import java.util.List;
import java.util.Optional;

import com.api.logisticaapi.Domain.Models.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Coloca long pois é o mesmo tipo da id dos usuarios
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByName(String name);

    List<Customer> findByEmailContaining(String email);

    Optional<Customer> findByEmail(String email);

}
