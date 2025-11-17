package org.example.stockservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO pour la REQUÊTE (Input) de création d'une cotation.
 * L'ID n'est pas inclus car il est généré par la base de données.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestStockMarketDto implements Serializable {
    private Date date;
    private Double openValue;
    private Double highValue;
    private Double lowValue;
    private Double closeValue;
    private Long volume;
    private Long companyId;
}