package fr.thcl.formation.ecf.backend.localib.vehicule;

import fr.thcl.formation.ecf.backend.localib.utils.Entity;
import fr.thcl.formation.ecf.backend.localib.vehicule.Enum.VehiculeEtat;
import fr.thcl.formation.ecf.backend.localib.vehicule.Enum.VehiculeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule extends Entity {

    private String marque;
    private String modele;
    private String immatriculation;
    private String img;

    private VehiculeEtat etat;
    private VehiculeType type;

    private double prixJour;
    private boolean disponible = true;


}
