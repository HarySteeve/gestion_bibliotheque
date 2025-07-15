package mg.itu.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reabonnement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reabonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_adherant", referencedColumnName = "id")
    private Adherant adherant;

    @Column(name = "montant_paye", nullable = false)
    private BigDecimal montantPaye;

    @Column(name = "date_reabonnement", nullable = false)
    private LocalDate dateReabonnement;

    @Column(name = "date_debut_abonnement", nullable = false)
    private LocalDate dateDebutAbonnement;

    @Column(name = "date_fin_abonnement", nullable = false)
    private LocalDate dateFinAbonnement;
}
