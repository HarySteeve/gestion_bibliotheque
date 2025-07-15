package mg.itu.spring.repository;

import mg.itu.spring.entity.FraisReabonnement;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FraisReabonnementRepository extends JpaRepository<FraisReabonnement, Integer> {
  
    @Query("SELECT COALESCE(SUM(f.montant), 0) FROM FraisReabonnement f WHERE f.profil.id = :idProfil AND f.date <= :date")
    BigDecimal findMontantTotalByProfilAndDate(@Param("idProfil") Integer idProfil, @Param("date") LocalDate date);

}
