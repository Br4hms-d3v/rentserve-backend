package be.brahms.rent_server.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "rentals")
public class Rental extends BaseEntity {
    @Column(name = "amount", precision = 7, scale = 2)
    private BigDecimal amount;
    @Column(name = "start_date_at")
    private LocalDate starDateAt;
    @Column(name = "end_date_at")
    private LocalDate endDateAt;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;

    // Relation ManyToOne
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_material_id")
    private UserMaterial userMaterial;

    @ManyToOne
    @JoinColumn(name = "user_favor_id")
    private UserFavor userFavor;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;
}