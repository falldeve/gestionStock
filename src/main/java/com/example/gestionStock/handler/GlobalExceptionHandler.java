package com.example.gestionStock.handler;

import com.example.gestionStock.exception.CategoryAlreadyExistsException;
import com.example.gestionStock.exception.CategoryInUseException;
import com.example.gestionStock.exception.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère l'exception {@link CategoryNotFoundException}.
     * Est déclenché lorsqu'une catégorie demandée n'est pas trouvée.
     *
     * @param ex L'exception {@link CategoryNotFoundException} interceptée.
     * @param request L'objet de la requête web actuelle.
     * @return Un {@link ResponseEntity} contenant les détails de l'erreur et le statut HTTP 404 (NOT_FOUND).
     */
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Gère l'exception {@link CategoryAlreadyExistsException}.
     * Est déclenché lors de la tentative de création d'une catégorie avec un libellé déjà existant.
     *
     * @param ex L'exception {@link CategoryAlreadyExistsException} interceptée.
     * @param request L'objet de la requête web actuelle.
     * @return Un {@link ResponseEntity} contenant les détails de l'erreur et le statut HTTP 409 (CONFLICT).
     */
    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Object> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    /**
     * Gère l'exception {@link CategoryInUseException}.
     * Est déclenché lors de la tentative de suppression d'une catégorie encore associée à des produits.
     *
     * @param ex L'exception {@link CategoryInUseException} interceptée.
     * @param request L'objet de la requête web actuelle.
     * @return Un {@link ResponseEntity} contenant les détails de l'erreur et le statut HTTP 409 (CONFLICT).
     */
    @ExceptionHandler(CategoryInUseException.class)
    public ResponseEntity<Object> handleCategoryInUseException(CategoryInUseException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}
