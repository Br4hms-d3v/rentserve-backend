package be.brahms.rent_server.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "categories")
public class Category extends BaseEntity {
    @Column(name = "name_category", length = 150, unique = true, nullable = false)
    private String nameCategory;

    // Relation OneToMany
    @OneToMany(mappedBy = "category")
    private Set<Favor> favours;

    @OneToMany(mappedBy = "category")
    private Set<Material> materials;
}
