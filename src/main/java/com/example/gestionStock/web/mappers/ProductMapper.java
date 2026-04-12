package com.example.gestionStock.web.mappers;

import com.example.gestionStock.web.dto.requests.ProductRequestDto;
import com.example.gestionStock.web.dto.responses.ProductResponseDto;
import com.example.gestionStock.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDto modelToDto(Product product);

    Product dtoToModel(ProductRequestDto productRequestDto);
}
