package mg.itu.spring.repository;

import mg.itu.spring.entity.DureeAbonnement;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DureeAbonnementRepository extends JpaRepository<DureeAbonnement, Integer> {
    
    @Query("SELECT d FROM DureeAbonnement d WHERE d.date <= :date ORDER BY d.date DESC LIMIT 1")
    Optional<DureeAbonnement> findDerniereDuree(@Param("date") LocalDate date);

}
