package mg.itu.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "demande_prolongement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandeProlongement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pret", nullable = false)
    private Pret pret;

    @Column(name = "date_demande", nullable = false)
    private LocalDate dateDemande;

    @Column(name = "debut_prolongement", nullable = false)
    private LocalDate debutProlongement;

    @Column(name = "fin_prolongement", nullable = false)
    private LocalDate finProlongement;
}
