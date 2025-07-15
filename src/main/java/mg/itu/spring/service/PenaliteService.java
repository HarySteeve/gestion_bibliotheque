package mg.itu.spring.service;

import mg.itu.spring.entity.Penalite;
import mg.itu.spring.repository.PenaliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PenaliteService {

    @Autowired
    private PenaliteRepository penaliteRepository;

    public void save(Penalite penalite) {
        penaliteRepository.save(penalite);
    }

    public List<Penalite> getAll() {
        return penaliteRepository.findAll();
    }

    public List<Penalite> getByAdherantId(Integer adherantId) {
        return penaliteRepository.findByAdherantId(adherantId);
    }

    public Penalite getDernierePenalite(Integer adherantId) {
        return penaliteRepository.findTopByAdherantIdOrderByDateFinDesc(adherantId);
    }
    
    public boolean existsByAdherantIdAndDate(Integer idAdherant, LocalDate date) {
        return penaliteRepository.existsByAdherantIdAndDate(idAdherant, date);
    }

}
