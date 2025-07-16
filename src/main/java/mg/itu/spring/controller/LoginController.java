package mg.itu.spring.controller;

import jakarta.servlet.http.HttpSession;
import mg.itu.spring.service.AdherantService;
import mg.itu.spring.service.BibliothecaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private AdherantService adherantService;

    @Autowired
    private BibliothecaireService bibliothecaireService;

    @GetMapping("/bibliothecaire/index")
    public String goToBibliothecaire() {
        return "bibliothecaire/index";
    }

    @GetMapping("/adherant/index")
    public String goToAdherant() {
        return "adherant/index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "index"; // login.jsp
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String mdp,
                        HttpSession session,
                        Model model) {

        // Tentative de connexion en tant qu'adhérant
        var optAdherant = adherantService.login(email, mdp);
        if (optAdherant.isPresent()) {
            session.setAttribute("adherant", optAdherant.get());
            return "adherant/index";
        }

        // Sinon, tentative de connexion en tant que bibliothécaire
        var optBiblio = bibliothecaireService.login(email, mdp);
        if (optBiblio.isPresent()) {
            session.setAttribute("bibliothecaire", optBiblio.get());
            return "bibliothecaire/index";
        }

        // Aucun des deux
        model.addAttribute("error", "Email ou mot de passe incorrect.");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
