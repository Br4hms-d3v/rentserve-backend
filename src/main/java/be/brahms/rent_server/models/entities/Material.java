package be.brahms.rent_server.models.entities;

import be.brahms.rent_server.enums.State;
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
@Table(name = "materials")
public class Material extends BaseEntity {
    @Column(name = "name_material", nullable = false)
    private String nameMaterial;
    @Column(name = "description_material", columnDefinition = "TEXT")
    private String descriptionMaterial;
    @Column(name = "price_hour_material", nullable = false, precision = 7, scale = 2)
    private BigDecimal priceHourMaterial;
    @Column(name = "state_material")
    @Enumerated(EnumType.STRING)
    private State stateMaterial;
    @Column(name = "is_available")
    private boolean isAvailable;

    // Relation OneToMany
    @OneToMany(mappedBy = "material")
    private Set<UserMaterial> userMaterials = new HashSet<>();

    // Relation ManyToOne
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
