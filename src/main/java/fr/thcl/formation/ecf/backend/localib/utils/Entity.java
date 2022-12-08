package fr.thcl.formation.ecf.backend.localib.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Entity {

    @Id
    protected String id;

    @JsonFormat(pattern =  "dd-MM-yyyy - HH:mm:ss")
    protected LocalDateTime dateCreation;

    @JsonFormat(pattern =  "dd-MM-yyyy - HH:mm:ss")
    protected LocalDateTime dateModification;
}
