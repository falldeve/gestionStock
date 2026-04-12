package com.example.gestionStock.web.dto.responses;

import com.example.gestionStock.enums.Status;
import com.example.gestionStock.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Integer id;
    private String libelle;
    private String ref;
    private int quantityStock;
    private int quantity;
    private BigDecimal prix;
    private Unit unit;
    private Status status;
    private CategoryResponseDto category; // Ajout des détails de la catégorie
}
