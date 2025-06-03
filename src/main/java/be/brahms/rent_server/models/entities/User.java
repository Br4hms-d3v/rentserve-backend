package be.brahms.rent_server.models.entities;

import be.brahms.rent_server.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseEntity {
    @Column(name = "name", length = 200, nullable = false)
    private String name;
    @Column(name = "first_name", length = 150, nullable = false)
    private String firstName;
    @Column(name = "birthdate", nullable = false)
    private Date birthdate;
    @Column(name = "pseudo", nullable = false)
    private String pseudo;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "city", length = 100, nullable = false)
    private String city;
    @Column(name = "zip_code", length = 60, nullable = false)
    private String zipCode;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    // OneToMany
    @OneToMany(mappedBy = "user")
    private Set<SecondResidence> secondResidences;

    @OneToMany(mappedBy = "user")
    private Set<UserMaterial> userMaterials = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserFavor> userFavours = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Rental> rentals = new HashSet<>();
}