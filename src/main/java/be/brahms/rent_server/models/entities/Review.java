package be.brahms.rent_server.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "reviews")
public class Review extends BaseEntity {
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "is_active")
    private Boolean isActive;

    // Relation ManyToOne
    @ManyToOne
    @JoinColumn(name = "user_material_id")
    private UserMaterial userMaterial;

    @ManyToOne
    @JoinColumn(name = "user_Favor_id")
    private UserFavor userFavor;
}