package org.example.companyservice.dtos;

import lombok.*;
import org.example.companyservice.enums.Domain;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCompanyDto implements Serializable {
    Long id;
    String name;
    Date dateintroduction;
    Double prixActuelle;
    Domain domain;
}