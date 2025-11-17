package org.example.keynoteservice.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor // <-- La solution ! Crée le constructeur vide pour ModelMapper
@AllArgsConstructor
public class RequestKeynoteDto implements Serializable {

    @NotNull(message = "Nom must not be null")
    String nom;
    @NotNull(message = "Prenom must not be null")
    String prenom;
    @NotNull(message = "Email must not be null")
    String email;
    @NotNull(message = "Fonction must not be null")
    String fonction;
}