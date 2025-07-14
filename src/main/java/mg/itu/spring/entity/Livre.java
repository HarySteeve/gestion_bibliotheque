package mg.itu.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "livre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titre;

    private String ibn;

    @Column(nullable = false)
    private LocalDate edition;

    private String tag;

    @ManyToOne
    @JoinColumn(name = "id_auteur", nullable = false)
    private Auteur auteur;
}
