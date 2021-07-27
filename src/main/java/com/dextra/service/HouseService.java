package com.dextra.service;

import com.dextra.exception.NotFoundException;
import com.dextra.model.House;
import com.dextra.repository.HouseRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HouseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HouseService.class);

    private static final String HOUSE_NOT_FOUND = "House not found. Id: %s";

    private HouseRepository houseRepository;

    public List<House> findAll() {
        LOGGER.info("Starting method 'findAll'");
        return houseRepository.findAll();
    }

    public House findById(String id) {
        LOGGER.info("Starting method 'findById' for: {}", id);
        return houseRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(HOUSE_NOT_FOUND, id)));
    }
}
