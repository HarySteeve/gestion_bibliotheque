package mg.itu.spring.controller;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mg.itu.spring.entity.Adherant;
import mg.itu.spring.entity.Penalite;
import mg.itu.spring.entity.Reabonnement;
import mg.itu.spring.service.AdherantService;
import mg.itu.spring.service.PenaliteService;
import mg.itu.spring.service.PretService;
import mg.itu.spring.service.ReabonnementService;

@RestController
@RequestMapping("/adherant/API")
public class AdherantRestController {
    @Autowired 
    private AdherantService adherantService;

    @Autowired
    private ReabonnementService reabonnementService;

    @Autowired 
    private PretService pretService;

    @Autowired 
    private PenaliteService penaliteService;

    @GetMapping("/detail/{id}")
    public Map<String, Object> getAdherantDetail(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        Adherant adherant = adherantService.findById(id).orElse(null);
        if(adherant == null) {
            response.put("error", "Adherant non trouve avec l'id: "+id);
            return response;
        }

        Map<String, Object> quotasPret = new HashMap<>();
        int nbQuotas = pretService.nbPretDisponibleParAdherant(id);
        quotasPret.put("quotas", nbQuotas);

        Map<String, Object> reabonnementMap = new HashMap<>();
        Reabonnement reabonnement = reabonnementService.findDernierReabonnementByAdherant(id);
        reabonnementMap.put("debut abonnement", reabonnement.getDateDebutAbonnement());
        reabonnementMap.put("fin abonnement", reabonnement.getDateDebutAbonnement());

        Map<String, Object> penaliteMap = new HashMap<>();
        Penalite penalite = penaliteService.getDernierePenalite(id);
        if(penalite != null) {
            long nbJours = ChronoUnit.DAYS.between(penalite.getDateDebut(), penalite.getDateFin());
            penaliteMap.put("Durre Penalite", nbJours);
            penaliteMap.put("Date debut", penalite.getDateDebut());
            penaliteMap.put("Date fin", penalite.getDateFin());
        }

        response.put("ADHERANT", adherant);
        response.put("QUOTAS", quotasPret);
        response.put("ABONNEMENT", reabonnementMap);
        response.put("PENALITE", penaliteMap);

        return response;
    }
}
