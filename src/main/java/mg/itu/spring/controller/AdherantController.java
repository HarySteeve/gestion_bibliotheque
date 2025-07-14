package mg.itu.spring.controller;

import mg.itu.spring.entity.Adherant;
import mg.itu.spring.service.AdherantService;
import mg.itu.spring.service.ProfilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/adherant")
public class AdherantController {

    @Autowired
    private AdherantService adherantService;

    @Autowired
    private ProfilService profilService;

    @GetMapping
    public String listAdherants(Model model) {
        model.addAttribute("adherants", adherantService.findAll());
        return "crud/adherant/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("adherant", new Adherant());
        model.addAttribute("profils", profilService.findAll());
        return "crud/adherant/form";
    }

    @PostMapping("/save")
    public String saveAdherant(@ModelAttribute("adherant") Adherant adherant) {
        adherantService.save(adherant);
        return "redirect:/adherant";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Adherant adherant = adherantService.findById(id).orElse(null);
        if (adherant == null) return "redirect:/adherant";
        model.addAttribute("adherant", adherant);
        model.addAttribute("profils", profilService.findAll());
        return "crud/adherant/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        adherantService.deleteById(id);
        return "redirect:/adherant";
    }
}
