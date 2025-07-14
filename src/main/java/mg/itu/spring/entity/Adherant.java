package mg.itu.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "adherant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adherant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_profil", referencedColumnName = "id")
    private Profil profil;

    @Column(length = 100, nullable = false)
    private String nom;

    @Column(length = 100, nullable = false)
    private String prenom;

    @Column(nullable = false)
    private LocalDate dtn;

    @Column(length = 200, nullable = false, unique = true)
    private String email;

    @Column(length = 255, nullable = false)
    private String mdp;
}
