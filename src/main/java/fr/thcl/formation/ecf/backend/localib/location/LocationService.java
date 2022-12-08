package fr.thcl.formation.ecf.backend.localib.location;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocationService {

    List<Location> findAll();

    Location save(Location entity);

    Location findById(String id);

    ResponseEntity<String> deleteById(String id);

    ResponseEntity<String> deleteAll();
}
