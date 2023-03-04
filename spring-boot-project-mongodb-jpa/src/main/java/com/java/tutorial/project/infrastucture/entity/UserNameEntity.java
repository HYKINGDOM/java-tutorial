package com.java.tutorial.project.infrastucture.entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@Builder
@Data
@Entity
@Document(collection = "MGdatabase")
public class UserNameEntity {

    private String shortName;

    private String nikeName;
}
