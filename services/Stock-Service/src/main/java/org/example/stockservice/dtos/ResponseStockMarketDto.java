package org.example.stockservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStockMarketDto implements Serializable {
    private Long id;
    private Date date;
    private Double openValue;
    private Double highValue;
    private Double lowValue;
    private Double closeValue;
    private Long volume;
    private Long companyId;
}