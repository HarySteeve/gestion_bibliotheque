package mg.itu.spring.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "pret")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_adherant")
    private Adherant adherant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_exemplaire")
    private Exemplaire exemplaire;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_type_pret")
    private TypePret typePret;

    @Column(name = "date_pris", nullable = false)
    private LocalDate datePris;

    @Column(name = "date_rendu")
    private LocalDate dateRendu;

    @Column(name = "cause_penalite", columnDefinition = "TEXT")
    private String causePenalite;
}
