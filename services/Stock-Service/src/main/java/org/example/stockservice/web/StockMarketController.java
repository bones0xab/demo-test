package org.example.stockservice.web;


import jakarta.validation.Valid;
import org.example.stockservice.dtos.RequestStockMarketDto;
import org.example.stockservice.dtos.ResponseStockMarketDto;
import org.example.stockservice.service.IStockMarketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks") // 1. URL de base pour la ressource "Cotations"
public class StockMarketController {

    // 2. Injecter L'INTERFACE (le "contrat"), jamais l'implémentation
    private final IStockMarketService stockMarketService;

    public StockMarketController(IStockMarketService stockMarketService) {
        this.stockMarketService = stockMarketService;
    }

    /**
     * Spécification 4: Consulter toutes les cotations
     * GET /api/stocks
     */
    @GetMapping
    public List<ResponseStockMarketDto> getAllStockQuotations() {
        return stockMarketService.getAllStockQuotations();
    }

    /**
     * Spécification 5: Consulter une cotation par son id
     * GET /api/stocks/{id}
     */
    @GetMapping("/{id}")
    public ResponseStockMarketDto getStockQuotationById(@PathVariable Long id) {
        // Lève 404 automatiquement grâce à votre @ResponseStatus sur l'exception
        return stockMarketService.getStockQuotationById(id);
    }

    /**
     * Fonctionnalité bonus: Consulter les cotations par ID d'entreprise
     * GET /api/stocks/company/{companyId}
     */
    @GetMapping("/company/{companyId}")
    public List<ResponseStockMarketDto> getStockQuotationsByCompanyId(@PathVariable Long companyId) {
        return stockMarketService.getStockQuotationsByCompanyId(companyId);
    }

    /**
     * Spécification 1: Ajouter une cotation
     * (Et déclencher la Spécification 3 par la même occasion)
     * POST /api/stocks
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Renvoie 201 (Created)
    public ResponseStockMarketDto createStockQuotation(@Valid @RequestBody RequestStockMarketDto requestDto) {
        // @Valid active la validation sur votre DTO (si vous en ajoutez)
        return stockMarketService.createStockQuotation(requestDto);
    }

    /**
     * Spécification 2: Supprimer une cotation
     * DELETE /api/stocks/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Renvoie 204 (No Content)
    public void deleteStockQuotation(@PathVariable Long id) {
        stockMarketService.deleteStockQuotation(id);
    }
}