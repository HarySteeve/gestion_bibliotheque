package mg.itu.spring.repository;

import mg.itu.spring.entity.*;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccessibiliteLivreRepository extends JpaRepository<AccessibiliteLivre, Integer> {
    AccessibiliteLivre findByLivreId(int livreId);
}