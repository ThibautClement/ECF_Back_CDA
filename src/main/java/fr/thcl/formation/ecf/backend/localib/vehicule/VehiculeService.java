package fr.thcl.formation.ecf.backend.localib.vehicule;

import fr.thcl.formation.ecf.backend.localib.vehicule.Enum.VehiculeEtat;
import fr.thcl.formation.ecf.backend.localib.vehicule.Enum.VehiculeType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehiculeService {

    List<Vehicule> findAll();

    Vehicule save(Vehicule entity);

    Vehicule findById(String id);

    ResponseEntity<String> deleteById(String id);

    ResponseEntity<String> deleteAll();

    List<Vehicule> findByMarque(String marque);

    List<Vehicule> findByEtat(VehiculeEtat etat);

    List<Vehicule> findByType(VehiculeType type);

    List<Vehicule> findByPrix(double prix);

    List<Vehicule> findByDispo();

}
