package com.dextra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Document(collection = "house")
public class House {

    @Id
    private String id;

    private String name;
    private String headOfHouse;
    private List<String> values;
    private List<String> colors;
    private String school;
    private String mascot;
    private String houseGhost;
    private String founder;
}
