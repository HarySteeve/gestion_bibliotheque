package mg.itu.spring.service;

import mg.itu.spring.entity.Exemplaire;
import mg.itu.spring.entity.Livre;
import mg.itu.spring.repository.ExemplaireRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExemplaireService {
    private ExemplaireRepository repo;

    public ExemplaireService(ExemplaireRepository repo) {
        this.repo = repo;
    }

    public List<Exemplaire> findAll() {
        return repo.findAll();
    }

    public Optional<Exemplaire> findById(Integer id) {
        return repo.findById(id);
    }

    public void save(Exemplaire ex) {
        repo.save(ex);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public void ajouterExemplaires(Livre livre, int quantite) {
        int numeroDebut = repo.findMaxNumeroByLivre(livre) + 1;
        for (int i = 0; i < quantite; i++) {
            Exemplaire ex = new Exemplaire();
            ex.setLivre(livre);
            ex.setNumero(numeroDebut + i);
            repo.save(ex);
        }
    }

    public Exemplaire findByNum(int num) {
        return repo.findByNumero(num);
    }

    // public String genererRef(Livre livre, int numero) {
    //     return "LIVRE-" + livre.getId() + "-" + numero;
    // }
    //  public boolean isExemplaireDisponible(Integer idExemplaire, LocalDate date) {
        // Vérifie les prêts
        // boolean occupeParPret = pretRepo.countByExemplaireIdAndDateInRange(idExemplaire, date) > 0;
    //  public boolean isExemplaireDisponible(Integer idExemplaire, LocalDate date) {
    //     // Vérifie les prêts
    //     boolean occupeParPret = pretRepo.existsByExemplaireIdAndDateInRange(idExemplaire, date);

    //     // Vérifie les prolongements
    //     boolean occupeParProlongement = prolongementRepo.existsByPretExemplaireIdAndDateInRange(idExemplaire, date);

    //     // Vérifie la date_indispo
    //     Exemplaire ex = repo.findById(idExemplaire).orElse(null);
    //     boolean indispo = ex != null && ex.getDateIndispo() != null && !ex.getDateIndispo().isAfter(date);

    //     return !(occupeParPret || occupeParProlongement || indispo);
    // }
}
