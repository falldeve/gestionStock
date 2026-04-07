package com.example.gestionStock.web.dto.responses;

import com.example.gestionStock.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {
    private Integer id;
    private String libelle;
    private Status status;
}
