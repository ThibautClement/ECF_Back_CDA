package fr.thcl.formation.ecf.backend.localib.location;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface LocationService {

    List<Location> findAll();

    Location save(Location entity);

    Location findById(String id);

    ResponseEntity<String> deleteById(String id);

    ResponseEntity<String> deleteAll();

    List<Location> findByLocataire(String idLocataire);

    List<Location> findByLocataireName(String nom);

    List<Location> findByVehicule(String idVehicule);

    List<Location> findByVehiculeMarque(String marque);

    List<Location> findByDateDebut(LocalDate dateDebut);

    List<Location> findByDateFin(LocalDate dateFin);


}
