package mg.itu.spring.repository;

import mg.itu.spring.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre, Integer> {}
