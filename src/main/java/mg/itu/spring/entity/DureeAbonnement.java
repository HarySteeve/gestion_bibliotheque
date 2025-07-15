package mg.itu.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "duree_abonnement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DureeAbonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer duree;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_unite", referencedColumnName = "id")
    private Unite unite;

    @Column(nullable = false)
    private LocalDate date;
}
