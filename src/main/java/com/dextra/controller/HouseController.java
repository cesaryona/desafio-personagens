package com.dextra.controller;

import com.dextra.model.House;
import com.dextra.service.HouseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/house")
@AllArgsConstructor
public class HouseController {

    private HouseService houseService;

    @GetMapping
    public List<House> findAll() {
        return houseService.findAll();
    }

    @GetMapping("/{id}")
    public House findById(@PathVariable String id) {
        return houseService.findById(id);
    }
}
