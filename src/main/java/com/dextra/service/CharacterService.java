package com.dextra.service;

import com.dextra.exception.NotFoundException;
import com.dextra.model.Character;
import com.dextra.model.CharacterRequest;
import com.dextra.repository.CharacterRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CharacterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterService.class);

    private CharacterRepository characterRepository;
    private HouseService houseService;

    public List<Character> findAll(){
        LOGGER.info("Starting method 'findAll'");
        return characterRepository.findAll();
    }

    public Character findById(String id) {
        LOGGER.info("Starting method 'findById' for: {}", id);
        return characterRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Character not found. Id: %s", id)));
    }

    public List<Character> findByHouse(String house) {
        LOGGER.info("Starting method 'findByHouse' for: {}", house);
        return characterRepository.findByHouse(house).orElseThrow(() -> new NotFoundException(String.format("Character not found. House: %s", house)));
    }

    public Character insert(CharacterRequest request) {
        LOGGER.info("Starting method 'insert'");
        return characterRepository.save(Character.builder()
                .name(request.getName())
                .role(request.getRole())
                .school(request.getSchool())
                .patronus(request.getPatronus())
                .house(houseService.findById(request.getHouse()))
                .build());
    }

    public void update(String id, CharacterRequest request) {
        LOGGER.info("Starting method 'update' for: {}", id);
        Character current = findById(id);
        Optional.ofNullable(request.getName()).ifPresent(current::setName);
        Optional.ofNullable(request.getRole()).ifPresent(current::setRole);
        Optional.ofNullable(request.getSchool()).ifPresent(current::setSchool);
        Optional.ofNullable(request.getPatronus()).ifPresent(current::setPatronus);
        Optional.ofNullable(request.getHouse()).ifPresent(value -> current.setHouse(houseService.findById(request.getHouse())));

        characterRepository.save(current);
    }

    public void deleteById(String id){
        LOGGER.info("Starting method 'deleteById' for: {}", id);
        characterRepository.deleteById(id);
    }
}
