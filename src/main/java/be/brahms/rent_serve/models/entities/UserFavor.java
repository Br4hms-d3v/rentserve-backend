package be.brahms.rent_serve.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the link between a user and a favor.
 * It extends the BaseEntity class and includes relationships to pictures, rentals, and reviews.
 */
@Entity
@Table(name = "user_favour")
@Getter
@Setter
@AllArgsConstructor
public class UserFavor extends BaseEntity {

    // Relation ManyToOne
    /**
     * The user who owns or offers the favor.
     * This field cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    /**
     * The favor associated with the user.
     * This field cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "favor_id", nullable = false)
    private Favor favor;

    // Constructor by default
    /**
     * Default constructor for UserFavor.
     */
    public UserFavor() { }

    // Relation OneToMany
    /**
     * A set of pictures related to this user-favor.
     * These pictures are linked with cascade operations for persist and merge.
     */
    @OneToMany(mappedBy = "userFavor", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Picture> pictures = new HashSet<>();

    /**
     * A set of rentals involving this user-favor.
     * Allows tracking how the favor has been rented.
     */
    @OneToMany(mappedBy = "userFavor", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Rental> rentals = new HashSet<>();

    /**
     * A set of reviews related to this user-favor.
     * Represents feedback left by other users.
     */
    @OneToMany(mappedBy = "userFavor", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Review> reviews = new HashSet<>();
}