package mg.itu.spring.service;

import mg.itu.spring.entity.AccessibiliteLivre;
import mg.itu.spring.entity.Adherant;
import mg.itu.spring.entity.Exemplaire;
import mg.itu.spring.entity.Penalite;
import mg.itu.spring.entity.Pret;
import mg.itu.spring.entity.Reabonnement;
import mg.itu.spring.entity.TypePret;
import mg.itu.spring.repository.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class PretService {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private AccessibiliteLivreService accesLivreService;

    @Autowired
    private PenaliteService penaliteService;

    @Autowired
    private ReabonnementService reabonnementService;

    // Sauvegarder un prêt (insertion ou mise à jour)
    public Pret save(Pret pret) {
        return pretRepository.save(pret);
    }

    // Récupérer tous les prêts
    public List<Pret> getAll() {
        return pretRepository.findAll();
    }

    // Récupérer un prêt par son ID
    public Pret findById(int id) {
        Optional<Pret> result = pretRepository.findById(id);
        return result.orElse(null);
    }

    // Supprimer un prêt par son ID
    public void delete(int id) {
        pretRepository.deleteById(id);
    }

    // Prêts non rendus (date_rendu = null)
    public List<Pret> getNonRendus() {
        return pretRepository.findByDateRenduIsNull();
    }

    // Prêts rendus (date_rendu != null)
    public List<Pret> getRendus() {
        return pretRepository.findByDateRenduIsNotNull();
    }

    public int compterPretsTotalAdherant(Integer idAdherant) {
        return pretRepository.countByAdherantId(idAdherant);
    }

    public int nbPretDisponibleParAdherant(int idAdherant) {
        return pretRepository.countByAdherantIdAndDateRenduIsNull(idAdherant);
    }

    public Pret getExemplairePlusRecente(int idExemplaire) {
        return pretRepository.findDernierPretByExemplaireId(idExemplaire);
    }

    public void verifierPretValide(Pret pret) {
        Adherant adherant = pret.getAdherant();
        Exemplaire exemplaire = pret.getExemplaire();
        TypePret typePret = pret.getTypePret();

        if (adherant == null || exemplaire == null || typePret == null) {
            throw new IllegalArgumentException("Paramètres invalides pour le prêt");
        }

        AccessibiliteLivre accesLivre = accesLivreService.findByLivreId(exemplaire.getLivre().getId());

        if(accesLivre != null) {
            if (adherant.getProfil().getId() != accesLivre.getLivre().getId()) {
                throw new IllegalArgumentException("Profil (" + adherant.getProfil().getDescription() + 
                    ") inadapte pour ce genre de livre: " + accesLivre.getProfil().getDescription() + ")");
            }

            if (typePret.getId() != accesLivre.getTypePret().getId()) {
                throw new IllegalArgumentException("Type de prêt (" + typePret.getLibelle() + 
                    ") inadapte pour ce genre de livre: " + accesLivre.getTypePret().getLibelle() + ")");
            }
        }

        if (exemplaire.getDateIndispo() != null) {
            throw new IllegalArgumentException("Exemplaire numéro (" + exemplaire.getNumero() + ") non disponible");
        }

        Penalite penalite = penaliteService.getDernierePenalite(adherant.getId());
        if (penalite != null && LocalDate.now().isAfter(penalite.getDateDebut()) && LocalDate.now().isBefore(penalite.getDateFin())) {
            long nbJours = ChronoUnit.DAYS.between(penalite.getDateDebut(), penalite.getDateFin());
            throw new IllegalArgumentException("Adhérant pénalisé de " + nbJours + " jours");
        }

        int nbPrets = nbPretDisponibleParAdherant(adherant.getId());
        if (nbPrets >= adherant.getProfil().getNbLivreMax()) {
            throw new IllegalArgumentException("Nombre de prêts maximum dépassé: " + nbPrets + "/" + adherant.getProfil().getNbLivreMax());
        }

        int dureePret = adherant.getProfil().getDureePret();
        LocalDate dateRenduPrevu = LocalDate.now().plusDays(dureePret);
        Reabonnement reabonnement = reabonnementService.findDernierReabonnementByAdherant(adherant.getId());
        if (reabonnement != null && dateRenduPrevu.isAfter(reabonnement.getDateFinAbonnement())) {
            throw new IllegalArgumentException("Adhérent désabonné");
        }
    }   

    public void rendreLivre(Pret pret) {
        int durePretAdherant = pret.getAdherant().getProfil().getDureePret();
        LocalDate dateNormalRendue = pret.getDatePris().plusDays(durePretAdherant);
        if(pret.getDateRendu().isAfter(dateNormalRendue)) {
            Penalite penalite = new Penalite();
            penalite.setAdherant(pret.getAdherant());
            penalite.setDateDebut(pret.getDateRendu());
            long dureePenalisation = ChronoUnit.DAYS.between(dateNormalRendue, pret.getDateRendu());
            LocalDate dateFinPenalisation = pret.getDateRendu().plusDays(dureePenalisation);
            penalite.setDateFin(dateFinPenalisation);
            penaliteService.save(penalite);

            pret.setCausePenalite("Rendement du livre retard de "+ dureePenalisation +"j");
        }
        verifierPretValide(pret);
        save(pret);
        pret.getExemplaire().setDateIndispo(null);
    }

    public List<Pret> findPretNonRenduByAdherant(Integer idAdherant) {
        return pretRepository.findByAdherantIdAndDateRenduIsNull(idAdherant);
    }
    
}
