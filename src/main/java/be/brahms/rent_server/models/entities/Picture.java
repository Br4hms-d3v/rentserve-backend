package be.brahms.rent_server.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pictures")
public class Picture extends BaseEntity {
    @Column(name = "name_picture", nullable = false)
    private String namePicture;

    // Relation ManyToOne
    @ManyToOne
    @JoinColumn(name = "user_material_id", nullable = false)
    private UserMaterial userMaterial;

    @ManyToOne
    @JoinColumn(name = "user_favor_id", nullable = false)
    private UserFavor userFavor;
}