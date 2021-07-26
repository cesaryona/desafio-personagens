package com.dextra.repository;

import com.dextra.model.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {

    Optional<List<Character>> findByHouse(String house);

    @Transactional
    void deleteById(String id);
}
