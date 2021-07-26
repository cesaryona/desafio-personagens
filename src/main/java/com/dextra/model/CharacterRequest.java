package com.dextra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CharacterRequest {

    private String name;
    private String role;
    private String school;
    private String patronus;
    private String house;
}
