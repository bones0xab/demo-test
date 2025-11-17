package org.example.companyservice.service;

import org.example.companyservice.dtos.RequestCompanyDto;
import org.example.companyservice.dtos.ResponseCompanyDto;
import org.example.companyservice.entities.Company;
import org.example.companyservice.enums.Domain;
import org.example.companyservice.exception.CompanyNotFoundException;
import org.example.companyservice.mapper.CompanyMapper;
import org.example.companyservice.repos.CompanyRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional // Bonne pratique : rend les méthodes transactionnelles
public class CompanyImpl implements CompanyInter {

    // Les "outils" de l'ingénieur : Repo et Mapper
    private final CompanyRepo companyRepo;
    private final CompanyMapper companyMapper;

    // Injection par constructeur (la meilleure pratique)
    public CompanyImpl(CompanyRepo companyRepo, CompanyMapper companyMapper) {
        this.companyRepo = companyRepo;
        this.companyMapper = companyMapper;
    }

    /**
     * Spécification 1: Enregistrer une entreprise
     */
    @Override
    public ResponseCompanyDto createCompany(RequestCompanyDto requestDto) {
        // 1. Traduire le DTO en Entité
        Company company = companyMapper.dtoToEntity(requestDto);

        // 2. Sauvegarder l'entité
        Company savedCompany = companyRepo.save(company);

        // 3. Renvoyer le DTO de réponse
        return companyMapper.entityToDto(savedCompany);
    }

    /**
     * Spécification 2: Supprimer une entreprise
     */
    @Override
    public void deleteCompany(Long id) {
        // D'abord, vérifier si l'ID existe (approche pro)
        if (!companyRepo.existsById(id)) {
            throw new CompanyNotFoundException("Impossible de supprimer : Entreprise introuvable avec l'ID: " + id);
        }
        companyRepo.deleteById(id);
    }

    /**
     * Spécification 3: Mettre à jour le prix de l'action
     */
    @Override
    public ResponseCompanyDto updateCompanyPrice(Long id, Double newPrice) {
        // 1. Trouver l'entreprise (ou lever une exception si non trouvée)
        Company company = companyRepo.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Mise à jour échouée : Entreprise introuvable avec l'ID: " + id));

        // 2. Appliquer la logique métier (mise à jour du prix)
        company.setPrixActuelle(newPrice);

        // 3. Sauvegarder (JPA/Hibernate est assez intelligent pour faire un UPDATE)
        Company updatedCompany = companyRepo.save(company);

        // 4. Renvoyer le DTO mis à jour
        return companyMapper.entityToDto(updatedCompany);
    }

    /**
     * Spécification 4: Consulter toutes les entreprises
     */
    @Override
    public List<ResponseCompanyDto> getAllCompanies() {
        List<Company> companies = companyRepo.findAll();

        // Mapper la liste d'Entités en liste de DTOs (approche pro)
        return companies.stream()
                .map(companyMapper::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Spécification 5: Consulter une entreprise par son id
     */
    @Override
    public ResponseCompanyDto getCompanyById(Long id) {
        // Trouver (ou lever l'exception 404)
        Company company = companyRepo.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Entreprise introuvable avec l'ID: " + id));

        return companyMapper.entityToDto(company);
    }

    /**
     * Spécification 6: Consulter les entreprises d'un domaine
     */
    @Override
    public List<ResponseCompanyDto> getCompaniesByDomain(Domain domain) {
        // 1. Utiliser notre méthode personnalisée du Repository
        List<Company> companies = companyRepo.findByDomain(domain);

        // 2. Mapper la liste
        return companies.stream()
                .map(companyMapper::entityToDto)
                .collect(Collectors.toList());
    }
}