package be.brahms.rent_server.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "second_residences")
public class SecondResidence extends BaseEntity {
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "city", length = 100, nullable = false)
    private String city;
    @Column(name = "zip_code", length = 50, nullable = false)
    private String zipCode;

    // Relation ManyToOne
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}