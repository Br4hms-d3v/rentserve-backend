package be.brahms.rent_server.models.entities;

import be.brahms.rent_server.enums.State;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a material (item or resource) and extends the BaseEntity class.
 * It includes the material's name, description, price, state, availability, and relationships to users and categories.
 */
@Entity
@Table(name = "materials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Material extends BaseEntity {

    /**
     * The name of the material.
     * This value cannot be null.
     */
    @Column(name = "name_material", nullable = false)
    private String nameMaterial;
    /**
     * A description of the material.
     * Stored as text, allowing for longer descriptions.
     */
    @Column(name = "description_material", columnDefinition = "TEXT")
    private String descriptionMaterial;
    /**
     * The price per hour for using the material.
     * Stored as a decimal with a precision of 7 and a scale of 2.
     * This value cannot be null.
     */
    @Column(name = "price_hour_material", nullable = false, precision = 7, scale = 2)
    private BigDecimal priceHourMaterial;
    /**
     * The state of the material (e.g., GOOD, DAMAGED).
     * Stored as a string using the State enum.
     */
    @Column(name = "state_material")
    @Enumerated(EnumType.STRING)
    private State stateMaterial;
    /**
     * A flag indicating whether the material is available or not.
     * The default value is false (unavailable) if not set.
     */
    @Column(name = "is_available")
    private boolean isAvailable;

    /**
     * A set of user-material relationships.
     * This represents which users have used this material.
     * A material can be linked to multiple users.
     */
    // Relation OneToMany
    @OneToMany(mappedBy = "material")
    private Set<UserMaterial> userMaterials = new HashSet<>();

    /**
     * The category to which this material belongs.
     * A material is associated with one category.
     */
    // Relation ManyToOne
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}