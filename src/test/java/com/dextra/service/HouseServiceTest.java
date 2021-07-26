package com.dextra.service;

import com.dextra.model.House;
import com.dextra.repository.HouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HouseServiceTest {

    private HouseRepository houseRepository;

    private HouseService houseService;

    @BeforeEach
    public void setup() {
        this.houseRepository = Mockito.mock(HouseRepository.class);

        this.houseService = new HouseService(houseRepository);
    }

    @Test
    public void shouldReturnAListOfHouses() {
        List<House> houseListMock = Arrays.asList(getHouseGryffindor(), getHouseRavenclaw());

        Mockito.when(this.houseRepository.findAll()).thenReturn(houseListMock);

        List<House> houseList = this.houseService.findAll();

        assertNotNull(houseList);
        assertEquals(2, houseList.size());
        assertEquals("1760529f-6d51-4cb1-bcb1-25087fce5bde", houseList.get(0).getId());
        assertEquals("56cabe3a-9bce-4b83-ba63-dcd156e9be45", houseList.get(1).getId());
        assertEquals("Goderic Gryffindor", houseList.get(0).getFounder());
        assertEquals("Rowena Ravenclaw", houseList.get(1).getFounder());
        assertEquals("lion", houseList.get(0).getMascot());
        assertEquals("eagle", houseList.get(1).getMascot());
    }

    @Test
    public void shouldReturnAHouseById() {
        Mockito.when(this.houseRepository.findById(Mockito.anyString())).thenReturn(Optional.of(getHouseGryffindor()));

        House house = this.houseService.findById("1760529f-6d51-4cb1-bcb1-25087fce5bde");

        assertNotNull(house);
        assertEquals("1760529f-6d51-4cb1-bcb1-25087fce5bde", house.getId());
        assertEquals("Gryffindor", house.getName());
        assertEquals("Minerva McGonagall", house.getHeadOfHouse());
        assertEquals(Arrays.asList("courage", "bravery", "nerve", "chivalry"), house.getValues());
        assertEquals(Arrays.asList("scarlet", "gold"), house.getColors());
        assertEquals("Hogwarts School of Witchcraft and Wizardry", house.getSchool());
        assertEquals("lion", house.getMascot());
        assertEquals("Nearly Headless Nick", house.getHouseGhost());
        assertEquals("Goderic Gryffindor", house.getFounder());
    }

    private House getHouseGryffindor() {
        return new House("1760529f-6d51-4cb1-bcb1-25087fce5bde", "Gryffindor", "Minerva McGonagall", Arrays.asList("courage", "bravery", "nerve", "chivalry"), Arrays.asList("scarlet", "gold"), "Hogwarts School of Witchcraft and Wizardry",
                "lion", "Nearly Headless Nick", "Goderic Gryffindor");
    }

    private House getHouseRavenclaw() {
        return new House("56cabe3a-9bce-4b83-ba63-dcd156e9be45", "Ravenclaw", "Fillius Flitwick", Arrays.asList("intelligence", "creativity", "learning", "wit"), Arrays.asList("blue", "bronze"), "Hogwarts School of Witchcraft and Wizardry",
                "eagle", "The Grey Lady", "Rowena Ravenclaw");
    }
}
