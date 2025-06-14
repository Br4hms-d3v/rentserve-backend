package be.brahms.rent_serve.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a picture and extends the BaseEntity class.
 * It includes the picture's name and its relationships to user-material and user-favor.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "pictures")
public class Picture extends BaseEntity {

    /**
     * The name of the picture.
     * This value cannot be null.
     */
    @Column(name = "name_picture", nullable = false)
    private String namePicture;

    // Constructor by default
    /**
     * Default constructor for Picture.
     */
    public Picture() {}

    // Relation ManyToOne
    /**
     * The user-material relationship to which this picture belongs.
     * A picture is associated with one user-material.
     */
    @ManyToOne
    @JoinColumn(name = "user_material_id", nullable = false)
    private UserMaterial userMaterial;

    /**
     * The user-favor relationship to which this picture belongs.
     * A picture is associated with one user-favor.
     */
    @ManyToOne
    @JoinColumn(name = "user_favor_id", nullable = false)
    private UserFavor userFavor;
}