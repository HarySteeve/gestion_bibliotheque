package mg.itu.spring.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.ui.Model;
import mg.itu.spring.entity.*;
import mg.itu.spring.service.*;

@Controller
public class ReservationController {

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired 
    private AdherantService adherantService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BibliothecaireService biblioService;

    
    @GetMapping("reservation/demande")
    public String formDemandeReservation() {
        return "demandeReservation/demande-reservation";
    }

    @PostMapping("/demande/reservation/form")
    public String demandeReservation(
        @RequestParam String idAdherant,
        @RequestParam String titreLivre,
        @RequestParam String reference,
        @RequestParam LocalDate dateReservee,
        RedirectAttributes redirectAttributes,
        Model model
    ) {
        Integer idAdherantInt = Integer.parseInt(idAdherant);

        Exemplaire exemplaire = exemplaireService.findByRef(reference);
        Adherant adherant = adherantService.findById(idAdherantInt).orElse(null);

        Reservation reservation = new Reservation();
        reservation.setAdherant(adherant);
        reservation.setExemplaire(exemplaire);
        reservation.setDateDemandeReservation(LocalDate.now());
        reservation.setDateReservee(dateReservee);

        reservationService.verifierReservationValide(reservation);
        reservationService.save(reservation);

        // Ajouter un attribut flash
        // Ajouter le flash message
        redirectAttributes.addFlashAttribute("successMessage", "Demande effectue avec succes !");

        // Rediriger vers le contrôleur GET
        return "redirect:/reservation/demande";
    }

    @GetMapping("/reservation/liste")
    public String showListeReservation(Model model) {
        List<Reservation> listeReservations = reservationService.findAll();

        model.addAttribute("reservations", listeReservations);
        return "demandeReservation/liste-reservation";
    }

    @PostMapping("/reservation/valider")
    public String validerReservation(
        @RequestParam String idReservation,
        @RequestParam String idBibliothecaire,
        Model model
    ) {
        Integer idReservationInt = Integer.parseInt(idReservation);
        Integer idBibliothecaireInt = Integer.parseInt(idBibliothecaire);
        Bibliothecaire bibliothecaire = biblioService.findById(idBibliothecaireInt);
        Reservation reservation = reservationService.findById(idReservationInt).orElse(null);
        reservation.setBibliothecaire(bibliothecaire);
        reservation.setDateValidation(LocalDate.now());
        reservationService.save(reservation);

        List<Reservation> listeReservations = reservationService.findAll();
        model.addAttribute("reservations", listeReservations);

        return "demandeReservation/liste-reservation";
    }

    // @GetMapping("/pret/liste")
    // public String showListePrets(Model model) {
    //     List<Pret> listePrets = pretService.getAll();
    //     model.addAttribute("listePrets", listePrets);

    //     return "pret/liste-pret";
    // }

    // @GetMapping("/pret/form/modifier")
    // public String afficherFormModif(@RequestParam String id, Model model) {
    //     Integer idInteger = Integer.parseInt(id);
    //     Pret pret = pretService.findById(idInteger);
    //     List<TypePret> typePrets = typePretService.getAll();
    
    //     model.addAttribute("pret", pret);
    //     model.addAttribute("typePrets", typePrets);
    //     return "pret/preter-livre";
    // }

    // @PostMapping("/pret/modifier")
    // public String modifierPret(
    //     @RequestParam String id,
    //     @RequestParam int idAdherant,
    //     @RequestParam String titreLivre,
    //     @RequestParam int numero,
    //     @RequestParam String idTypePret,
    //     @RequestParam LocalDate datePris,
    //     @RequestParam(required = false) LocalDate dateRendu,
    //     @RequestParam(required = false) String causePenalite,
    //     RedirectAttributes redirectAttributes
    // ) {
    //     Integer idInteger = Integer.parseInt(id);
    //     Pret pret = pretService.findById(idInteger);

    //     pret.setAdherant(adherantService.findById(idAdherant).orElse(null));
    //     pret.setExemplaire(exemplaireService.findByNum(numero));
    //     pret.setTypePret(typePretService.getById(Integer.parseInt(idTypePret)));
    //     pret.setDatePris(datePris);
    //     pret.setDateRendu(dateRendu);
    //     int durePretAdherant = pret.getAdherant().getProfil().getDureePret();
    //     LocalDate dateNormalRendue = pret.getDatePris().plusDays(durePretAdherant);
    //     if(pret.getDateRendu().isAfter(dateNormalRendue)) {
    //         Penalite penalite = new Penalite();
    //         penalite.setAdherant(pret.getAdherant());
    //         penalite.setDateDebut(pret.getDateRendu());
    //         long dureePenalisation = ChronoUnit.DAYS.between(dateNormalRendue, pret.getDateRendu());
    //         LocalDate dateFinPenalisation = pret.getDateRendu().plusDays(dureePenalisation);
    //         penalite.setDateFin(dateFinPenalisation);
    //         penaliteService.save(penalite);

    //         pret.setCausePenalite("Rendement du livre retard de "+ dureePenalisation +"j");
    //     }
    //     pretService.verifierPretValide(pret);
    //     pretService.save(pret);
    //     pret.getExemplaire().setDateIndispo(null);

    //     redirectAttributes.addFlashAttribute("successMessage", "Pret modifie avec succes !");
    //     return "redirect:/livre/preter";
    // }
}
