package org.example.companyservice.web;

import jakarta.validation.Valid;
import org.example.companyservice.dtos.RequestCompanyDto;
import org.example.companyservice.dtos.ResponseCompanyDto;
import org.example.companyservice.enums.Domain;
import org.example.companyservice.service.CompanyInter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies") // 1. URL de base pour la ressource "Company"
public class CompanyController {

    // 2. Injecter L'INTERFACE (le "contrat"), jamais l'implémentation
    private final CompanyInter companyService;

    public CompanyController(CompanyInter companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<ResponseCompanyDto> getAllCompanies() {
        return companyService.getAllCompanies();
    }


    @GetMapping("/{id}")
    public ResponseCompanyDto getCompanyById(@PathVariable Long id) {
        // Lève 404 automatiquement grâce à votre @ResponseStatus sur l'exception
        return companyService.getCompanyById(id);
    }


    @GetMapping("/search") // L'approche "pro" pour une recherche
    public List<ResponseCompanyDto> getCompaniesByDomain(@RequestParam Domain domain) {
        return companyService.getCompaniesByDomain(domain);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Renvoie 201 (Created)
    public ResponseCompanyDto createCompany(@Valid @RequestBody RequestCompanyDto requestDto) {
        // @Valid active la validation sur votre DTO (si vous en ajoutez)
        return companyService.createCompany(requestDto);
    }


    @PutMapping("/{id}/price") // Cible une ressource spécifique (le prix)
    public ResponseCompanyDto updateCompanyPrice(@PathVariable Long id, @RequestParam Double newPrice) {
        return companyService.updateCompanyPrice(id, newPrice);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Renvoie 204 (No Content)
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
}