package com.api.logisticaapi.Domain.Repositories;

import com.api.logisticaapi.Domain.Models.Delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
