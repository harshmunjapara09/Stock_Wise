package com.example.StockWise.Repository;

import com.example.StockWise.Model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepo extends JpaRepository<Portfolio,Long> {

    boolean existsByEmailAndSymbol(String email, String stockName);

    Portfolio findByEmailAndSymbol(String email, String stockName);

    List<Portfolio> findByEmail(String email);

    boolean existsByEmail(String email);
}
