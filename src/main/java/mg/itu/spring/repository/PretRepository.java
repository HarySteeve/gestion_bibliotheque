package mg.itu.spring.repository;

import mg.itu.spring.entity.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PretRepository extends JpaRepository<Pret, Integer> {
    List<Pret> findByAdherantIdAndDateRenduIsNull(Integer idAdherant); 

    List<Pret> findByDateRenduIsNull();

    List<Pret> findByDateRenduIsNotNull();

    int countByAdherantId(Integer idAdherant);

    @Query("SELECT COUNT(p) FROM Pret p WHERE p.exemplaire.id = :idExemplaire AND :date BETWEEN p.datePris AND p.dateRendu")
    long countByExemplaireIdAndDateInRange(@Param("idExemplaire") Integer idExemplaire,
                                        @Param("date") LocalDate date);

    int countByAdherantIdAndDateRenduIsNull(Integer idAdherant);

    @Query(value = "SELECT * FROM pret WHERE id_exemplaire = :idExemplaire ORDER BY date_pris DESC LIMIT 1", nativeQuery = true)
    Pret findDernierPretByExemplaireId(@Param("idExemplaire") int idExemplaire);

    Pret findTopByAdherantIdOrderByDatePrisDesc(int idAdherant);

}