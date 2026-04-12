package com.example.gestionStock.service.impl;

import com.example.gestionStock.web.dto.requests.ProductRequestDto;
import com.example.gestionStock.web.dto.responses.ProductResponseDto;
import com.example.gestionStock.entity.Category;
import com.example.gestionStock.entity.Product;
import com.example.gestionStock.exception.CategoryNotFoundException;
import com.example.gestionStock.web.mappers.ProductMapper;
import com.example.gestionStock.repository.CategoryRepository;
import com.example.gestionStock.repository.ProductRepository;
import com.example.gestionStock.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto create(ProductRequestDto requestDto) {
        // Vérifier que la catégorie existe
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(requestDto.getCategoryId()));

        Product product = productMapper.dtoToModel(requestDto);
        product.setCategory(category); // Assigner l'objet Category complet

        Product savedProduct = productRepository.save(product);
        return productMapper.modelToDto(savedProduct);
    }

    @Override
    public ProductResponseDto update(Integer id, ProductRequestDto requestDto) {
        // Vérifier que le produit existe
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produit non trouvé avec l'ID : " + id));

        // Vérifier que la nouvelle catégorie existe
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(requestDto.getCategoryId()));

        product.setLibelle(requestDto.getLibelle());
        product.setRef(requestDto.getRef());
        product.setQuantityStock(requestDto.getQuantityStock());
        product.setQuantity(requestDto.getQuantity());
        product.setPrix(requestDto.getPrix());
        product.setUnit(requestDto.getUnit());
        product.setStatus(requestDto.getStatus());
        product.setCategory(category); // Mettre à jour la catégorie

        Product updatedProduct = productRepository.save(product);
        return productMapper.modelToDto(updatedProduct);
    }

    @Override
    public ProductResponseDto findById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produit non trouvé avec l'ID : " + id));
        return productMapper.modelToDto(product);
    }

    @Override
    public Page<ProductResponseDto> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::modelToDto);
    }

    @Override
    public void delete(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("Produit non trouvé avec l'ID : " + id);
        }
        productRepository.deleteById(id);
    }
}
