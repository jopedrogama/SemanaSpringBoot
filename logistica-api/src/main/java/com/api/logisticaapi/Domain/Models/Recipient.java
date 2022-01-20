package com.api.logisticaapi.Domain.Models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Recipient {

    @Column(name = "recipient_name")
    private String name;

    @Column(name = "recipient_street")
    private String street;

    @Column(name = "recipient_building_number")
    private String buildingNumber;

    @Column(name = "recipient_address_complement")
    private String addressComplement;

    @Column(name = "recipient_district")
    private String district;

    @Column(name = "recipient_cep")
    private String cep;

    @Column(name = "recipient_city")
    private String city;

    @Column(name = "recipient_state")
    private String state;

    @Override
    public String toString() {
        return "Name:" + this.name + ". street:" + this.street;
    }
}
