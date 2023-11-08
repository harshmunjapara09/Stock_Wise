package com.example.StockWise.Model.dto;

import lombok.Data;

@Data
public class StockDecision {
    private String stockName;
    private String currentPrice;
    private String recommendation;

}
