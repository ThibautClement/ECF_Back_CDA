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

        if (entity.getNom() == null) {
            locataire.setNom(locataire.getNom());
        } else {
            locataire.setNom(entity.getNom());
        }

        if (entity.getPrenom() == null) {
            locataire.setPrenom(locataire.getPrenom());
        } else {
            locataire.setPrenom(entity.getPrenom());
        }

        if (entity.getTelephone() == null) {
            locataire.setTelephone(locataire.getTelephone());
        } else {
            locataire.setTelephone(entity.getTelephone());
        }

        if (entity.getEmail() == null) {
            locataire.setEmail(locataire.getEmail());
        } else {
            locataire.setEmail(entity.getEmail());
        }

        if (entity.getDateDeNaissance() == null) {
            locataire.setDateDeNaissance(locataire.getDateDeNaissance());
        } else {
            locataire.setDateDeNaissance(entity.getDateDeNaissance());
        }

        return this.save(locataire);
    }

    @Override
    public List<Locataire> findByName(String nom) {
        List<Locataire> locataireList = this.findAll();
        List<Locataire> locataireByName = new ArrayList<>();

        for (Locataire locataire: locataireList) {
            if (locataire.getNom().equalsIgnoreCase(nom)){
                locataireByName.add(locataire);
            }
        }

        if (locataireByName.isEmpty()) {
            logger.warn("Il n'existe aucun locataire à ce nom : " +nom);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'existe aucun locataire à ce nom : " +nom);
        } else {
            return locataireByName;
        }
    }

    @Override
    public List<Locataire> findByDateDeNaissance(LocalDate dateDeNaissance) {
        List<Locataire> locataireList = this.findAll();
        List<Locataire> locataireByDateDeNaissance = new ArrayList<>();

        for (Locataire locataire: locataireList) {
            if (locataire.getDateDeNaissance().equals(dateDeNaissance)){
                locataireByDateDeNaissance.add(locataire);
            }
        }

        if (locataireByDateDeNaissance.isEmpty()) {
            logger.warn("Il n'existe aucun locataire pour cette date de naissance : " +dateDeNaissance);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'existe aucun locataire pour cette date de naissance : " +dateDeNaissance);
        } else {
            return locataireByDateDeNaissance;
        }
    }
}
