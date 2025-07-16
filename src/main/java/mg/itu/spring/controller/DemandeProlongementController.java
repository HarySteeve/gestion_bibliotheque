package mg.itu.spring.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import mg.itu.spring.entity.Adherant;
import mg.itu.spring.entity.DemandeProlongement;
import mg.itu.spring.entity.Pret;
import mg.itu.spring.repository.PretRepository;
import mg.itu.spring.service.AdherantService;
import mg.itu.spring.service.DemandeProlongementService;
import mg.itu.spring.service.PretService;

@Controller
@RequestMapping("/prolongement")
public class DemandeProlongementController {

    private final PretRepository pretRepo;
    private final DemandeProlongementService prolongementService;
    private final PretService pretService;
    private final AdherantService adherantService;

    public DemandeProlongementController(PretRepository pretRepo, DemandeProlongementService service, PretService pret, AdherantService adherant) {
        this.pretRepo = pretRepo;
        this.prolongementService = service;
        this.pretService = pret;
        this.adherantService = adherant;
    }

    @GetMapping("/form")
    public String formProlongement(HttpSession session, Model model) {
        Adherant adherant = (Adherant) session.getAttribute("adherant");
        if (adherant == null) return "redirect:/";

        List<Pret> prets = pretRepo.findByAdherantIdAndDateRenduIsNull(adherant.getId());
        model.addAttribute("prets", prets);
        return "demandeProlongement/form";
    }

    @PostMapping("/submit")
    public String submitProlongement(@RequestParam("idPret") Integer idPret, Model model) {
        
        String erreur = prolongementService.demanderProlongement(idPret);
        if (!erreur.equals("succes")) {
            model.addAttribute("error", erreur);
            return "demandeProlongement/form";
        }
        return "redirect:/adherant/index";
    }

    @GetMapping("/list")
    public String showList(Model model) {
        List<DemandeProlongement> demandes = prolongementService.findAll();

        model.addAttribute("demandes", demandes);
        
        return "demandeProlongement/list";
    }

    @GetMapping("/valider/{idDemande}")
    public String validerProlongement(@PathVariable Integer idDemande) {
        DemandeProlongement demandeProlongement = prolongementService.findById(idDemande).orElse(null);
        Adherant adherant = demandeProlongement.getPret().getAdherant();
        Pret pretAdherantRecent = pretService.pretRecentByAdherantId(adherant.getId());

        LocalDate datePretNormalRendue = pretAdherantRecent.getDatePris().plusDays(adherant.getProfil().getDureePret());
        LocalDate finProlongement = datePretNormalRendue.plusDays(adherant.getProfil().getDureePret());
        demandeProlongement.setDebutProlongement(datePretNormalRendue);
        demandeProlongement.setFinProlongement(finProlongement);

        prolongementService.save(demandeProlongement);
        return "redirect:/prolongement/list";
    }
}
