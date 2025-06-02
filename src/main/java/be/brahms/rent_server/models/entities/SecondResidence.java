package be.brahms.rent_server.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "second_residences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SecondResidence extends BaseEntity {
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "city", length = 100, nullable = false)
    private String city;
    @Column(name = "zip_code", length = 50, nullable = false)
    private String zipCode;

    // Relation ManyToOne
    @OneToMany(mappedBy = "")
    private Set<User> users = new HashSet<>();
}