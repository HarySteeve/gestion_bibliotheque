package mg.itu.spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profil")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(name = "nb_livre_max", nullable = false)
    private int nbLivreMax;

    @Column(name = "duree_pret", nullable = false)
    private int dureePret;

    @Column(name = "nb_prolongement", nullable = false)
    private int nbProlongement;

    @Column(name = "duree_prolongement", nullable = false)
    private int dureeProlongement;

    @Column(name = "nb_jour_penalite", nullable = false)
    private int nbJourPenalite = 1;

    @Column(name = "nb_reservation_max", nullable = false)
    private int nbReservationMax;

    @Column(name = "duree_jour_reservation_max", nullable = false)
    private int dureeJourReservationMax;
}
