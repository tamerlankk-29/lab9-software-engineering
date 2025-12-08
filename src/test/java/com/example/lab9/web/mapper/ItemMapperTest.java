package com.example.lab9.web.mapper;

import com.example.lab9.domain.Country;
import com.example.lab9.domain.Item;
import com.example.lab9.web.dto.ItemCreateDto;
import com.example.lab9.web.dto.ItemDto;
import com.example.lab9.web.dto.ItemUpdateDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemMapperTest {

    private final ItemMapper mapper = Mappers.getMapper(ItemMapper.class);

    @Test
    void entityToDto_withManufacturer() {
        Country cn = new Country(); cn.setId(10L); cn.setName("China"); cn.setCode("CN");
        Item i = new Item();
        i.setId(5L); i.setName("Phone"); i.setPrice(1000); i.setQuantity(3);
        i.setManufacturer(cn);
        ItemDto dto = mapper.toDto(i);
        assertEquals(5L, dto.getId());
        assertEquals("Phone", dto.getName());
        assertEquals(1000, dto.getPrice());
        assertEquals(3, dto.getQuantity());
        assertEquals(10L, dto.getManufacturerId());
    }

    @Test
    void listMapping() {
        Item i1 = new Item(); i1.setId(1L); i1.setName("A"); i1.setPrice(1); i1.setQuantity(1);
        Item i2 = new Item(); i2.setId(2L); i2.setName("B"); i2.setPrice(2); i2.setQuantity(2);
        List<ItemDto> list = mapper.toDto(List.of(i1, i2));
        assertEquals(2, list.size());
        assertEquals(1L, list.get(0).getId());
        assertEquals("B", list.get(1).getName());
    }

    @Test
    void fromCreateDto_nullManufacturer() {
        ItemCreateDto dto = new ItemCreateDto();
        dto.setName("X"); dto.setPrice(10); dto.setQuantity(2); dto.setManufacturerId(99L);
        var entity = mapper.fromCreateDto(dto);
        assertNull(entity.getId());
        assertEquals("X", entity.getName());
        assertEquals(10, entity.getPrice());
        assertEquals(2, entity.getQuantity());
        assertNull(entity.getManufacturer());
    }

    @Test
    void fromUpdateDto() {
        ItemUpdateDto dto = new ItemUpdateDto();
        dto.setName("X2"); dto.setPrice(20); dto.setQuantity(4); dto.setManufacturerId(5L);
        var entity = mapper.fromUpdateDto(dto);
        assertNull(entity.getId());
        assertEquals("X2", entity.getName());
        assertEquals(20, entity.getPrice());
        assertEquals(4, entity.getQuantity());
        assertNull(entity.getManufacturer());
    }
}
