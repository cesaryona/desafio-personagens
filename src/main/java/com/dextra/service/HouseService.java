package com.dextra.service;

import com.dextra.exception.NotFoundException;
import com.dextra.model.House;
import com.dextra.repository.HouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HouseService {

    private static final String HOUSE_NOT_FOUND = "House not found. Id: %s";

    private HouseRepository houseRepository;

    public List<House> findAll() {
        return houseRepository.findAll();
    }

    public House findById(String id) {
        return houseRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(HOUSE_NOT_FOUND, id)));
    }
}
