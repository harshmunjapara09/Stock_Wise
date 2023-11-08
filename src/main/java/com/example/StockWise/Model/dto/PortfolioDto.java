package com.example.StockWise.Model.dto;

import com.example.StockWise.Model.Portfolio;
import lombok.Data;

import java.util.List;

@Data
public class PortfolioDto {
    private double totalAmount;
    private double investedAmount;
    private double profitAndLoss;
    private List<Portfolio> StockList;
}
