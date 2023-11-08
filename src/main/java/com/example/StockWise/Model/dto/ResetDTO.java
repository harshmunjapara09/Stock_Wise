package com.example.StockWise.Model.dto;

import lombok.Data;

@Data
public class ResetDTO {
    String email;
    String otp;
    String newPass;
}
