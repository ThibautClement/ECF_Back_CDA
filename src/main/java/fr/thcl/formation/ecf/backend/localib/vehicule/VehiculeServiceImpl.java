package fr.thcl.formation.ecf.backend.localib.vehicule;

import fr.thcl.formation.ecf.backend.localib.vehicule.Enum.VehiculeEtat;
import fr.thcl.formation.ecf.backend.localib.vehicule.Enum.VehiculeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VehiculeServiceImpl implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    private static final Logger logger = LoggerFactory.getLogger(VehiculeServiceImpl.class);

    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    /**
     * Récupère la liste de tous les véhicules
     * @return une liste de véhicule(s)
     */
    @Override
    public List<Vehicule> findAll() {
        return this.vehiculeRepository.findAll();
    }

    /**
     * Sauvegarde un véhicule existant ou non et met à jour sa date de création et de modification
     * @param entity
     * @return un vehicule
     */
    @Override
    public Vehicule save(Vehicule entity) {
        if (entity.getDateCreation() == null) {
            entity.setDateCreation(LocalDateTime.now());
        }
        entity.setDateModification(LocalDateTime.now());
        return this.vehiculeRepository.save(entity);
    }

    /**
     * Récupère un vehicule par son id
     * @param id
     * @return un vehicule
     */
    @Override
    public Vehicule findById(String id) {
        return vehiculeRepository.findById(id).orElseThrow(() -> {
            logger.warn("findByIdInvalide "+id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    /**
     * Supprimer un vehicule à l'id donné
     * @param id
     * @return un message de suppression et un statut Http ACCEPTED
     */
    @Override
    public ResponseEntity<String> deleteById(String id) {
        vehiculeRepository.deleteById(id);
        return new ResponseEntity<>("Suppression du vehicule à l'id : " + id, HttpStatus.ACCEPTED);
    }

    /**
     * Supprimer tous les vehicules
     * @return un message de suppression et un statut Http ACCEPTED
     */
    @Override
    public ResponseEntity<String> deleteAll() {
        vehiculeRepository.deleteAll();
        return new ResponseEntity<>("Suppression de tous les vehicules", HttpStatus.ACCEPTED);
    }

    /**
     * Permet de modifier les données d'un vehicules
     * @param idVehicule
     * @param entity le body qui comprend les élèments à modifier
     * @return un vehicule modifié
     */
    @Override
    public Vehicule modifierVehicule(String idVehicule, Vehicule entity) {
        Vehicule vehicule = this.findById(idVehicule);

        if (entity.getMarque() == null) {
            vehicule.setMarque(vehicule.getMarque());
        } else {
            vehicule.setMarque(entity.getMarque());
        }

        if (entity.getModele() == null) {
            vehicule.setModele(vehicule.getModele());
        } else {
            vehicule.setModele(entity.getModele());
        }

        if (entity.getImmatriculation() == null) {
            vehicule.setImmatriculation(vehicule.getImmatriculation());
        } else {
            vehicule.setImmatriculation(entity.getImmatriculation());
        }

        if (entity.getImg() == null) {
            vehicule.setImg(vehicule.getImg());
        } else {
            vehicule.setImg(entity.getImg());
        }

        if (entity.getEtat() == null) {
            vehicule.setEtat(vehicule.getEtat());
        } else {
            vehicule.setEtat(entity.getEtat());
        }

        if (entity.getType() == null) {
            vehicule.setType(vehicule.getType());
        } else {
            vehicule.setType(entity.getType());
        }

        if (entity.getPrixJour() == 0) {
            vehicule.setPrixJour(vehicule.getPrixJour());
        } else {
            vehicule.setPrixJour(entity.getPrixJour());
        }

        if (entity.isDisponible() != vehicule.isDisponible()) {
            vehicule.setDisponible(entity.isDisponible());
        } else {
            vehicule.setDisponible(vehicule.isDisponible());
        }

        return this.save(vehicule);
    }

    /**
     * Récupère une liste de vehicules par marque
     * @param marque la marque recherché
     * @return une liste de vehicule(s)
     */
    @Override
    public List<Vehicule> findByMarque(String marque) {
        List<Vehicule> vehiculeList = this.findAll();
        List<Vehicule> vehiculesByMarque = new ArrayList<>();

        for (Vehicule vehicule: vehiculeList) {
            if (vehicule.getMarque().equalsIgnoreCase(marque)) {
                vehiculesByMarque.add(vehicule);
            }
        }

        if (vehiculesByMarque.isEmpty()) {
            logger.warn("Il n'existe aucun locataire à ce nom : " +marque);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'existe aucun vehicule de cette marque "+marque);
        } else {
            return vehiculesByMarque;
        }
    }

    /**
     * Récupère une liste de véhicules par etat
     * @param etat l'état recherché
     * @return une liste de vehicule(s)
     */
    @Override
    public List<Vehicule> findByEtat(VehiculeEtat etat) {
        List<Vehicule> vehiculeList = this.findAll();
        List<Vehicule> vehiculesByEtat = new ArrayList<>();

        for (Vehicule vehicule: vehiculeList){
            if (vehicule.getEtat().equals(etat)) {
                vehiculesByEtat.add(vehicule);
            }
        }

        if (vehiculesByEtat.isEmpty()) {
            logger.warn("Il n'existe aucun vehicule dans cet etat : "+etat);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'existe vehicule dans cet etat : "+etat);
        } else {
            return vehiculesByEtat;
        }
    }

    /**
     * Récupère une liste de véhicules par type
     * @param type le type recherché
     * @return une liste de véhicule(s)
     */
    @Override
    public List<Vehicule> findByType(VehiculeType type) {
        List<Vehicule> vehiculeList = this.findAll();
        List<Vehicule> vehiculesByType = new ArrayList<>();

        for (Vehicule vehicule: vehiculeList){
            if (vehicule.getType().equals(type)) {
                vehiculesByType.add(vehicule);
            }
        }

        if (vehiculesByType.isEmpty()) {
            logger.warn("Il n'existe aucun vehicule de ce type : "+type);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'existe aucun vehicule de ce type : "+type);
        } else {
            return vehiculesByType;
        }
    }

    /**
     * Récupère une liste de véhicules par prix supérieur ou égal au prix donné
     * @param prix le prix recherché
     * @return une liste de véhicule(s)
     */
    @Override
    public List<Vehicule> findByPrixSup(int prix) {
        List<Vehicule> vehiculeList = this.findAll();
        List<Vehicule> vehiculesByPrix = new ArrayList<>();

        for (Vehicule vehicule: vehiculeList){
            if (vehicule.getPrixJour() >= prix) {
                vehiculesByPrix.add(vehicule);
            }
        }

        if (vehiculesByPrix.isEmpty()) {
            logger.warn("Il n'existe aucun vehicule dont le prix est supérieur à : "+prix);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'existe aucun vehicule dont le prix est supérieur à : "+prix);
        } else {
            return vehiculesByPrix;
        }
    }

    /**
     * Récupère une liste de véhicules par prix inférieur ou égal au prix donné
     * @param prix le prix recherché
     * @return une liste de véhicule(s)
     */
    @Override
    public List<Vehicule> findByPrixInf(int prix) {
        List<Vehicule> vehiculeList = this.findAll();
        List<Vehicule> vehiculesByPrix = new ArrayList<>();

        for (Vehicule vehicule: vehiculeList){
            if (vehicule.getPrixJour() <= prix) {
                vehiculesByPrix.add(vehicule);
            }
        }

        if (vehiculesByPrix.isEmpty()) {
            logger.warn("Il n'existe aucun vehicule dont le prix est inférieur à : "+prix);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'existe aucun vehicule dont le prix est inférieur à : "+prix);
        } else {
            return vehiculesByPrix;
        }
    }

    /**
     * Récupère une liste de véhicules par disponibilité
     * @return une liste de véhicule(s)
     */
    @Override
    public List<Vehicule> findByDispo() {
        List<Vehicule> vehiculeList = this.findAll();
        List<Vehicule> vehiculeByDispo = new ArrayList<>();

        for (Vehicule vehicule: vehiculeList){
            if (vehicule.isDisponible()) {
                vehiculeByDispo.add(vehicule);
            }
        }

        if (vehiculeByDispo.isEmpty()) {
            logger.warn("Il n'existe aucun vehicule disponible.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'existe aucun vehicule disponible.");
        } else {
            return vehiculeByDispo;
        }
    }
}
