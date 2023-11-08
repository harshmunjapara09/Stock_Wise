package com.example.StockWise.Model.dto;

import lombok.Data;

@Data
public class StockActivity {
    private String stockName;
    private double stockPrice;
    private int quantity;
    private double totalAmount;
    private String status;
    private String messege;
}
