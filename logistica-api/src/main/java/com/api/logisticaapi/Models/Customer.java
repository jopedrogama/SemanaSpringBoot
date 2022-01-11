package com.api.logisticaapi.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

}
