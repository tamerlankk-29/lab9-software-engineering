package com.example.lab9.web.mapper;

import com.example.lab9.domain.Country;
import com.example.lab9.web.dto.CountryCreateDto;
import com.example.lab9.web.dto.CountryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDto toDto(Country entity);
    List<CountryDto> toDto(List<Country> entities);

    @Mapping(target = "id", ignore = true)
    Country fromCreateDto(CountryCreateDto dto);
}
