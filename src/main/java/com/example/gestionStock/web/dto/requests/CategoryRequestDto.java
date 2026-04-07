package com.example.gestionStock.web.dto.requests;

import com.example.gestionStock.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {
    private String libelle;
    private Status status;
}
