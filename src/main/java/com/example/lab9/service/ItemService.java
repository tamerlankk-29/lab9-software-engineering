package com.example.lab9.service;

import com.example.lab9.domain.Country;
import com.example.lab9.domain.Item;
import com.example.lab9.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
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

    @Transactional
    public Item update(Long id, String name, int price, int quantity, Long manufacturerId) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + id));
        item.setName(name);
        item.setPrice(price);
        item.setQuantity(quantity);
        if (manufacturerId != null) {
            Country manufacturer = countryService.findById(manufacturerId)
                    .orElseThrow(() -> new IllegalArgumentException("Country not found: " + manufacturerId));
            item.setManufacturer(manufacturer);
        }
        return itemRepository.save(item);
    }

    @Transactional
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}
