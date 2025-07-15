package mg.itu.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mg.itu.spring.entity.Penalite;

import java.time.LocalDate;
import java.util.List;

public interface PenaliteRepository extends JpaRepository<Penalite, Integer> {
    List<Penalite> findByAdherantId(Integer adherantId);
    // Donne-moi la première pénalité d’un adhérant, triée par date de fin décroissante (la plus récente)
    Penalite findTopByAdherantIdOrderByDateFinDesc(Integer adherantId);
    @Query("SELECT COUNT(p) > 0 FROM Penalite p WHERE p.adherant.id = :idAdherant AND :date BETWEEN p.dateDebut AND p.dateFin")
    boolean existsByAdherantIdAndDate(@Param("idAdherant") Integer idAdherant, @Param("date") LocalDate date);

}
