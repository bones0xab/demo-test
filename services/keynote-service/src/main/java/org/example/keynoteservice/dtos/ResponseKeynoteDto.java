package org.example.keynoteservice.dtos;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor // <-- La solution ! Crée le constructeur vide pour ModelMapper
@AllArgsConstructor
public class ResponseKeynoteDto implements Serializable {
    Long id;
    String nom;
    String prenom;
    String email;
    String fonction;
}