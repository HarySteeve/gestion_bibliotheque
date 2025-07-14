package mg.itu.spring.controller;

import mg.itu.spring.entity.Livre;
import mg.itu.spring.service.LivreService;
import mg.itu.spring.service.AuteurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livre")
public class LivreController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private AuteurService auteurService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("livres", livreService.findAll());
        return "crud/livre/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("livre", new Livre());
        model.addAttribute("auteurs", auteurService.findAll());
        return "crud/livre/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Livre livre) {
        livreService.save(livre);
        return "redirect:/livre";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Livre livre = livreService.findById(id).orElse(null);
        if (livre == null) return "redirect:/livre";
        model.addAttribute("livre", livre);
        model.addAttribute("auteurs", auteurService.findAll());
        return "crud/livre/form";
    }

    // @GetMapping("/delete/{id}")
    // public String delete(@PathVariable Integer id) {
    //     livreService.deleteById(id);
    //     return "redirect:/livre";
    // }
}
