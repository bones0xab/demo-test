package org.example.conferenceservice.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link org.example.conferenceservice.entities.Review}
 */
@Getter
@Setter
@NoArgsConstructor // <-- La solution ! Crée le constructeur vide pour ModelMapper
@AllArgsConstructor
public class RequestReviewDto implements Serializable {

    @NotNull(message = "Review text must not be null")
    String text;
    @NotNull(message = "Score obligatoire")
    @Min(1)
    @Max(5)
    int score;

    @NotNull(message = "Review text must not be null")
    Long conferenceId;

}