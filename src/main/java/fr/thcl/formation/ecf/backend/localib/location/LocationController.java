package fr.thcl.formation.ecf.backend.localib.location;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("")
    public List<Location> findAll() {
        return locationService.findAll();
    }

    @PostMapping("")
    public Location save(@RequestBody Location entity) {
        return locationService.save(entity);
    }

    @GetMapping("{id}")
    public Location findById(@PathVariable String id) {
        return locationService.findById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        return locationService.deleteById(id);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll() {
        return locationService.deleteAll();
    }

    @GetMapping("/locataires/{idLocataire}")
    public List<Location> findByLocataire(@PathVariable String idLocataire) {
        return locationService.findByLocataire(idLocataire);
    }

    @GetMapping("/locataires")
    public List<Location> findByLocataireName(@RequestParam String nom) {
        return locationService.findByLocataireName(nom);
    }

    @GetMapping("/vehicules/{idVehicule}")
    public List<Location> findByVehicule(@PathVariable String idVehicule) {
        return locationService.findByVehicule(idVehicule);
    }

    @GetMapping("/vehicules")
    public List<Location> findByVehiculeMarque(@RequestParam String marque) {
        return locationService.findByVehiculeMarque(marque);
    }

    @GetMapping("/dated")
    public List<Location> findByDateDebut(@RequestParam LocalDate dateDebut) {
        return locationService.findByDateDebut(dateDebut);
    }
    
    @GetMapping("/datef")
    public List<Location> findByDateFin(@RequestParam LocalDate dateFin) {
        return locationService.findByDateFin(dateFin);
    }
}
