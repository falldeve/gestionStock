package com.example.gestionStock.exception;

/**
 * Exception lancée lors de la tentative de création d'une nouvelle catégorie
 * avec un libellé qui est déjà utilisé par une autre catégorie existante.
 * Permet d'assurer l'unicité des libellés de catégories.
 */
public class CategoryAlreadyExistsException extends RuntimeException {

    /**
     * Construit une nouvelle exception avec un message détaillant le libellé dupliqué.
     * @param libelle Le libellé qui existe déjà dans la base de données.
     */
    public CategoryAlreadyExistsException(String libelle) {
        super("Une catégorie avec le libellé '" + libelle + "' existe déjà.");
    }
}
