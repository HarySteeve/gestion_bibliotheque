package mg.itu.spring.service;

import mg.itu.spring.entity.AccessibiliteLivre;
import mg.itu.spring.entity.Adherant;
import mg.itu.spring.entity.Exemplaire;
import mg.itu.spring.entity.Penalite;
import mg.itu.spring.entity.Pret;
import mg.itu.spring.entity.Reabonnement;
import mg.itu.spring.entity.Reservation;
import mg.itu.spring.repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepo;

    @Autowired
    private AccessibiliteLivreService accesLivreService;

    @Autowired 
    private PenaliteService penaliteService;

    @Autowired
    private ReabonnementService reabonnementService;

    @Autowired
    private PretService pretService;

    public ReservationService(ReservationRepository reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    public List<Reservation> findAll() {
        return reservationRepo.findAll();
    }

    public Optional<Reservation> findById(Integer id) {
        return reservationRepo.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    public void deleteById(Integer id) {
        reservationRepo.deleteById(id);
    }

    public Integer countByAdherantId(int idAdherant) {
        return reservationRepo.countByAdherantId(idAdherant);
    }

    public Integer nbReservationFait(int idAdherant, LocalDate dateDemandeReservation) {
        return reservationRepo.countReservationByDateInRange(idAdherant, dateDemandeReservation);
    }

    public Reservation reservationPlusRecenteByExemplaire(int exemplaireId) {
        return reservationRepo.findDerniereReservationByExemplaireId(exemplaireId);
    }

    public void verifierReservationValide(Reservation reservation) {
        Adherant adherant = reservation.getAdherant();
        Exemplaire exemplaire = reservation.getExemplaire();

        if (adherant == null || exemplaire == null) {
            throw new IllegalArgumentException("Paramètres invalides pour le prêt");
        }

        long nbJourDateDemandeDateReservation = ChronoUnit.DAYS.between(reservation.getDateDemandeReservation(), reservation.getDateReservee());
        if(nbJourDateDemandeDateReservation > adherant.getProfil().getDureeJourReservationMax()) {
            throw new IllegalArgumentException("Duree de reservation + de 15j");
        }

        Integer nbReservation = nbReservationFait(adherant.getId(), reservation.getDateDemandeReservation());
        if(nbReservation >= adherant.getProfil().getNbReservationMax()) {
            throw new IllegalArgumentException("Nombre de demande de reservation maximum dépassé: " + nbReservation + "/" + adherant.getProfil().getNbReservationMax() + "j");
        }

        Pret pretRecentByExemplaire = pretService.getExemplairePlusRecente(exemplaire.getId());
        if(pretRecentByExemplaire != null) {
            LocalDate dateNormalRendue = pretRecentByExemplaire.getDatePris().plusDays(adherant.getProfil().getDureePret());
            if(reservation.getDateReservee().isBefore(dateNormalRendue)) {
                throw new IllegalArgumentException("L'exemplaire est prete au jour de votre reservation");
            }
        }

        Reservation reservationPlusRecente = reservationPlusRecenteByExemplaire(exemplaire.getId());
        if(reservationPlusRecente != null) {
            if(reservation.getDateReservee().isBefore(reservationPlusRecente.getDateReservee()) && reservationPlusRecente.getDateValidation() != null) {
                throw new IllegalArgumentException("L'exemplaire est deja reserve le jour de votre reservation");
            }
        }
        
        AccessibiliteLivre accesLivre = accesLivreService.findByLivreId(exemplaire.getLivre().getId());

        if (adherant.getProfil().getId() != accesLivre.getLivre().getId()) {
            throw new IllegalArgumentException("Profil (" + adherant.getProfil().getDescription() + 
                ") inadapte pour ce genre de livre: " + accesLivre.getProfil().getDescription() + ")");
        }

        Penalite penalite = penaliteService.getDernierePenalite(adherant.getId());
        if (penalite != null && reservation.getDateReservee().isAfter(penalite.getDateDebut()) && reservation.getDateReservee().isBefore(penalite.getDateFin())) {
            long nbJours = ChronoUnit.DAYS.between(penalite.getDateDebut(), penalite.getDateFin());
            throw new IllegalArgumentException("Adhérant pénalisé de " + nbJours + " jours");
        }

        Reabonnement reabonnement = reabonnementService.findDernierReabonnementByAdherant(adherant.getId());
        if (reabonnement != null && reservation.getDateReservee().isAfter(reabonnement.getDateFinAbonnement())) {
            throw new IllegalArgumentException("Adhérent désabonné le jour de reservation: "+ reservation.getDateReservee());
        }
    }  

}
