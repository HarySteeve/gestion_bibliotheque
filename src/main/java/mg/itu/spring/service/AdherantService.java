package mg.itu.spring.service;

import mg.itu.spring.entity.Adherant;
import mg.itu.spring.repository.AdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdherantService {

    @Autowired
    private AdherantRepository repo;

    public List<Adherant> findAll() {
        return repo.findAll();
    }

    public Optional<Adherant> findById(Integer id) {
        return repo.findById(id);
    }

    public void save(Adherant adherant) {
        repo.save(adherant);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public Optional<Adherant> login(String email, String mdp) {
        return repo.findByEmailAndMdp(email, mdp);
    }
}
