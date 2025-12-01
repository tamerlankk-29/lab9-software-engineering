package com.example.lab9.service;

import com.example.lab9.domain.Country;
import com.example.lab9.domain.Item;
import com.example.lab9.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;
    private final CountryService countryService;

    public ItemService(ItemRepository itemRepository, CountryService countryService) {
        this.itemRepository = itemRepository;
        this.countryService = countryService;
    }

    public List<Item> findAllWithManufacturer() {
        return itemRepository.findAllWithManufacturer();
    }

    @Transactional
    public Item create(String name, int price, int quantity, Long manufacturerId) {
        Country manufacturer = countryService.findById(manufacturerId)
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + manufacturerId));
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setQuantity(quantity);
        item.setManufacturer(manufacturer);
        return itemRepository.save(item);
    }
}
