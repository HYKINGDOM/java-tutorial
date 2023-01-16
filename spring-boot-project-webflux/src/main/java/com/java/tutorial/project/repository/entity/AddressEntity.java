package com.java.tutorial.project.repository.entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@Table("address")
public class AddressEntity {

    @Id
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
    private Timestamp lastUpdate;

}
