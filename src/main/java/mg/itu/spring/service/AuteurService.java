package mg.itu.spring.service;

import mg.itu.spring.entity.Auteur;
import mg.itu.spring.repository.AuteurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuteurService {
    private final AuteurRepository repo;

    public AuteurService(AuteurRepository repo) {
        this.repo = repo;
    }

    public List<Auteur> findAll() {
        return repo.findAll();
    }
}
