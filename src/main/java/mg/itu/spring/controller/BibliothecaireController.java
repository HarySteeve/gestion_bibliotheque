package mg.itu.spring.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mg.itu.spring.entity.*;
import mg.itu.spring.service.*;

@Controller
@RequestMapping("/bibliothecaire")
public class BibliothecaireController {
    @Autowired
    private BibliothecaireService biblioService;

    @GetMapping
    public ModelAndView index() {
        List<Bibliothecaire> listBibliothecairesActifs = biblioService.getActifs();
        List<Bibliothecaire> listBibliothecairesVires = biblioService.getVires();
        System.out.println("VIRÉS :");
        listBibliothecairesVires.forEach(b -> System.out.println(b.getUsername() + " - " + b.getDateRenvoie()));

        ModelAndView mav = new ModelAndView("crud/bibliothecaire/form-list");
        mav.addObject("bibliothecairesActifs", listBibliothecairesActifs);
        mav.addObject("bibliothecairesVires", listBibliothecairesVires);
        return mav;
    }

    @PostMapping("/add")
    public ModelAndView addBibliothecaire (
        @RequestParam String nom,
        @RequestParam String prenom, 
        @RequestParam String mdp,
        @RequestParam String username,
        @RequestParam String email,
        @RequestParam LocalDate dateEmbauche
    ) {
        Bibliothecaire bibliothecaire = new Bibliothecaire();
        bibliothecaire.setNom(nom);
        bibliothecaire.setPrenom(prenom);
        bibliothecaire.setMdp(mdp);
        bibliothecaire.setEmail(email);
        bibliothecaire.setUsername(username);
        bibliothecaire.setDateEmbauche(dateEmbauche);

        biblioService.save(bibliothecaire);

        ModelAndView mv = new ModelAndView("redirect:/bibliothecaire");

        return mv;
    }

    @GetMapping("/edit")
    public ModelAndView editBibliothecaire(@RequestParam int id) {
        Bibliothecaire biblio = biblioService.findById(id);
        List<Bibliothecaire> listBibliothecairesActifs = biblioService.getActifs();
        List<Bibliothecaire> listBibliothecairesVires = biblioService.getVires();

        ModelAndView mv = new ModelAndView("crud/bibliothecaire/form-list");
        mv.addObject("bibliothecaire", biblio);
        mv.addObject("bibliothecairesActifs", listBibliothecairesActifs);
        mv.addObject("bibliothecairesVires", listBibliothecairesVires);

        return mv;
    }

    @PostMapping("/update")
    public ModelAndView updateBibliothecaire(@ModelAttribute Bibliothecaire bibliothecaire) {
        biblioService.save(bibliothecaire); // Si l'id existe => update, sinon => insert

        ModelAndView mv = new ModelAndView("redirect:/bibliothecaire");

        return mv;
    }

    @GetMapping("/virer")
    public ModelAndView supprBibliothecaire(@RequestParam int id) {
        Bibliothecaire bibliothecaire = biblioService.findById(id);

        bibliothecaire.setDateRenvoie(LocalDate.now());
        biblioService.save(bibliothecaire);

        ModelAndView mv = new ModelAndView("redirect:/bibliothecaire");

        return mv;
    }

}
