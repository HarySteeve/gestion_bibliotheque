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
public class PretControlleur {
    @Autowired
    private PretService pretService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired 
    private AdherantService adherantService;

    @Autowired
    private TypePretService typePretService;

    @Autowired
    private DemandeProlongementService demandeService;

    @GetMapping("/livre/preter")
    public String formPreterLivre(Model model) {
        List<TypePret> typePrets = typePretService.getAll();
        model.addAttribute("typePrets", typePrets);
        return "pret/pret-form";
    }

    @PostMapping("/pret/effectuer")
    public String preterUnLivre(
        @RequestParam int idAdherant,
        @RequestParam int numero,
        @RequestParam String idTypePret,
        @RequestParam LocalDate datePret,
        RedirectAttributes redirectAttributes,
        Model model
    ) {
        Integer typePret = Integer.parseInt(idTypePret);

        Exemplaire exemplaire = exemplaireService.findByNum(numero);
        Adherant adherant = adherantService.findById(idAdherant).orElse(null);
        TypePret typePretObjet = typePretService.getById(typePret);

        Pret pret = new Pret();
        pret.setAdherant(adherant);
        pret.setExemplaire(exemplaire);
        pret.setTypePret(typePretObjet);
        pret.setDatePris(datePret);

        pretService.verifierPretValide(pret);
        pretService.save(pret);

        exemplaire.setDateIndispo(LocalDate.now());

        List<TypePret> typePrets = typePretService.getAll();
        model.addAttribute("typePrets", typePrets);

        // Ajouter un attribut flash
        // Ajouter le flash message
        redirectAttributes.addFlashAttribute("successMessage", "Pret effectue avec succes !");

        // Rediriger vers le contrôleur GET
        return "redirect:/livre/preter";
    }

    @GetMapping("/pret/liste")
    public String showListePrets(Model model) {
        List<Pret> listePrets = pretService.getAll();
        model.addAttribute("listePrets", listePrets);

        return "pret/pret-list";
    }

    @GetMapping("/pret/form/modifier")
    public String afficherFormModif(@RequestParam String id, Model model) {
        Integer idInteger = Integer.parseInt(id);
        Pret pret = pretService.findById(idInteger);
        List<TypePret> typePrets = typePretService.getAll();
    
        model.addAttribute("pret", pret);
        model.addAttribute("typePrets", typePrets);
        return "pret/pret-form";
    }

    @PostMapping("/pret/modifier")
    public String modifierPret(
        @RequestParam String id,
        @RequestParam int idAdherant,
        @RequestParam int numero,
        @RequestParam String idTypePret,
        @RequestParam LocalDate datePris,
        @RequestParam(required = false) LocalDate dateRendu,
        @RequestParam(required = false) String causePenalite,
        RedirectAttributes redirectAttributes,
        Model model
    ) {
        Integer idInteger = Integer.parseInt(id);
        Pret pret = pretService.findById(idInteger);

        pret.setAdherant(adherantService.findById(idAdherant).orElse(null));
        pret.setExemplaire(exemplaireService.findByNum(numero));
        pret.setTypePret(typePretService.getById(Integer.parseInt(idTypePret)));
        pret.setDatePris(datePris);
        pret.setDateRendu(dateRendu);

        pretService.rendreLivre(pret);
        redirectAttributes.addFlashAttribute("successMessage", "Pret modifie avec succes !");
        return "redirect:/livre/preter";
    }
}
