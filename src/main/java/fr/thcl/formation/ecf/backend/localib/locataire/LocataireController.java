package fr.thcl.formation.ecf.backend.localib.locataire;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/locataires")
public class LocataireController {

    private final LocataireService locataireService;

    public LocataireController(LocataireService locataireService) {
        this.locataireService = locataireService;
    }

    @GetMapping("")
    public List<Locataire> findAll() {
        return locataireService.findAll();
    }

    @PostMapping("")
    public Locataire save(@RequestBody Locataire entity) {
        return locataireService.save(entity);
    }

    @GetMapping("{id}")
    public Locataire findById(@PathVariable String id) {
        return locataireService.findById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        return locataireService.deleteById(id);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll() {
        return locataireService.deleteAll();
    }

    @PatchMapping("{idLocataire}")
    public Locataire modifierLocataire(@PathVariable String idLocataire, @RequestBody Locataire entity) {
        return locataireService.modifierLocataire(idLocataire, entity);
    }

    @GetMapping("/name")
    public List<Locataire> findByName(@RequestParam String nom) {
        return locataireService.findByName(nom);
    }

    @GetMapping("/birthdate")
    public List<Locataire> findByDateDeNaissance(@RequestParam LocalDate dateDeNaissance) {
        return locataireService.findByDateDeNaissance(dateDeNaissance);
    }
}
