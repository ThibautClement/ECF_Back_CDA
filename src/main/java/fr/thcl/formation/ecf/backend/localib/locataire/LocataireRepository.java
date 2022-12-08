package fr.thcl.formation.ecf.backend.localib.locataire;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocataireRepository extends MongoRepository<Locataire, String> {
}
