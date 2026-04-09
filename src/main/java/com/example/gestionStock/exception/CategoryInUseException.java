package com.example.gestionStock.exception;

/**
 * Exception lancée lorsqu'une tentative de suppression d'une catégorie échoue
 * parce qu'elle est encore référencée par un ou plusieurs produits.
 * Vise à maintenir l'intégrité référentielle de la base de données.
 */
public class CategoryInUseException extends RuntimeException {

    /**
     * Construit une nouvelle exception avec un message détaillant l'ID de la catégorie concernée.
     * @param id L'identifiant de la catégorie qui ne peut pas être supprimée.
     */
    public CategoryInUseException(Integer id) {
        super("Impossible de supprimer la catégorie avec l'ID " + id + " car elle est toujours associée à des produits.");
    }
}
