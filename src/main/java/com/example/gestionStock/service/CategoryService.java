package com.example.gestionStock.service;

import com.example.gestionStock.web.dto.requests.CategoryRequestDto;
import com.example.gestionStock.web.dto.responses.CategoryResponseDto ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {


    CategoryResponseDto create(CategoryRequestDto requestDto);


    CategoryResponseDto update(Integer id, CategoryRequestDto requestDto);


    CategoryResponseDto findById(Integer id);


    Page<CategoryResponseDto> findAll(Pageable pageable);


    void delete(Integer id);
}
