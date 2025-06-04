package be.brahms.rent_server.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_favour")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFavor extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "favor_id", nullable = false)
    private Favor favor;

    // Relation OneToMany
    @OneToMany(mappedBy = "userFavor", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Picture> pictures = new HashSet<>();

    @OneToMany(mappedBy = "userFavor", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Rental> rentals = new HashSet<>();

    @OneToMany(mappedBy = "userFavor", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Review> reviews = new HashSet<>();
}