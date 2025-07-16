package mg.itu.spring.exception;

import jakarta.servlet.http.HttpServletRequest;
import mg.itu.spring.entity.TypePret;
import mg.itu.spring.service.TypePretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private TypePretService typePretService;

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException ex, Model model, HttpServletRequest request) {
        String path = request.getServletPath();
        model.addAttribute("errorMessage", ex.getMessage());

        switch (path) {
            case "/pret/effectuer":
            case "/pret/modifier":
                return handleFormPret(request, model);
            case "/demande/reservation/form":
                return handleFormReservation(request, model);
            default:
                return "error"; // view générique d'erreur
        }
    }

    // === Traitement du formulaire de prêt ===
    private String handleFormPret(HttpServletRequest request, Model model) {
        String idAdherant = request.getParameter("idAdherant");
        String titreLivre = request.getParameter("titreLivre");
        String reference = request.getParameter("reference");
        String idTypePret = request.getParameter("idTypePret");
        String datePris = request.getParameter("datePris");
        String id = request.getParameter("id");

        if (id != null) {
            model.addAttribute("id", Integer.parseInt(id));
            model.addAttribute("isEdit", true);
        }

        model.addAttribute("idAdherant", parseInteger(idAdherant));
        model.addAttribute("titreLivre", titreLivre);
        model.addAttribute("reference", reference);
        model.addAttribute("idTypePret", parseInteger(idTypePret));
        model.addAttribute("datePris", datePris);

        List<TypePret> typePrets = typePretService.getAll();
        model.addAttribute("typePrets", typePrets);

        return "pret/pret-form";
    }

    // === Traitement du formulaire de réservation ===
    private String handleFormReservation(HttpServletRequest request, Model model) {
        String idAdherant = request.getParameter("idAdherant");
        String titreLivre = request.getParameter("titreLivre");
        String reference = request.getParameter("reference");
        String dateReservee = request.getParameter("dateReservee");

        model.addAttribute("idAdherant", parseInteger(idAdherant));
        model.addAttribute("titreLivre", titreLivre);
        model.addAttribute("reference", reference);
        model.addAttribute("dateReservee", dateReservee);

        return "demandeReservation/demande-reservation";
    }

    // === Méthode utilitaire pour convertir en Integer ===
    private Integer parseInteger(String value) {
        try {
            return (value != null && !value.equals("null")) ? Integer.parseInt(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
