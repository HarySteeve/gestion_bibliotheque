package mg.itu.spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accessibilite_livre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessibiliteLivre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_livre", referencedColumnName = "id")
    private Livre livre;

    @ManyToOne
    @JoinColumn(name = "id_profil", referencedColumnName = "id")
    private Profil profil;

    @ManyToOne
    @JoinColumn(name = "id_type_pret", referencedColumnName = "id")
    private TypePret typePret;

    private Integer ageMin;

    private Integer ageMax;
}
