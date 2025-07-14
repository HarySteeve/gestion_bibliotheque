package mg.itu.spring.controller;

import mg.itu.spring.entity.Profil;
import mg.itu.spring.service.ProfilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profil")
public class ProfilController {

    @Autowired
    private ProfilService profilService;

    public ProfilController(ProfilService profilService) {
        this.profilService = profilService;
    }

    @GetMapping
    public String listProfils(Model model) {
        model.addAttribute("profils", profilService.findAll());
        return "crud/profil/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("profil", new Profil());
        return "crud/profil/form";
    }

    // @PostMapping("/save")
    // public String saveProfil(@ModelAttribute("profil") Profil profil) {
    //     profilService.save(profil);
    //     return "redirect:/profil";
    // }

    @PostMapping("/save")
    public String saveProfil(
        @RequestParam(name = "id", required = false) Integer id,
        @RequestParam("description") String description,
        @RequestParam("nbLivreMax") int nbLivreMax,
        @RequestParam("dureePret") int dureePret,
        @RequestParam("nbProlongement") int nbProlongement,
        @RequestParam("nbReservationMax") int nbReservationMax
    ) {
        Profil profil = new Profil();

        if (id != null) profil.setId(id); // si modification

        profil.setDescription(description);
        profil.setNbLivreMax(nbLivreMax);
        profil.setDureePret(dureePret);
        profil.setNbProlongement(nbProlongement);
        profil.setDureeProlongement(dureePret);
        profil.setNbReservationMax(nbReservationMax);

        profilService.save(profil);
        return "redirect:/profil";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Profil profil = profilService.findById(id).orElse(null);
        if (profil == null) return "redirect:/profil";
        model.addAttribute("profil", profil);
        return "crud/profil/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProfil(@PathVariable Integer id) {
        profilService.deleteById(id);
        return "redirect:/profil";
    }

}
