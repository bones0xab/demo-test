package org.example.stockservice.service;

import org.example.stockservice.dtos.RequestStockMarketDto;
import org.example.stockservice.dtos.ResponseStockMarketDto;
import org.example.stockservice.entities.StockMarket;
import org.example.stockservice.exception.StockNotFoundException;
import org.example.stockservice.feign.CompanyServiceClient;
import org.example.stockservice.mapper.StockeMapper;
import org.example.stockservice.repo.StockRepo;
import org.example.stockservice.service.IStockMarketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StockMarketServiceImpl implements IStockMarketService {

    private final StockRepo stockRepo;
    private final StockeMapper stockMapper;
    private final CompanyServiceClient companyClient; // 4. INJECTER Feign

    // Un logger pour voir ce qui se passe
    private static final Logger log = LoggerFactory.getLogger(StockMarketServiceImpl.class);

    public StockMarketServiceImpl(StockRepo stockRepo, StockeMapper stockMapper,CompanyServiceClient companyClient) {
        this.stockRepo = stockRepo;
        this.stockMapper = stockMapper;
        this.companyClient = companyClient;
    }

    /**
     * Spécification 1 ET 3: Ajouter une cotation ET Mettre à jour le prix
     */
    @Override
    public ResponseStockMarketDto createStockQuotation(RequestStockMarketDto requestDto) {
        StockMarket savedStock = stockRepo.save(stockMapper.dtoToEntity(requestDto));

        Long companyId = savedStock.getCompanyId();
        Double newPrice = savedStock.getCloseValue();

        log.info("Appel de company-service via OpenFeign...");

        try {
            // 6. C'EST LE NOUVEAU CODE (PROPRE)
            // Plus d'URL. Juste un appel de méthode Java.
            companyClient.updateCompanyPrice(companyId, newPrice);

            log.info("Mise à jour du prix réussie pour l'entreprise ID: {}", companyId);
        } catch (Exception e) {
            log.error("ÉCHEC de l'appel OpenFeign : {}", e.getMessage());
        }

        return stockMapper.entityToDto(savedStock);
    }

    /**
     * Spécification 2: Supprimer une cotation
     */
    @Override
    public void deleteStockQuotation(Long id) {
        if (!stockRepo.existsById(id)) {
            throw new StockNotFoundException("Cotation introuvable avec l'ID: " + id);
        }
        stockRepo.deleteById(id);
    }


    @Override
    public List<ResponseStockMarketDto> getAllStockQuotations() {
        return stockRepo.findAll().stream()
                .map(stockMapper::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Spécification 5: Consulter une cotation par son id
     */
    @Override
    public ResponseStockMarketDto getStockQuotationById(Long id) {
        StockMarket stock = stockRepo.findById(id)
                .orElseThrow(() -> new StockNotFoundException("Cotation introuvable avec l'ID: " + id));
        return stockMapper.entityToDto(stock);
    }

    /**
     * Fonctionnalité bonus: Consulter l'historique d'une entreprise
     */
    @Override
    public List<ResponseStockMarketDto> getStockQuotationsByCompanyId(Long companyId) {
        // Utilisation de notre méthode personnalisée du Repository
        List<StockMarket> stocks = stockRepo.findByCompanyId(companyId);

        return stocks.stream()
                .map(stockMapper::entityToDto)
                .collect(Collectors.toList());
    }
}