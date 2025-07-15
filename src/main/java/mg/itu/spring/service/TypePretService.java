package mg.itu.spring.service;

import mg.itu.spring.entity.TypePret;
import mg.itu.spring.repository.TypePretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypePretService {

    @Autowired
    private TypePretRepository repository;

    public List<TypePret> getAll() {
        return repository.findAll();
    }

    public TypePret getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public TypePret save(TypePret typePret) {
        return repository.save(typePret);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
