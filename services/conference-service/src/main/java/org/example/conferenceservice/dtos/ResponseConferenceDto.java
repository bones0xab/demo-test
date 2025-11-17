package org.example.conferenceservice.dtos;

import lombok.*;
import org.example.conferenceservice.enums.Type;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor // <-- La solution ! Crée le constructeur vide pour ModelMapper
@AllArgsConstructor
public class ResponseConferenceDto implements Serializable {
    Long id;
    String titre;
    Type type;
    Date date;
    int duree;
    int nbrinscripts;
    double score;
}