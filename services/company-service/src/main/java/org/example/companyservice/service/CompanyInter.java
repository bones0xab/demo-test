package org.example.companyservice.service;

import org.example.companyservice.dtos.RequestCompanyDto;
import org.example.companyservice.dtos.ResponseCompanyDto;
import org.example.companyservice.enums.Domain;
import org.example.companyservice.exception.CompanyNotFoundException;

import java.util.List;

public interface CompanyInter {
    ResponseCompanyDto createCompany(RequestCompanyDto requestDto);


    void deleteCompany(Long id) throws CompanyNotFoundException;


    ResponseCompanyDto updateCompanyPrice(Long id, Double newPrice) throws CompanyNotFoundException;


    List<ResponseCompanyDto> getAllCompanies();


    ResponseCompanyDto getCompanyById(Long id) throws CompanyNotFoundException;

    List<ResponseCompanyDto> getCompaniesByDomain(Domain domain);

}
