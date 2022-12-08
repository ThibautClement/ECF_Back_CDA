package fr.thcl.formation.ecf.backend.localib.locataire;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.thcl.formation.ecf.backend.localib.utils.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Period;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Locataire extends Entity {

    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateDeNaissance;

    public int getAge() {
        return Period.between(dateDeNaissance, LocalDate.now()).getYears();
    }

}
