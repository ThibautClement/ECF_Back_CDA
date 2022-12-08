package fr.thcl.formation.ecf.backend.localib.location;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.thcl.formation.ecf.backend.localib.locataire.Locataire;
import fr.thcl.formation.ecf.backend.localib.utils.Entity;
import fr.thcl.formation.ecf.backend.localib.vehicule.Vehicule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.Period;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Location extends Entity {

    @DBRef
    private Locataire locataire;
    @DBRef
    private Vehicule vehicule;

    @JsonFormat(pattern =  "dd/MM/yyyy")
    private LocalDate dateDebut = LocalDate.now();
    @JsonFormat(pattern =  "dd/MM/yyyy")
    private LocalDate dateFin;

    public int getJoursLocation() {
        return Period.between(dateDebut, dateFin).getDays();
    }

    public double getPrixLocation() {
        return Period.between(dateDebut, dateFin).getDays()*vehicule.getPrixJour();
    }
}
