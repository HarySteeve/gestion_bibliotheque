package mg.itu.spring.service;

import mg.itu.spring.entity.DemandeProlongement;
import mg.itu.spring.entity.Pret;
import mg.itu.spring.entity.Profil;
import mg.itu.spring.repository.DemandeProlongementRepository;
import mg.itu.spring.repository.PretRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeProlongementService {
    @Autowired
    private DemandeProlongementRepository demandeRepo;
    @Autowired
    private  PretRepository pretRepository;

    public List<DemandeProlongement> findAll() {
        return demandeRepo.findAll();
    }

    public Optional<DemandeProlongement> findById(Integer id) {
        return demandeRepo.findById(id);
    }

    public DemandeProlongement save(DemandeProlongement demande) {
        return demandeRepo.save(demande);
    }

    public void deleteById(Integer id) {
        demandeRepo.deleteById(id);
    }

    public DemandeProlongement findByPretId(int id) {
        return demandeRepo.findByPretId(id);
    }

    // public void verifierDemandeProlongement(DemandeProlongement demande) {
    //     int nbProlongement 
    // }

    public String demanderProlongement(int idPret) {
        Pret pret = pretRepository.findById(idPret).orElseThrow(() -> new RuntimeException("Prêt introuvable"));
        Profil profil = pret.getAdherant().getProfil();

        int nbProlongementsExistants = demandeRepo.countByPretId(idPret);

        if (nbProlongementsExistants >= profil.getNbProlongement()) {
            return "Ce prêt a déjà atteint la limite de prolongement autorisée.";
        }

        DemandeProlongement demandeProlongement = new DemandeProlongement();
        demandeProlongement.setPret(pret);
        demandeProlongement.setDateDemande(LocalDate.now());
        demandeRepo.save(demandeProlongement);

        return "succes";
    }

}

