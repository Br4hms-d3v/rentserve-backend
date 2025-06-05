package be.brahms.rent_server.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * This class represents a category of items and extends the BaseEntity class.
 * It includes a name for the category and relationships to favours and materials.
 */
@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Category extends BaseEntity {

    /**
     * The name of the category.
     * This value must be unique, cannot be null, and has a maximum length of 150 characters.
     */
    @Column(name = "name_category", length = 150, unique = true, nullable = false)
    private String nameCategory;

    // Constructor by default
    /**
     * Default constructor for Category.
     */
    public Category() {}

    /**
     * A set of favours associated with this category.
     * A category can have multiple favours.
     * The relationship is maintained through cascading persist and merge operations.
     */
    // Relation OneToMany
    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Favor> favours;

    /**
     * A set of materials associated with this category.
     * A category can have multiple materials.
     * The relationship is maintained through cascading persist and merge operations.
     */
    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Material> materials;
}
