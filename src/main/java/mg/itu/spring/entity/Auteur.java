package mg.itu.spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "auteur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;
}
