package org.example.companyservice.repos;

import org.example.companyservice.dtos.RequestCompanyDto;
import org.example.companyservice.dtos.ResponseCompanyDto;
import org.example.companyservice.entities.Company;
import org.example.companyservice.enums.Domain;
import org.example.companyservice.exception.CompanyNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {

    List<Company> findByDomain(Domain domain);
}
