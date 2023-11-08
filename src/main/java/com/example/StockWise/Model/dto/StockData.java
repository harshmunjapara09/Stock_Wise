package com.example.StockWise.Model.dto;

import lombok.Data;

@Data
public class StockData {
    private String symbol;
    private String currentPrice;
    private String dayHigh;
    private String dayLow;
    private String percentChange;
    private String lastUpdatedTime;
}
