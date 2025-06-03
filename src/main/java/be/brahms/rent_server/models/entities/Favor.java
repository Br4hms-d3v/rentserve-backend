package be.brahms.rent_server.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "favour")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Favor extends BaseEntity {
    @Column(name = "name_favor", nullable = false)
    private String nameFavor;
    @Column(name = "description_favor", columnDefinition = "TEXT")
    private String descriptionFavor;
    @Column(name = "price_hour_favor", nullable = false, precision = 7, scale = 2)
    private BigDecimal priceHourFavor;
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
