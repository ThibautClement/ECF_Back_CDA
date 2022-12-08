package fr.thcl.formation.ecf.backend.localib.utils;

import fr.thcl.formation.ecf.backend.localib.locataire.Locataire;
import fr.thcl.formation.ecf.backend.localib.locataire.LocataireService;
import fr.thcl.formation.ecf.backend.localib.location.Location;
import fr.thcl.formation.ecf.backend.localib.location.LocationService;
import fr.thcl.formation.ecf.backend.localib.vehicule.Enum.VehiculeEtat;
import fr.thcl.formation.ecf.backend.localib.vehicule.Enum.VehiculeType;
import fr.thcl.formation.ecf.backend.localib.vehicule.Vehicule;
import fr.thcl.formation.ecf.backend.localib.vehicule.VehiculeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("debug")
public class DebugController {

    private final LocataireService locataireService;
    private final VehiculeService vehiculeService;

    private final LocationService locationService;

    public DebugController(LocataireService locataireService, VehiculeService vehiculeService, LocationService locationService) {
        this.locataireService = locataireService;
        this.vehiculeService = vehiculeService;
        this.locationService = locationService;
    }

    @DeleteMapping("clear")
    public void clear() {
        locataireService.deleteAll();
        vehiculeService.deleteAll();
        locationService.deleteAll();
    }


    @PostMapping("init")
    public void init(){
        clear();
        Locataire locataire1 = this.locataireService.save(new Locataire(
                "Clément",
                "Thibaut",
                "0644167471",
                "thibaut.clement@gmail.com",
                LocalDate.of(1993,2,6)
        ));
        Locataire locataire2 = this.locataireService.save(new Locataire(
                "Flouquet",
                "Florian",
                "0648759165",
                "florian.flouquet@gmail.com",
                LocalDate.of(1997,5,12)
        ));
        Locataire locataire3 = this.locataireService.save(new Locataire(
                "Touzard",
                "Valerian",
                "0641597863",
                "valerian.touzard@gmail.com",
                LocalDate.of(1999,11,27)
        ));
        Vehicule vehicule1 = this.vehiculeService.save(new Vehicule(
                "BMW",
                "Serie 1",
                "45-CWS-76",
                "/bmw.png",
                VehiculeEtat.BON,
                VehiculeType.VOITURE,
                75.5,
                true
        ));
        Vehicule vehicule2 = this.vehiculeService.save(new Vehicule(
                "Mercedes",
                "CLS",
                "76-SJD-42",
                "/merco.png",
                VehiculeEtat.MOYEN,
                VehiculeType.VOITURE,
                95.5,
                false
        ));
        Vehicule vehicule3 = this.vehiculeService.save(new Vehicule(
                "Peugeot",
                "205",
                "79-AMD-14",
                "/peugeot.png",
                VehiculeEtat.MAUVAIS,
                VehiculeType.VOITURE,
                45.5,
                true
        ));
        Location location1 = this.locationService.save(new Location(
                locataire1,
                vehicule1,
                LocalDate.now(),
                LocalDate.of(2022, 12, 16)
        ));
        Location location2 = this.locationService.save(new Location(
                locataire2,
                vehicule2,
                LocalDate.now(),
                LocalDate.of(2022, 12, 14)
        ));
        Location location3 = this.locationService.save(new Location(
                locataire3,
                vehicule3,
                LocalDate.now(),
                LocalDate.of(2022, 12, 18)
        ));
    }
}
