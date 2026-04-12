package com.example.gestionStock.service;

import com.example.gestionStock.web.dto.requests.ProductRequestDto;
import com.example.gestionStock.web.dto.responses.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductResponseDto create(ProductRequestDto requestDto);

    ProductResponseDto update(Integer id, ProductRequestDto requestDto);

    ProductResponseDto findById(Integer id);

    Page<ProductResponseDto> findAll(Pageable pageable);

    void delete(Integer id);
}
