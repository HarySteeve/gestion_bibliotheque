package mg.itu.spring.service;

import mg.itu.spring.repository.FraisReabonnementRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class FraisReabonnementService {
    private final FraisReabonnementRepository fraisRepo;

    public FraisReabonnementService(FraisReabonnementRepository fraisRepo) {
        this.fraisRepo = fraisRepo;
    }

    public BigDecimal getFraisTotalByIdProfilByDate(Integer idProfil, LocalDate date) {
        return fraisRepo.findMontantTotalByProfilAndDate(idProfil, date);
    }

}
