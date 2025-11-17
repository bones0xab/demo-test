package org.example.conferenceservice.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.conferenceservice.enums.Type;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor // <-- La solution ! Crée le constructeur vide pour ModelMapper
@AllArgsConstructor
public class RequesrtConferenceDto implements Serializable {
    @NotNull(message = "Title must not be null")
    String titre;
    @NotNull(message = "Type must not be null")
    Type type;

    @NotNull(message = "Date must not be null")
    @Future(message = "Date must be in the future")
    Date date;
    int duree;
}