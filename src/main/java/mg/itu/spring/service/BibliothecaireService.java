package mg.itu.spring.service;

import mg.itu.spring.entity.Bibliothecaire;
import mg.itu.spring.repository.BibliothecaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BibliothecaireService {

    @Autowired
    private BibliothecaireRepository repository;

    public List<Bibliothecaire> getAll() {
        return repository.findAll();
    }

    public void save(Bibliothecaire bibliothecaire) {
        repository.save(bibliothecaire);
    }

    public Bibliothecaire findById(int id) {
        Optional<Bibliothecaire> opt = repository.findById(id);
        return opt.orElse(null);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public Bibliothecaire findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public List<Bibliothecaire> getActifs() {
        return repository.findByDateRenvoieIsNull();
    }

    public List<Bibliothecaire> getVires() {
        return repository.findByDateRenvoieIsNotNull();
    }

    public Optional<Bibliothecaire> login(String email, String mdp) {
        return repository.findByEmailAndMdp(email, mdp);
    }
}
