package com.example.gestionStock.web.controllers;

import com.example.gestionStock.web.dto.requests.CategoryRequestDto;
import com.example.gestionStock.web.dto.responses.CategoryResponseDto;
import com.example.gestionStock.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "API pour la gestion des catégories")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Créer une nouvelle catégorie", description = "Crée une catégorie et la retourne.")
    @ApiResponse(responseCode = "201", description = "Catégorie créée avec succès")
    @ApiResponse(responseCode = "409", description = "Une catégorie avec ce libellé existe déjà")
    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto requestDto) {
        CategoryResponseDto createdCategory = categoryService.create(requestDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @Operation(summary = "Lister toutes les catégories", description = "Retourne une liste paginée de toutes les catégories.")
    @GetMapping
    public ResponseEntity<Page<CategoryResponseDto>> getAllCategories(Pageable pageable) {
        Page<CategoryResponseDto> categories = categoryService.findAll(pageable);
        return ResponseEntity.ok(categories);
    }

    @Operation(summary = "Trouver une catégorie par son ID", description = "Retourne les détails d'une catégorie spécifique.")
    @ApiResponse(responseCode = "200", description = "Catégorie trouvée")
    @ApiResponse(responseCode = "404", description = "Catégorie non trouvée")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Integer id) {
        CategoryResponseDto category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @Operation(summary = "Mettre à jour une catégorie", description = "Met à jour les détails d'une catégorie existante.")
    @ApiResponse(responseCode = "200", description = "Catégorie mise à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Catégorie non trouvée")
    @ApiResponse(responseCode = "409", description = "Le nouveau libellé est déjà utilisé par une autre catégorie")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Integer id, @RequestBody CategoryRequestDto requestDto) {
        CategoryResponseDto updatedCategory = categoryService.update(id, requestDto);
        return ResponseEntity.ok(updatedCategory);
    }

    @Operation(summary = "Supprimer une catégorie", description = "Supprime une catégorie par son ID.")
    @ApiResponse(responseCode = "204", description = "Catégorie supprimée avec succès")
    @ApiResponse(responseCode = "404", description = "Catégorie non trouvée")
    @ApiResponse(responseCode = "409", description = "La catégorie est en cours d'utilisation et ne peut pas être supprimée")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
