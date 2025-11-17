package org.example.conferenceservice.dtos;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor // <-- La solution ! Crée le constructeur vide pour ModelMapper
@AllArgsConstructor
public class ResponseReviewDto implements Serializable {
    Long id;
    Date datereview;
    String text;
    int score;
    Long conferenceId;
}