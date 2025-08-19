package be.brahms.rent_serve.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a favor (service or item) and extends the BaseEntity class.
 * It includes the favor's name, description, price, and availability, along with its relationships to users and categories.
 */
@Entity
@Table(name = "favour")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Favor extends BaseEntity {

    /**
     * The name of the favor.
     * This value cannot be null.
     */
    @Column(name = "name_favor", nullable = false)
    private String nameFavor;

    /**
     * A flag indicating whether the favor is available or not.
     * The default value is false (unavailable) if not set.
     */
    @Column(name = "is_available")
    private boolean isAvailable;

    // Constructor by default
    /**
     * Default constructor for Favor.
     */
    public Favor() {}

    /**
     * A set of user-favor relationships.
     * This represents which users have chosen this favor.
     * A favor can be linked to multiple users.
     */
    // Relation OneToMany
    @OneToMany(mappedBy = "favor")
    private Set<UserFavor> userFavour = new HashSet<>();

    /**
     * The category to which this favor belongs.
     * A favor is associated with one category.
     */
    // Relation ManyToOne
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
