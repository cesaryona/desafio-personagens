package com.dextra.controller;

import com.dextra.model.Character;
import com.dextra.model.CharacterRequest;
import com.dextra.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/character")
@AllArgsConstructor
public class CharacterController {

    private CharacterService characterService;

    @GetMapping
    public List<Character> findById() {
        return characterService.findAll();
    }

    @GetMapping("/{id}")
    public Character findById(@PathVariable String id) {
        return characterService.findById(id);
    }

    @GetMapping("/find-by-house")
    public List<Character> findByHouse(@RequestParam(required = true) String house) {
        return characterService.findByHouse(house);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Character insert(@RequestBody CharacterRequest request) {
        return characterService.insert(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestBody CharacterRequest request){
        characterService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        characterService.deleteById(id);
    }
}
