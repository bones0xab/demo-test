package org.example.stockservice.repo;

import org.example.stockservice.entities.StockMarket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepo extends JpaRepository<StockMarket, Long> {
}
