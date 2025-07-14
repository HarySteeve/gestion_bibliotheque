package mg.itu.spring.repository;

import mg.itu.spring.entity.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BibliothecaireRepository extends JpaRepository<Bibliothecaire, Integer> {
    Bibliothecaire findByUsername(String userName);
    Optional<Bibliothecaire> findByEmailAndMdp(String email, String mdp);
    List<Bibliothecaire> findByDateRenvoieIsNull();
    List<Bibliothecaire> findByDateRenvoieIsNotNull();
}