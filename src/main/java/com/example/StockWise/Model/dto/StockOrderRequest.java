package com.example.StockWise.Model.dto;

import lombok.Data;

@Data
public class StockOrderRequest {
    private String stockName;
    private int quantity;
}
