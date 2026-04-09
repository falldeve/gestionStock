package com.example.gestionStock.exception;

/**
 * Exception lancée lorsqu'une opération tente d'accéder à une catégorie
 * qui n'existe pas dans la base de données.
 * Typiquement utilisée dans les cas où une recherche par ID ne retourne aucun résultat.
 */
public class CategoryNotFoundException extends RuntimeException {

    /**
     * Construit une nouvelle exception avec un message détaillant l'ID de la catégorie non trouvée.
     * @param id L'identifiant de la catégorie qui n'a pas pu être trouvée.
     */
    public CategoryNotFoundException(Integer id) {
        super("La catégorie avec l'ID " + id + " n'a pas été trouvée.");
    }
}
