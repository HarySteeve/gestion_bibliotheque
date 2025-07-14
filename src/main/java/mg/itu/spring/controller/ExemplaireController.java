package mg.itu.spring.controller;

import mg.itu.spring.entity.Exemplaire;
import mg.itu.spring.entity.Livre;
import mg.itu.spring.service.ExemplaireService;
import mg.itu.spring.service.LivreService;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exemplaire")
public class ExemplaireController {

    private final ExemplaireService exService;
    private final LivreService livreService;

    public ExemplaireController(ExemplaireService exService, LivreService livreService) {
        this.exService = exService;
        this.livreService = livreService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("exemplaires", exService.findAll());
        return "crud/exemplaire/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("livres", livreService.findAll());
        return "crud/exemplaire/form-add";
    }

    @PostMapping("/save")
    public String saveMany(@RequestParam("idLivre") Integer idLivre,
                           @RequestParam("quantite") int quantite) {
        Livre livre = livreService.findById(idLivre).orElse(null);
        if (livre != null && quantite > 0) {
            exService.ajouterExemplaires(livre, quantite);
        }
        return "redirect:/exemplaire";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Integer id, Model model) {
        Exemplaire ex = exService.findById(id).orElse(null);
        if (ex == null) return "redirect:/exemplaire";
        model.addAttribute("exemplaire", ex);
        return "crud/exemplaire/form-edit";
    }

    @PostMapping("/update")
    public String update( 
        @RequestParam("id") Integer id,
        @RequestParam(name = "dateIndispo", required=false) LocalDate dateIndispo,
        @RequestParam("ref") String ref
    ) {
        Exemplaire ex = exService.findById(id).orElse(null);
        if(dateIndispo != null) ex.setDateIndispo(dateIndispo);
        ex.setRef(ref);
        exService.save(ex);
        return "redirect:/exemplaire";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        exService.deleteById(id);
        return "redirect:/exemplaire";
    }
}
