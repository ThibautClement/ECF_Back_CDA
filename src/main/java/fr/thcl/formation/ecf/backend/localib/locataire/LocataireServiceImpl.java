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

    /**
     * Récupère la liste de tous les locataires
     * @return une liste de locataire
     */
    @Override
    public List<Locataire> findAll() {
        return this.locataireRepository.findAll();
    }

    /**
     * Sauvegarde un locataire existant ou non et met à jour sa date de création et de modification
     * @param entity
     * @return un locataire
     */
    @Override
    public Locataire save(Locataire entity) {
        if (entity.getDateCreation() == null) {
            entity.setDateCreation(LocalDateTime.now());
        }
        entity.setDateModification(LocalDateTime.now());
        return this.locataireRepository.save(entity);
    }

    /**
     * Récupère un locataire par son id
     * @param id
     * @return un locataire
     */
    @Override
    public Locataire findById(String id) {
        return locataireRepository.findById(id).orElseThrow(() -> {
            logger.warn("findByIdInvalide "+id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    /**
     * Supprimer un locataire par son id
     * @param id
     * @return un message de suppression et un statut Http ACCEPTED
     */
    @Override
    public ResponseEntity<String> deleteById(String id) {
        locataireRepository.deleteById(id);
        return new ResponseEntity<>("Suppression du locataire à l'id : " + id, HttpStatus.ACCEPTED);
    }

    /**
     * Supprime tous les locataires
     * @return un message de suppression et un statut Http ACCEPTED
     */
    @Override
    public ResponseEntity<String> deleteAll() {
        locataireRepository.deleteAll();
        return new ResponseEntity<>("Suppression de tous les locataires", HttpStatus.ACCEPTED);
    }

    /**
     * Permet de modifier les données d'un locataire
     * @param idLocataire l'id du locataire à modifier
     * @param entity le body qui comprend les élèments à modifier
     * @return un locataire
     */
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

    /**
     * Récupère une liste de locataires par nom
     * @param nom le nom de la recherche
     * @return une liste de locataire(s)
     */
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

    /**
     * Récupère une liste de locataires par date de naissance
     * @param dateDeNaissance la date de naissance recherché
     * @return une liste de locataire(s)
     */
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
