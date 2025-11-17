package org.example.stockservice.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date; // J'utilise java.util.Date pour être cohérent avec votre entité Company

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stock_quotations") // Un nom de table clair
public class StockMarket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE) // Précise que seule la date (jour/mois/année) nous intéresse
    private Date date;

    // Valeurs de la cotation
    private Double openValue;
    private Double highValue;
    private Double lowValue;
    private Double closeValue;

    // Volume des échanges
    private Long volume; // Long est plus sûr que Integer pour des grands volumes


    private Long companyId;
}