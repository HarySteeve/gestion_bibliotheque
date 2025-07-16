package mg.itu.spring.repository;

import mg.itu.spring.entity.Exemplaire;
import mg.itu.spring.entity.Livre;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
    @Query("SELECT COALESCE(MAX(e.numero), 0) FROM Exemplaire e WHERE e.livre = :livre")
    int findMaxNumeroByLivre(Livre livre);

    Exemplaire findByNumero(int numero);

    List<Exemplaire> findByLivreId(int idLivre);

    Exemplaire findByRef(String ref);
}
