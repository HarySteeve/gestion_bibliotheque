package mg.itu.spring.repository;

import mg.itu.spring.entity.DemandeProlongement;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DemandeProlongementRepository extends JpaRepository<DemandeProlongement, Integer> {

    int countByPretId(Integer idPret);
    
    Optional<DemandeProlongement> findTopByPretIdOrderByFinProlongementDesc(Integer idPret);

}
