package com.example.lab9.service;

import com.example.lab9.domain.Country;
import com.example.lab9.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    public Optional<Country> findByCode(String code) {
        return countryRepository.findByCode(code);
    }

    @Transactional
    public Country create(String name, String code) {
        Country c = new Country();
        c.setName(name);
        c.setCode(code);
        return countryRepository.save(c);
    }

    @Transactional
    public Country update(Long id, String name, String code) {
        Country c = countryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + id));
        c.setName(name);
        c.setCode(code);
        return countryRepository.save(c);
    }

    @Transactional
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
