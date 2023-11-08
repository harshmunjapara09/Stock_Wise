package com.example.StockWise.Services;

import com.example.StockWise.Model.Portfolio;
import com.example.StockWise.Model.User;
import com.example.StockWise.Model.dto.PortfolioDto;
import com.example.StockWise.Model.dto.StockData;
import com.example.StockWise.Repository.PortfolioRepo;
import com.example.StockWise.Repository.UserRepo;
import com.example.StockWise.Services.Utility.ApiCallingMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
public class PortfolioService {
    @Autowired
    PortfolioRepo portfolioRepo;
    @Autowired
    UserRepo userRepo;

    public PortfolioDto getportfolio(String email) {
        User user = userRepo.findByUserEmail(email);
        PortfolioDto p = new PortfolioDto();
        if (user.getStatus().equals("logged in")) {
            List<Portfolio> list = new ArrayList<>();
            if (!portfolioRepo.existsByEmail(email)) {
                list.add(null);
                p.setProfitAndLoss(0);
                p.setTotalAmount(0);
                p.setInvestedAmount(0);
                p.setStockList(list);
                return p;
            }
            list = portfolioRepo.findByEmail(email);
            p.setStockList(list);
            double totalAmount = 0;
            double investedAmont = 0;
            for (Portfolio portfolio : list) {
                totalAmount += portfolio.getCurrentAmount();
                investedAmont += portfolio.getInvestedAmount();
            }
            p.setTotalAmount(totalAmount);
            p.setInvestedAmount(investedAmont);
            p.setProfitAndLoss((totalAmount - investedAmont));
            return p;
        } else {
            return p;
        }
    }

    @Scheduled(fixedDelay = 60000) // 60000 milliseconds = 1 minute
    public void updateStockPrices() {
        List<Portfolio> portfolios = portfolioRepo.findAll();

        for (Portfolio portfolio : portfolios) {
            try {
                StockData stockData = ApiCallingMethod.apiCallingMethodByStock(portfolio.getSymbol());

                // Update the current price in the portfolio
                portfolio.setCurrentPrice(Double.parseDouble(stockData.getCurrentPrice()));

                // Calculate the current amount based on the updated price
                double currentAmount = portfolio.getCurrentPrice() * portfolio.getQuantity();
                portfolio.setCurrentAmount(currentAmount);

                // Save the updated portfolio
                portfolioRepo.save(portfolio);
            } catch (IOException e) {
                // Handle any exceptions that occur during the API call
                e.printStackTrace();
            }
        }
    }
}