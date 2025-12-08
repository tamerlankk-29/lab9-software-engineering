package com.example.lab9.web.mapper;

import com.example.lab9.domain.Country;
import com.example.lab9.web.dto.CountryCreateDto;
import com.example.lab9.web.dto.CountryDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountryMapperTest {

    private final CountryMapper mapper = Mappers.getMapper(CountryMapper.class);

    @Test
    void entityToDto() {
        Country c = new Country();
        c.setId(1L);
        c.setName("Kazakhstan");
        c.setCode("KZ");

        CountryDto dto = mapper.toDto(c);
        assertEquals(1L, dto.getId());
        assertEquals("Kazakhstan", dto.getName());
        assertEquals("KZ", dto.getCode());
    }

    @Test
    void dtoList() {
        Country c1 = new Country(); c1.setId(1L); c1.setName("A"); c1.setCode("AA");
        Country c2 = new Country(); c2.setId(2L); c2.setName("B"); c2.setCode("BB");
        List<CountryDto> list = mapper.toDto(List.of(c1, c2));
        assertEquals(2, list.size());
        assertEquals(1L, list.get(0).getId());
        assertEquals("BB", list.get(1).getCode());
    }

    @Test
    void fromCreateDto() {
        CountryCreateDto dto = new CountryCreateDto();
        dto.setName("Test");
        dto.setCode("TT");
        Country c = mapper.fromCreateDto(dto);
        assertNull(c.getId());
        assertEquals("Test", c.getName());
        assertEquals("TT", c.getCode());
    }
}
