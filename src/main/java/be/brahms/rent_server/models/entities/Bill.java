package be.brahms.rent_server.models.entities;

import be.brahms.rent_server.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "bills")
public class Bill extends BaseEntity {
    @Column(name = "amount", precision = 7, scale = 2, nullable = false)
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name = "is_paid")
    private Boolean isPaid;

    // Relation OneToMany
    @OneToMany(mappedBy = "bill")
    private Set<Rental> rentals;

    // Relation ManyToOne
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
