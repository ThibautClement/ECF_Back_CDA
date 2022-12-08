package fr.thcl.formation.ecf.backend.localib.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LocationServiceImpl implements LocationService{

    private final LocationRepository locatationRepository;

    private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

    public LocationServiceImpl(LocationRepository locatationRepository) {
        this.locatationRepository = locatationRepository;
    }

    /**
     * Récupère la liste de toutes les locations
     * @return une liste de locations
     */
    @Override
    public List<Location> findAll() {
        return this.locatationRepository.findAll();
    }

    /**
     * Sauvegarde une location existante ou non et met à jour sa date de création et de modification
     * @param entity
     * @return une location
     */
    @Override
    public Location save(Location entity) {
        if (entity.getDateCreation() == null) {
            entity.setDateCreation(LocalDateTime.now());
        }
        entity.setDateModification(LocalDateTime.now());
        return this.locatationRepository.save(entity);
    }

    /**
     * Récupère une location par son id
     * @param id
     * @return une location
     */
    @Override
    public Location findById(String id) {
        return locatationRepository.findById(id).orElseThrow(() -> {
            logger.warn("findByIdInvalide "+id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    /**
     * Supprime une location
     * @param id
     * @return un message de suppression et un statut Http ACCEPTED
     */
    @Override
    public ResponseEntity<String> deleteById(String id) {
        locatationRepository.deleteById(id);
        return new ResponseEntity<>("Suppression du locataire à l'id : " + id, HttpStatus.ACCEPTED);
    }

    /**
     * Supprime toutes les locations
     * @return un message de suppression et un statut Http ACCEPTED
     */
    @Override
    public ResponseEntity<String> deleteAll() {
        locatationRepository.deleteAll();
        return new ResponseEntity<>("Suppression de tous les locataires", HttpStatus.ACCEPTED);
    }

    /**
     * Récupère une liste de locations par locataires
     * @param idLocataire
     * @return une liste de location(s)
     */
    @Override
    public List<Location> findByLocataire(String idLocataire) {
        List<Location> locationList = this.findAll();
        List<Location> locationsByLocataire = new ArrayList<>();

        for (Location location: locationList) {
            if (location.getLocataire().getId().equals(idLocataire)){
                locationsByLocataire.add(location);
            }
        }

        if (locationsByLocataire.isEmpty()) {
            logger.warn("Il n'existe aucune location avec ce locataire.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'existe aucune location avec ce locataire.");
        } else {
            return locationsByLocataire;
        }
    }

    /**
     * Récupère une liste de locations par nom de locataires
     * @param nom
     * @return une liste de location(s)
     */
    @Override
    public List<Location> findByLocataireName(String nom) {
        List<Location> locationList = this.findAll();
        List<Location> locationsByLocataireName = new ArrayList<>();

        for (Location location: locationList) {
            if (location.getLocataire().getNom().equalsIgnoreCase(nom)) {
                locationsByLocataireName.add(location);
            }
        }

        if (locationsByLocataireName.isEmpty()) {
            logger.warn("Il n'existe aucune location avec ce nom de locataire.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'existe aucune location avec ce nom de locataire.");
        } else {
            return locationsByLocataireName;
        }
    }

    /**
     * Récupère une liste de locations par véhicule
     * @param idVehicule
     * @return une liste de location(s)
     */
    @Override
    public List<Location> findByVehicule(String idVehicule) {
        List<Location> locationList = this.findAll();
        List<Location> locationsByVehicule = new ArrayList<>();

        for (Location location: locationList) {
            if (location.getVehicule().getId().equals(idVehicule)){
                locationsByVehicule.add(location);
            }
        }

        if (locationsByVehicule.isEmpty()) {
            logger.warn("Il n'existe aucune location avec ce vehicule.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'existe aucune location avec ce vehicule.");
        } else {
            return locationsByVehicule;
        }
    }

    /**
     * Récupère une liste de locations par marque de véhicule
     * @param marque
     * @return une liste de location(s)
     */
    @Override
    public List<Location> findByVehiculeMarque(String marque) {
        List<Location> locationList = this.findAll();
        List<Location> locationsByVehiculeMarque = new ArrayList<>();

        for (Location location: locationList) {
            if (location.getVehicule().getMarque().equalsIgnoreCase(marque)) {
                locationsByVehiculeMarque.add(location);
            }
        }

        if (locationsByVehiculeMarque.isEmpty()) {
            logger.warn("Il n'existe aucune location avec cette marque de véhicule.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'existe aucune location cette marque de véhicule.");
        } else {
            return locationsByVehiculeMarque;
        }
    }

    /**
     * Récupère une liste de locations par date de début de location
     * @param dateDebut
     * @return une liste de location(s)
     */
    @Override
    public List<Location> findByDateDebut(LocalDate dateDebut) {
        List<Location> locationList = this.findAll();
        List<Location> locationsByDateDebut = new ArrayList<>();

        for (Location location: locationList) {
            if (location.getDateDebut().equals(dateDebut)){
                locationsByDateDebut.add(location);
            }
        }

        if (locationsByDateDebut.isEmpty()) {
            logger.warn("Il n'existe aucune location a cette date de debut.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'existe aucune location a cette date de debut.");
        } else {
            return locationsByDateDebut;
        }
    }

    /**
     * Récupère une liste de locations par date de fin
     * @param dateFin
     * @return une liste de location(s)
     */
    @Override
    public List<Location> findByDateFin(LocalDate dateFin) {
        List<Location> locationList = this.findAll();
        List<Location> locationsByDateFin = new ArrayList<>();

        for (Location location: locationList) {
            if (location.getDateFin().equals(dateFin)){
                locationsByDateFin.add(location);
            }
        }

        if (locationsByDateFin.isEmpty()) {
            logger.warn("Il n'existe aucune location a cette date de fin.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'existe aucune location a cette date de fin.");
        } else {
            return locationsByDateFin;
        }
    }
}
