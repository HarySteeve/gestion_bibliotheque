package mg.itu.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "exemplaire")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    @Column(nullable = false)
    private int numero;

    @Column(unique = true)
    private String ref;

    @Column(name = "date_indispo")
    private LocalDate dateIndispo;
}
