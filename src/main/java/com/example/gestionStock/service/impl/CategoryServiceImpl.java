package com.example.gestionStock.service.impl;

import com.example.gestionStock.web.dto.requests.CategoryRequestDto;
import com.example.gestionStock.web.dto.responses.CategoryResponseDto;
import com.example.gestionStock.entity.Category;
import com.example.gestionStock.exception.CategoryAlreadyExistsException;
import com.example.gestionStock.exception.CategoryNotFoundException;
import com.example.gestionStock.web.mappers.CategoryMapper;
import com.example.gestionStock.repository.CategoryRepository;
import com.example.gestionStock.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto create(CategoryRequestDto requestDto) {
        if (categoryRepository.findByLibelle(requestDto.getLibelle()).isPresent()) {
            throw new CategoryAlreadyExistsException(requestDto.getLibelle());
        }
        Category category = categoryMapper.dtoToModel(requestDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.modelToDto(savedCategory);
    }

    @Override
    public CategoryResponseDto update(Integer id, CategoryRequestDto requestDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        // Vérifier si le nouveau libellé existe déjà pour une autre catégorie
        categoryRepository.findByLibelle(requestDto.getLibelle())
                .ifPresent(existingCategory -> {
                    if (!existingCategory.getId().equals(id)) {
                        throw new CategoryAlreadyExistsException(requestDto.getLibelle());
                    }
                });

        category.setLibelle(requestDto.getLibelle());
        category.setStatus(requestDto.getStatus());
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.modelToDto(updatedCategory);
    }

    @Override
    public CategoryResponseDto findById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryMapper.modelToDto(category);
    }

    @Override
    public Page<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::modelToDto);
    }

    @Override
    public void delete(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
       
        categoryRepository.deleteById(id);
    }
}
