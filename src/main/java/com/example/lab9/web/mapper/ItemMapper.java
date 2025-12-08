package com.example.lab9.web.mapper;

import com.example.lab9.domain.Country;
import com.example.lab9.domain.Item;
import com.example.lab9.web.dto.ItemCreateDto;
import com.example.lab9.web.dto.ItemDto;
import com.example.lab9.web.dto.ItemUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    @Mapping(target = "manufacturerId", source = "manufacturer", qualifiedByName = "countryToId")
    ItemDto toDto(Item entity);
    List<ItemDto> toDto(List<Item> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "manufacturer", ignore = true)
    Item fromCreateDto(ItemCreateDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "manufacturer", ignore = true)
    Item fromUpdateDto(ItemUpdateDto dto);

    @Named("countryToId")
    default Long mapCountryToId(Country c) {
        return c != null ? c.getId() : null;
    }
}
