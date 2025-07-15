package mg.itu.spring.service;

import mg.itu.spring.entity.AccessibiliteLivre;
import mg.itu.spring.repository.AccessibiliteLivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessibiliteLivreService {

    @Autowired
    private AccessibiliteLivreRepository repository;

    public List<AccessibiliteLivre> getAll() {
        return repository.findAll();
    }

    public AccessibiliteLivre findById(int id) {
        return repository.findById(id).orElse(null);
    }

    public AccessibiliteLivre save(AccessibiliteLivre accessibiliteLivre) {
        return repository.save(accessibiliteLivre);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public AccessibiliteLivre findByLivreId(int livreId) {
        return repository.findByLivreId(livreId);
    }
}
