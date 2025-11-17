package org.example.stockservice.repo;

import org.example.stockservice.entities.StockMarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepo extends JpaRepository<StockMarket, Long> {
    List<StockMarket> findByCompanyId(Long companyId);
}
