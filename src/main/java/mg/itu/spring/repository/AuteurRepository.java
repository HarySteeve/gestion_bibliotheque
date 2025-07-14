package mg.itu.spring.repository;

import mg.itu.spring.entity.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository<Auteur, Integer> {}
