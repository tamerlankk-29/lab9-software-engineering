package com.example.lab9.service;

import com.example.lab9.domain.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    void findAll_and_findById() {
        List<Country> all = countryService.findAll();
        assertTrue(all.size() > 0);
        Country any = all.get(0);
        assertTrue(countryService.findById(any.getId()).isPresent());
    }

    @Test
    void create_update_delete() {
        Country c = countryService.create("TestLand", "TL");
        assertNotNull(c.getId());

        Country updated = countryService.update(c.getId(), "TestLand2", "TL2");
        assertEquals("TestLand2", updated.getName());
        assertEquals("TL2", updated.getCode());

        Long id = updated.getId();
        countryService.delete(id);
        assertTrue(countryService.findById(id).isEmpty());
    }

    @Test
    void findByCode_seeded() {
        assertTrue(countryService.findByCode("CN").isPresent());
        assertTrue(countryService.findByCode("DE").isPresent());
    }
}
