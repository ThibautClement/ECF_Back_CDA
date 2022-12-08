package fr.thcl.formation.ecf.backend.localib.locataire;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface LocataireService {

    List<Locataire> findAll();

    Locataire save(Locataire entity);

    Locataire findById(String id);

    ResponseEntity<String> deleteById(String id);

    ResponseEntity<String> deleteAll();

    Locataire modifierLocataire(String idLocataire, Locataire entity);

    List<Locataire> findByName(String nom);

    List<Locataire> findByDateDeNaissance(LocalDate dateDeNaissance);

}
