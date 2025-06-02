package be.brahms.rent_server.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_materials")
public class UserMaterial extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    // Relation OneToMany
    @OneToMany(mappedBy = "userMaterial")
    private Set<Picture> pictures = new HashSet<>();
}
