package org.example.conferenceservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.conferenceservice.enums.Type;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Conference {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private Type type;
    private Date date;
    private int duree;
    private int nbrinscripts;
    private double score;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;

}
