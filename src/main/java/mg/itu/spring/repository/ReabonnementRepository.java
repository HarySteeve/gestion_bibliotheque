package mg.itu.spring.repository;

import mg.itu.spring.entity.Reabonnement;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReabonnementRepository extends JpaRepository<Reabonnement, Integer> {
    @Query("SELECT r FROM Reabonnement r WHERE r.adherant.id = :idAdherant ORDER BY r.dateFinAbonnement DESC LIMIT 1")
    Optional<Reabonnement> findDernierReabonnementByAdherant(@Param("idAdherant") Integer idAdherant);

}
    