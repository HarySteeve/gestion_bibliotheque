package mg.itu.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "frais_reabonnement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FraisReabonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_profil", referencedColumnName = "id")
    private Profil profil;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate date;
}
