package fr.thcl.formation.ecf.backend.localib.location;

import fr.thcl.formation.ecf.backend.localib.locataire.LocataireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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
}
