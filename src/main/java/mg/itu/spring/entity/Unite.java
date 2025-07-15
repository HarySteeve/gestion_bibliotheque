package mg.itu.spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unite")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Unite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String libelle;
}
