package mg.itu.spring.repository;

import mg.itu.spring.entity.TypePret;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePretRepository extends JpaRepository<TypePret, Integer> {

}
