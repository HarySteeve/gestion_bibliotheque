package mg.itu.spring.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import mg.itu.spring.entity.Adherant;
import mg.itu.spring.entity.Pret;
import mg.itu.spring.repository.PretRepository;
import mg.itu.spring.service.DemandeProlongementService;

@Controller
@RequestMapping("/prolongement")
public class DemandeProlongementController {

    private final PretRepository pretRepo;
    private final DemandeProlongementService prolongementService;

    public DemandeProlongementController(PretRepository pretRepo, DemandeProlongementService service) {
        this.pretRepo = pretRepo;
        this.prolongementService = service;
    }

    @GetMapping("/form")
    public String formProlongement(HttpSession session, Model model) {
        Adherant adherant = (Adherant) session.getAttribute("adherant");
        if (adherant == null) return "redirect:/login";

        List<Pret> prets = pretRepo.findByAdherantIdAndDateRenduIsNull(adherant.getId());
        model.addAttribute("prets", prets);
        return "demandeProlongement/form";
    }

    @PostMapping("/submit")
    public String submitProlongement(@RequestParam("idPret") Integer idPret, Model model) {
        String erreur = prolongementService.demanderProlongement(idPret);
        if (erreur != null) {
            model.addAttribute("error", erreur);
            return "demandeProlongement/form";
        }
        return "redirect:/adherant/index";
    }
}
