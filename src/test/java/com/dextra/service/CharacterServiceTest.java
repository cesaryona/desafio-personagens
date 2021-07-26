package com.dextra.service;

import com.dextra.model.Character;
import com.dextra.model.CharacterRequest;
import com.dextra.model.House;
import com.dextra.repository.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CharacterServiceTest {

    private CharacterRepository characterRepository;
    private HouseService houseService;

    private CharacterService characterService;

    @BeforeEach
    public void setup() {
        this.characterRepository = Mockito.mock(CharacterRepository.class);
        this.houseService = Mockito.mock(HouseService.class);

        this.characterService = new CharacterService(characterRepository, houseService);
    }

    @Test
    public void shouldReturnAListOfCharacters() {
        List<Character> characterListMock = Arrays.asList(getCharacterHarry(), getCharacterHermione());

        Mockito.when(this.characterRepository.findAll()).thenReturn(characterListMock);

        List<Character> characterList = this.characterService.findAll();

        assertNotNull(characterList);
        assertEquals(2, characterList.size());
        assertEquals("60fb735788c44265c16fbb16", characterList.get(0).getId());
        assertEquals("Harry Potter", characterList.get(0).getName());
        assertEquals("student", characterList.get(0).getRole());
        assertEquals("Hogwarts School of Witchcraft and Wizardry", characterList.get(0).getSchool());
        assertEquals("stag", characterList.get(0).getPatronus());
        assertNotNull(characterList.get(0).getHouse());
        assertEquals("60fb6217995ca741adcec02d", characterList.get(0).getHouse().getId());
        assertEquals("Gryffindor", characterList.get(0).getHouse().getName());
        assertEquals("Minerva McGonagall", characterList.get(0).getHouse().getHeadOfHouse());
        assertEquals(Arrays.asList("courage", "bravery", "nerve", "chivalry"), characterList.get(0).getHouse().getValues());
        assertEquals(Arrays.asList("scarlet", "gold"), characterList.get(0).getHouse().getColors());
        assertEquals("Hogwarts School of Witchcraft and Wizardry", characterList.get(0).getHouse().getSchool());
        assertEquals("lion", characterList.get(0).getHouse().getMascot());
        assertEquals("Nearly Headless Nick", characterList.get(0).getHouse().getHouseGhost());
        assertEquals("Goderic Gryffindor", characterList.get(0).getHouse().getFounder());
    }

    @Test
    public void shouldReturnACharacterById() {
        Character characterMock = getCharacterHarry();

        Mockito.when(this.characterRepository.findById(Mockito.anyString())).thenReturn(Optional.of(characterMock));

        Character character = this.characterService.findById("60fb735788c44265c16fbb16");

        assertNotNull(character);
        assertEquals("60fb735788c44265c16fbb16", character.getId());
        assertEquals("Harry Potter", character.getName());
        assertEquals("student", character.getRole());
        assertEquals("Hogwarts School of Witchcraft and Wizardry", character.getSchool());
        assertEquals("stag", character.getPatronus());
        assertNotNull(character.getHouse());
    }


    @Test
    public void shouldReturnACharacterByHouse() {
        List<Character> characterListMock = Arrays.asList(getCharacterHarry(), getCharacterHermione());

        Mockito.when(this.characterRepository.findByHouse(Mockito.anyString())).thenReturn(Optional.of(characterListMock));

        List<Character> characterList = this.characterService.findByHouse("60fb6217995ca741adcec02d");

        assertNotNull(characterList);
        assertEquals(2, characterList.size());
        assertEquals("60fb735788c44265c16fbb16", characterList.get(0).getId());
        assertEquals("8e4a081140924fa5ab9d542e", characterList.get(1).getId());
        assertEquals("Harry Potter", characterList.get(0).getName());
        assertEquals("Hermione Granger", characterList.get(1).getName());
        assertEquals("stag", characterList.get(0).getPatronus());
        assertEquals("otter", characterList.get(1).getPatronus());
    }

    @Test
    public void shouldInsertACharacter() {
        Mockito.when(this.houseService.findById(Mockito.anyString())).thenReturn(getHouse());
        this.characterService.insert(getCharacterRequest());
        Mockito.verify(characterRepository, Mockito.times(1)).save(Mockito.any(Character.class));
    }

    @Test
    public void shouldUpdateACharacter() {
        String characterId = "60fb735788c44265c16fbb16";
        Mockito.when(this.characterRepository.findById(characterId)).thenReturn(Optional.of(getCharacterHarry()));
        Mockito.when(this.houseService.findById(Mockito.anyString())).thenReturn(getHouse());

        CharacterRequest request = new CharacterRequest("Ronald Bilius Weasley", "student", "Hogwarts School of Witchcraft and Wizardry", "Jack Russell Terrier", "60fb6217995ca741adcec02d");

        this.characterService.update(characterId, request);

        Mockito.verify(characterRepository, Mockito.times(1)).save(Mockito.any(Character.class));
    }

    @Test
    public void shoulDeleteCharacterById() {
        String id = "60fb735788c44265c16fbb16";

        this.characterService.deleteById(id);

        Mockito.verify(characterRepository, Mockito.times(1)).deleteById(id);
    }

    private CharacterRequest getCharacterRequest() {
        return new CharacterRequest("Harry Potter", "student", "Hogwarts School of Witchcraft and Wizardry", "stag", "60fb6217995ca741adcec02d");
    }

    private Character getCharacterHarry() {
        return new Character("60fb735788c44265c16fbb16",
                "Harry Potter", "student", "Hogwarts School of Witchcraft and Wizardry", "stag", getHouse());
    }

    private Character getCharacterHermione() {
        return new Character("8e4a081140924fa5ab9d542e",
                "Hermione Granger", "student", "Hogwarts School of Witchcraft and Wizardry", "otter", getHouse());
    }

    private House getHouse() {
        return new House("60fb6217995ca741adcec02d", "Gryffindor", "Minerva McGonagall", Arrays.asList("courage", "bravery", "nerve", "chivalry"), Arrays.asList("scarlet", "gold"), "Hogwarts School of Witchcraft and Wizardry",
                "lion", "Nearly Headless Nick", "Goderic Gryffindor");

    }
}
