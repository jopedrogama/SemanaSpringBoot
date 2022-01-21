package com.api.logisticaapi.Domain.Models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Recipient {

    @NotBlank
    @Column(name = "recipient_name")
    private String name;

    @NotBlank
    @Column(name = "recipient_street")
    private String street;

    @Column(name = "recipient_building_number")
    private String buildingNumber;

    @Column(name = "recipient_address_complement")
    private String addressComplement;

    @NotBlank
    @Column(name = "recipient_district")
    private String district;

    @NotBlank
    @Size(min = 7, max = 8)
    @Column(name = "recipient_cep")
    private String cep;

    @NotBlank
    @Column(name = "recipient_city")
    private String city;

    @NotBlank
    @Column(name = "recipient_state")
    private String state;

    @Override
    public String toString() {
        return "Name:" + this.name + ". street:" + this.street;
    }
}
