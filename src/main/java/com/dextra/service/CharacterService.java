package com.dextra.service;

import com.dextra.exception.NotFoundException;
import com.dextra.model.Character;
import com.dextra.model.CharacterRequest;
import com.dextra.repository.CharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CharacterService {

    private CharacterRepository characterRepository;
    private HouseService houseService;

    public List<Character> findAll(){
        return characterRepository.findAll();
    }

    public Character findById(String id) {
        return characterRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Character not found. Id: %s", id)));
    }

    public List<Character> findByHouse(String house) {
        return characterRepository.findByHouse(house).orElseThrow(() -> new NotFoundException(String.format("Character not found. House: %s", house)));
    }

    public Character insert(CharacterRequest request) {
        return characterRepository.save(Character.builder()
                .name(request.getName())
                .role(request.getRole())
                .school(request.getSchool())
                .patronus(request.getPatronus())
                .house(houseService.findById(request.getHouse()))
                .build());
    }

    public void update(String id, CharacterRequest request) {
        Character current = findById(id);
        Optional.ofNullable(request.getName()).ifPresent(current::setName);
        Optional.ofNullable(request.getRole()).ifPresent(current::setRole);
        Optional.ofNullable(request.getSchool()).ifPresent(current::setSchool);
        Optional.ofNullable(request.getPatronus()).ifPresent(current::setPatronus);
        Optional.ofNullable(request.getHouse()).ifPresent(value -> current.setHouse(houseService.findById(request.getHouse())));

        characterRepository.save(current);
    }

    public void deleteById(String id){
        characterRepository.deleteById(id);
    }
}
