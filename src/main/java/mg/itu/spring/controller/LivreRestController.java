package mg.itu.spring.controller;

import mg.itu.spring.entity.Exemplaire;
import mg.itu.spring.entity.Livre;
import mg.itu.spring.service.LivreService;
import mg.itu.spring.service.ExemplaireService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livre/API")
public class LivreRestController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private ExemplaireService exemplaireService;

    /*@GetMapping("/detail/{id}")
    public Map<String, Object> getLivreDetail(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        Livre livreOpt = livreService.findById(id).orElse(null);
        if (livreOpt == null) {
            response.put("error", "Livre non trouvé avec l'id: " + id);
            return response;
        }

        List<Exemplaire> exemplaires = exemplaireService.findByLivreId(id);

        response.put("livre", livreOpt);
        response.put("exemplaires", exemplaires);

        return response;
    }*/
    @GetMapping("/detail/{id}")
    public Map<String, Object> getLivreDetail(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        Livre livre = livreService.findById(id).orElse(null);
        if (livre == null) {
            response.put("error", "Livre non trouvé avec l'id: " + id);
            return response;
        }

        List<Exemplaire> exemplaires = exemplaireService.findByLivreId(id);

        // On transforme les exemplaires en un format personnalisé
        List<Map<String, Object>> exemplairesCustom = exemplaires.stream().map(ex -> {
            Map<String, Object> exMap = new HashMap<>();
            exMap.put("id", ex.getId());
            exMap.put("numero", ex.getNumero());
            exMap.put("reference", ex.getRef());
            exMap.put("disponibilite", (ex.getDateIndispo() == null) ? "Disponible" : "Indisponible");
            return exMap;
        }).toList();

        response.put("livre", livre);
        response.put("exemplaires", exemplairesCustom);

        return response;
    }

}
