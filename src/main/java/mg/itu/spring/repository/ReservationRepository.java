package mg.itu.spring.repository;

import mg.itu.spring.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Integer countByAdherantId(Integer idAdherant);

    @Query(value = "SELECT COUNT(*) FROM reservation " +
                   "WHERE id_adherant = :idAdherant " +
                   "AND :date BETWEEN date_demande_reservation AND date_reservee", nativeQuery = true)
    Integer countReservationByDateInRange(@Param("idAdherant") Integer idAdherant,
                                          @Param("date") java.time.LocalDate date);

    @Query(value = "SELECT * FROM reservation WHERE id_exemplaire = :idExemplaire ORDER BY date_reservee DESC LIMIT 1", nativeQuery = true)
    Reservation findDerniereReservationByExemplaireId(@Param("idExemplaire") int idExemplaire);

}
