package fr.thcl.formation.ecf.backend.localib.vehicule;

import fr.thcl.formation.ecf.backend.localib.vehicule.Enum.VehiculeEtat;
import fr.thcl.formation.ecf.backend.localib.vehicule.Enum.VehiculeType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vehicules")
public class VehiculeController {

    private final VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @GetMapping("")
    public List<Vehicule> findAll() {
        return vehiculeService.findAll();
    }

    @PostMapping("")
    public Vehicule save(@RequestBody Vehicule entity) {
        return vehiculeService.save(entity);
    }

    @GetMapping("{id}")
    public Vehicule findById(@PathVariable String id) {
        return vehiculeService.findById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        return vehiculeService.deleteById(id);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll() {
        return vehiculeService.deleteAll();
    }

    @GetMapping("/marque")
    public List<Vehicule> findByMarque(@RequestParam String marque) {
        return vehiculeService.findByMarque(marque);
    }

    @GetMapping("/etat")
    public List<Vehicule> findByEtat(@RequestParam VehiculeEtat etat) {
        return vehiculeService.findByEtat(etat);
    }

    @GetMapping("/type")
    public List<Vehicule> findByType(@RequestParam VehiculeType type) {
        return vehiculeService.findByType(type);
    }

    @GetMapping("/prix")
    public List<Vehicule> findByPrix(@RequestParam double prix) {
        return vehiculeService.findByPrix(prix);
    }

    @GetMapping("/dispo")
    public List<Vehicule> findByDispo() {
        return vehiculeService.findByDispo();
    }
}
