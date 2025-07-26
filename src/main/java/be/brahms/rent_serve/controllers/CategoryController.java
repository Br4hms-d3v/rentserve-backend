package be.brahms.rent_serve.controllers;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller manages categories
 * Display the list of categories
 */
@RestController
@RequestMapping("/api/categorie")
public class CategoriesController {

    /**
     * Get all categories from the database
     * This method returns a list of all categories
     * Each category is converted to a CategoryDto
     * Each categoryDto is wrapped inside an EntityModel with HATEOAS links.
     *
     * @return ResponseEntity with a list of category model
     * */
    public ResponseEntity<List<EntityModel<>>> getCategories {
    }

    /**
     * Create a new category
     * */
//    public ResponseEntity<> {
//    }

    /**
     * Edit category
     * */
//    public ResponseEntity<> {
//    }

    /**
     * Delete category
     * */
//    public ResponseEntity<> {
//    }

    /**
     * Select all categories by material
     * */
//    public ResponseEntity<> {
//    }

    /**
     * Select all categories by favor
     * */
//    public ResponseEntity<> {
//    }

    /**
     * Search categories for material
     * */
//    public ResponseEntity<> {
//    }

    /**
     * Search categories for favor
     * */
//    public ResponseEntity<> {
//    }

}
