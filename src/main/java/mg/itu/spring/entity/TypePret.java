package mg.itu.spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "type_pret")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypePret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String libelle;
}
