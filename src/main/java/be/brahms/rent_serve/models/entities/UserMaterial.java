package be.brahms.rent_serve.models.entities;

import be.brahms.rent_serve.enums.State;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the link between a user and a material.
 * It extends the BaseEntity class and includes relationships to pictures, rentals, and reviews.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "user_materials")
public class UserMaterial extends BaseEntity {

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

    // Relation ManyToOne
    /**
     * The user who owns or offers the material.
     * This field cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    /**
     * The material associated with the user.
     * This field cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    // Constructor by default

    /**
     * Default constructor for UserMaterial.
     */
    public UserMaterial() {
    }

    // Relation OneToMany
    /**
     * A set of rentals involving this user-material.
     * Allows tracking how the material has been rented.
     */
    @OneToMany(mappedBy = "userMaterial", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Rental> rentals = new HashSet<>();

    /**
     * A set of reviews related to this user-material.
     * Represents feedback left by other users.
     */
    @OneToMany(mappedBy = "userMaterial", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Review> reviews = new HashSet<>();

    // Relation ManyToMany
    @ManyToMany
    @JoinTable(
            name = "picture_user_materials",
            joinColumns = @JoinColumn(name = "user_material_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id")
    )
    private Set<Picture> pictures = new HashSet<>();
}