package fr.thcl.formation.ecf.backend.localib.locataire;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LocataireServiceImpl implements LocataireService {

    private final LocataireRepository locataireRepository;
    private static final Logger logger = LoggerFactory.getLogger(LocataireServiceImpl.class);

    public LocataireServiceImpl(LocataireRepository locataireRepository) {
        this.locataireRepository = locataireRepository;
    }

    @Override
    public List<Locataire> findAll() {
        return this.locataireRepository.findAll();
    }

    @Override
    public Locataire save(Locataire entity) {
        if (entity.getDateCreation() == null) {
            entity.setDateCreation(LocalDateTime.now());
        }
        entity.setDateModification(LocalDateTime.now());
        return this.locataireRepository.save(entity);
    }

    @Override
    public Locataire findById(String id) {
        return locataireRepository.findById(id).orElseThrow(() -> {
            logger.warn("findByIdInvalide "+id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public ResponseEntity<String> deleteById(String id) {
        locataireRepository.deleteById(id);
        return new ResponseEntity<>("Suppression du locataire à l'id : " + id, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> deleteAll() {
        locataireRepository.deleteAll();
        return new ResponseEntity<>("Suppression de tous les locataires", HttpStatus.ACCEPTED);
    }

    @Override
    public Locataire modifierLocataire(String idLocataire, Locataire entity) {
        Locataire locataire = this.findById(idLocataire);

        locataire.setNom(entity.getNom());
        locataire.setPrenom(entity.getPrenom());
        locataire.setTelephone(entity.getTelephone());
        locataire.setEmail(entity.getEmail());
        locataire.setDateDeNaissance(entity.getDateDeNaissance());

        return this.save(locataire);
    }

//    @Override
//    public List<Locataire> findByName(String name) {
//        List<Locataire> locataireList = this.findAll();
//        List<Locataire> locataireByName = new ArrayList<>();
//
//        for (Locataire locataire: locataireList) {
//            if (locataire.getNom().equals(name)){
//                locataireByName.add(locataire);
//            }
//        }
//
//        if (locataireByName.isEmpty()) {
//            new ResponseEntity<>("Il n'y aucun locataire à ce nom.", HttpStatus.NOT_FOUND);
//        }
//
//        return locataireByName;
//    }
//
//    @Override
//    public List<Locataire> findByDateDeNaissance(LocalDate dateDeNaissance) {
//        List<Locataire> locataireList = this.findAll();
//        List<Locataire> locataireByDateDeNaissance = new ArrayList<>();
//
//        for (Locataire locataire: locataireList) {
//            if (locataire.getDateDeNaissance().equals(dateDeNaissance)){
//                locataireByDateDeNaissance.add(locataire);
//            }
//        }
//
//        if (locataireByDateDeNaissance.isEmpty()) {
//            new ResponseEntity<>("Il n'y aucun locataire pour cette date de naissance.", HttpStatus.NOT_FOUND);
//        }
//
//        return locataireByDateDeNaissance;
//    }
}