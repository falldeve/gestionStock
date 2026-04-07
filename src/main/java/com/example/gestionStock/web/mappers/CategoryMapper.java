package com.example.gestionStock.web.mappers;

import com.example.gestionStock.web.dto.requests.CategoryRequestDto;
import com.example.gestionStock.web.dto.responses.CategoryResponseDto;
import com.example.gestionStock.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponseDto modelToDto(Category category);

    Category dtoToModel(CategoryRequestDto categoryRequestDto);
}
