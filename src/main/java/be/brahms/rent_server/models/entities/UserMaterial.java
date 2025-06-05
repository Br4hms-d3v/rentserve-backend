package be.brahms.rent_server.models.entities;

import jakarta.persistence.*;
import lombok.*;

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
    public UserMaterial() { }

    // Relation OneToMany
    /**
     * A set of pictures related to this user-material.
     * These pictures are linked with cascade operations for persist and merge.
     */
    @OneToMany(mappedBy = "userMaterial", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Picture> pictures = new HashSet<>();

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
}