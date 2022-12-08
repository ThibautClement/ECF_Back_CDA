package fr.thcl.formation.ecf.backend.localib.vehicule;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehiculeService {

    List<Vehicule> findAll();

    Vehicule save(Vehicule entity);

    Vehicule findById(String id);

    ResponseEntity<String> deleteById(String id);

    ResponseEntity<String> deleteAll();

}
