package com.example.lab9.repository;

import com.example.lab9.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select distinct i from Item i left join fetch i.manufacturer")
    java.util.List<Item> findAllWithManufacturer();
}
