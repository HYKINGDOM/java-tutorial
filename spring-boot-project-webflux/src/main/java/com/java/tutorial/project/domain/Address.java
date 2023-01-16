package com.java.tutorial.project.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("sakila")
public class Address {

    @Column(value = "address_id")
    private Integer addressId;

    private String address;

    private String address2;

    private String district;

    @Column(value = "city_id")
    private Integer cityId;

    @Column(value = "postal_code")
    private String postalCode;

    private String phone;

    @Column(value = "last_update")
    private Date lastUpdate;

}
