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

    @PatchMapping("{idVehicule}")
    public Vehicule modifierVehicule(@PathVariable String idVehicule, @RequestBody Vehicule entity) {
        return vehiculeService.modifierVehicule(idVehicule, entity);
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

    @GetMapping("/prixsup")
    public List<Vehicule> findByPrixSup(@RequestParam int prix) {
        return vehiculeService.findByPrixSup(prix);
    }

    @GetMapping("/prixinf")
    public List<Vehicule> findByPrixInf(@RequestParam int prix) {
        return vehiculeService.findByPrixInf(prix);
    }

    @GetMapping("/dispo")
    public List<Vehicule> findByDispo() {
        return vehiculeService.findByDispo();
    }
}
