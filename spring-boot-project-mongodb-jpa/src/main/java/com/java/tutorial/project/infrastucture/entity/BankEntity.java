package com.java.tutorial.project.infrastucture.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.time.LocalDateTime;



@Entity
@Builder
@AllArgsConstructor
@Data
@Document(collection = "BankEntity")
public class BankEntity {

    private String value;

    private String primaryKey;

    private String realKey;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdTimestamp;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedTimestamp;

    @Version
    private int version;
}
