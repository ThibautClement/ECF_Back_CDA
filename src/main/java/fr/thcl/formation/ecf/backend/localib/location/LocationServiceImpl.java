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

    @Override
    public List<Location> findAll() {
        return this.locatationRepository.findAll();
    }

    @Override
    public Location save(Location entity) {
        if (entity.getDateCreation() == null) {
            entity.setDateCreation(LocalDateTime.now());
        }
        entity.setDateModification(LocalDateTime.now());
        return this.locatationRepository.save(entity);
    }

    @Override
    public Location findById(String id) {
        return locatationRepository.findById(id).orElseThrow(() -> {
            logger.warn("findByIdInvalide "+id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public ResponseEntity<String> deleteById(String id) {
        locatationRepository.deleteById(id);
        return new ResponseEntity<>("Suppression du locataire à l'id : " + id, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> deleteAll() {
        locatationRepository.deleteAll();
        return new ResponseEntity<>("Suppression de tous les locataires", HttpStatus.ACCEPTED);
    }

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
