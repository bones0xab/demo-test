package org.example.companyservice.mapper;


import org.example.companyservice.dtos.RequestCompanyDto;
import org.example.companyservice.dtos.ResponseCompanyDto;
import org.example.companyservice.entities.Company;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    private final ModelMapper modelMapper;

    public CompanyMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public Company dtoToEntity(RequestCompanyDto dto) {
        return modelMapper.map(dto, Company.class);
    }


    public ResponseCompanyDto entityToDto(Company company) {
        return modelMapper.map(company, ResponseCompanyDto.class);
    }
}
