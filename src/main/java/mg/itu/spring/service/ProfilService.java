package mg.itu.spring.service;

import mg.itu.spring.entity.Profil;
import mg.itu.spring.repository.ProfilRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfilService {

    private final ProfilRepository profilRepository;

    public ProfilService(ProfilRepository profilRepository) {
        this.profilRepository = profilRepository;
    }

    public List<Profil> findAll() {
        return profilRepository.findAll();
    }

    public Optional<Profil> findById(Integer id) {
        return profilRepository.findById(id);
    }

    public Profil save(Profil profil) {
        return profilRepository.save(profil);
    }

    public void deleteById(Integer id) {
        profilRepository.deleteById(id);
    }
}
