package mg.itu.spring.controller;

import mg.itu.spring.service.AdherantService;
import mg.itu.spring.service.ReabonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/reabonnement")
public class ReabonnementController {

    private final AdherantService adherantService;
    private final ReabonnementService reabonnementService;

    @Autowired
    public ReabonnementController(AdherantService adherantService, ReabonnementService reabonnementService) {
        this.adherantService = adherantService;
        this.reabonnementService = reabonnementService;
    }

    @GetMapping("/add")
    public String showReabonnementForm(Model model) {
        model.addAttribute("adherants", adherantService.findAll());
        model.addAttribute("today", LocalDate.now());
        return "reabonnement/form"; // Correspond à /WEB-INF/views/reabonnement/form.jsp
    }

    @PostMapping("/save")
    public String saveReabonnement(
            @RequestParam("idAdherant") Integer idAdherant,
            @RequestParam("dateReabonnement") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        reabonnementService.creerReabonnement(idAdherant, date);
        return "redirect:/reabonnement/add";
    }
}
