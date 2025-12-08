package com.example.lab9.service;

import com.example.lab9.domain.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CountryService countryService;

    @Test
    void findAllWithManufacturer_and_findById() {
        List<Item> items = itemService.findAllWithManufacturer();
        assertTrue(items.size() > 0);
        Item any = items.get(0);
        assertTrue(itemService.findById(any.getId()).isPresent());
    }

    @Test
    void create_update_delete_withManufacturer() {
        Long cnId = countryService.findByCode("CN").orElseThrow().getId();
        Item created = itemService.create("Unit Item", 123, 7, cnId);
        assertNotNull(created.getId());
        assertNotNull(created.getManufacturer());
        assertEquals(cnId, created.getManufacturer().getId());

        Item updated = itemService.update(created.getId(), "Unit Item 2", 150, 9, cnId);
        assertEquals("Unit Item 2", updated.getName());
        assertEquals(150, updated.getPrice());
        assertEquals(9, updated.getQuantity());
        assertEquals(cnId, updated.getManufacturer().getId());

        Long id = updated.getId();
        itemService.delete(id);
        assertTrue(itemService.findById(id).isEmpty());
    }

    @Test
    void create_withInvalidManufacturer_throws() {
        assertThrows(IllegalArgumentException.class, () ->
                itemService.create("Bad", 1, 1, -1L)
        );
    }
}
