package mg.itu.spring.service;

import mg.itu.spring.entity.Livre;
import mg.itu.spring.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {
    private final LivreRepository repo;

    public LivreService(LivreRepository repo) {
        this.repo = repo;
    }

    public List<Livre> findAll() {
        return repo.findAll();
    }

    public Optional<Livre> findById(Integer id) {
        return repo.findById(id);
    }

    public void save(Livre livre) {
        repo.save(livre);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
