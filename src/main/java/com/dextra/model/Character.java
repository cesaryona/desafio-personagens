package com.dextra.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@Document(collection = "character")
public class Character implements Serializable {

    @Id
    private String id;

    private String name;
    private String role;
    private String school;
    private String patronus;

    @DBRef
    private House house;

}
