package mg.itu.spring.repository;

import mg.itu.spring.entity.Adherant;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdherantRepository extends JpaRepository<Adherant, Integer> {
    Optional<Adherant> findByEmailAndMdp(String email, String mdp);
}
