package org.example.stockservice.mapper;


import org.example.stockservice.dtos.RequestStockMarketDto;
import org.example.stockservice.dtos.ResponseStockMarketDto;
import org.example.stockservice.entities.StockMarket;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StockeMapper {
    private final ModelMapper modelMapper;

    // ModelMapper est injecté (vous devez le définir comme @Bean dans un fichier @Configuration)
    public StockeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StockMarket dtoToEntity(RequestStockMarketDto dto) {
        // Les noms de champs correspondent à 100%, ModelMapper fait tout
        return modelMapper.map(dto, StockMarket.class);
    }

    /**
     * Traduit l'Entité en DTO de Réponse
     */
    public ResponseStockMarketDto entityToDto(StockMarket stock) {
        // Les noms de champs correspondent à 100%, ModelMapper fait tout
        return modelMapper.map(stock, ResponseStockMarketDto.class);
    }

}
