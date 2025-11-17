package org.example.stockservice.service;

import org.example.stockservice.dtos.RequestStockMarketDto;
import org.example.stockservice.dtos.ResponseStockMarketDto;

import java.util.List;

public interface IStockMarketService {
    ResponseStockMarketDto createStockQuotation(RequestStockMarketDto requestDto);


    void deleteStockQuotation(Long id) throws StockNotFoundException;


    List<ResponseStockMarketDto> getAllStockQuotations();


    ResponseStockMarketDto getStockQuotationById(Long id) throws StockNotFoundException;


    List<ResponseStockMarketDto> getStockQuotationsByCompanyId(Long companyId);
}
