package com.api.logisticaapi.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.api.logisticaapi.Domain.Models.Delivery;
import com.api.logisticaapi.Dtos.Request.DeliveryRequest;
import com.api.logisticaapi.Dtos.Response.DeliveryDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DeliveryDTO toModel(Delivery delivery) {
        return modelMapper.map(delivery, DeliveryDTO.class);
    }

    public List<DeliveryDTO> toListModel(List<Delivery> delivery) {
        return delivery.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Delivery toEntity(DeliveryRequest deliveryInput) {
        return modelMapper.map(deliveryInput, Delivery.class);
    }
}
