package be.brahms.rent_server.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "favours")
public class Favor extends BaseEntity {
    @Column(name = "name_favour", nullable = false)
    private String nameFavour;
    @Column(name = "description_favour", columnDefinition = "TEXT")
    private String descriptionFavour;
    @Column(name = "price_hour_favour", nullable = false, precision = 7, scale = 2)
    private BigDecimal priceHourFavour;
    @Column(name = "is_available")
    private boolean isAvailable;

    // Relation OneToMany
    @OneToMany(mappedBy = "favor")
    private Set<UserFavor> userFavour = new HashSet<>();

    // Relation ManyToOne
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
