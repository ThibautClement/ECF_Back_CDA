package fr.thcl.formation.ecf.backend.localib.locataire;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocataireService {

    List<Locataire> findAll();

    Locataire save(Locataire entity);

    Locataire findById(String id);

    ResponseEntity<String> deleteById(String id);
}
