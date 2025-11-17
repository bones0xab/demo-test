package org.example.conferenceservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date datereview;
    private String text;
    @Min(value = 1)
    @Max(value = 5)
    private  int score;
    @ManyToOne(fetch = FetchType.LAZY)
    private Conference conference;
}
