package mg.itu.spring.service;

import mg.itu.spring.entity.*;
import mg.itu.spring.repository.*;  
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReabonnementService {
    private final ReabonnementRepository reabonnementRepo;
    private final AdherantRepository adherantRepo;
    private final DureeAbonnementRepository dureeRepo;
    private final FraisReabonnementService fraisService;

    public ReabonnementService(ReabonnementRepository reabonnementRepo,
                               AdherantRepository adherantRepo,
                               DureeAbonnementRepository dureeRepo,
                               FraisReabonnementService fraisService) {
        this.reabonnementRepo = reabonnementRepo;
        this.adherantRepo = adherantRepo;
        this.dureeRepo = dureeRepo;
        this.fraisService = fraisService;
    }

    public void creerReabonnement(Integer idAdherant, LocalDate dateReabonnement) {
        Adherant adherant = adherantRepo.findById(idAdherant).orElseThrow(() ->
            new RuntimeException("Adhérant introuvable avec id: " + idAdherant));

        Integer idProfil = adherant.getProfil().getId();

        // 1. Récupérer montant à payer
        BigDecimal montant = fraisService.getFraisTotalByIdProfilByDate(idProfil, dateReabonnement);

        // 2. Calcul de la date début
        Optional<Reabonnement> dernierReab = reabonnementRepo.findDernierReabonnementByAdherant(idAdherant);
        LocalDate dateDebut;
        if (dernierReab.isPresent()) {
            dateDebut = dernierReab.get().getDateFinAbonnement().plusDays(1);
        } else {
            dateDebut = dateReabonnement;
        }

        // 3. Récupérer la durée active
        DureeAbonnement duree = dureeRepo.findDerniereDuree(dateReabonnement)
            .orElseThrow(() -> new RuntimeException("Aucune durée d’abonnement valide trouvée."));

        // 4. Calcul de la date fin
        int nbJours = calculerDureeEnJours(duree);
        LocalDate dateFin = dateDebut.plusDays(nbJours);

        // 5. Insertion
        Reabonnement reab = new Reabonnement();
        reab.setAdherant(adherant);
        reab.setMontantPaye(montant);
        reab.setDateReabonnement(dateReabonnement);
        reab.setDateDebutAbonnement(dateDebut);
        reab.setDateFinAbonnement(dateFin);

        reabonnementRepo.save(reab);
    }

    private int calculerDureeEnJours(DureeAbonnement d) {
        String unite = d.getUnite().getLibelle().toLowerCase();
        switch (unite) {
            case "jour":
                return d.getDuree();
            case "mois":
                return d.getDuree() * 30;
            case "annee":
                return d.getDuree() * 365;
            default:
                throw new RuntimeException("Unité inconnue : " + unite);
        }
    }

    public Reabonnement findDernierReabonnementByAdherant(int idAdherant) {
        return reabonnementRepo.findDernierReabonnementByAdherant(idAdherant).orElse(null);
    }
}
