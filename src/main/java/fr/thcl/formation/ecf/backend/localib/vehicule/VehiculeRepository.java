package fr.thcl.formation.ecf.backend.localib.vehicule;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehiculeRepository extends MongoRepository<Vehicule, String> {
}
