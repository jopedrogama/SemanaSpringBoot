package com.api.logisticaapi.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.api.logisticaapi.Domain.Models.DeliveryEvent;
import com.api.logisticaapi.Dtos.Response.DeliveryEventDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryEventMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DeliveryEventDTO toModel(DeliveryEvent deliveryEvent) {
        return modelMapper.map(deliveryEvent, DeliveryEventDTO.class);
    }

    public List<DeliveryEventDTO> toList(List<DeliveryEvent> deliveryEvents) {
        return deliveryEvents.stream().map(this::toModel).collect(Collectors.toList());
    }

}
