package com.example.StockWise.Controller;

import com.example.StockWise.Model.dto.StockActivity;
import com.example.StockWise.Model.dto.StockOrderRequest;
import com.example.StockWise.Services.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/activity")
@Tag(name = "Stock Transactions", description = "Execute secure stock transactions, including buying and selling stocks, to manage your investment portfolio efficiently and effectively..")
public class ActivityController {

    @Autowired
    ActivityService activityService;
    @PostMapping("/buyStock")
    @Operation(
            summary = "Stock Buy",
            description = "Securely purchase stocks through this API, ensuring a reliable investment experience.",
            tags = {"Stock Transactions"})
    private StockActivity BuyStock(@RequestBody StockOrderRequest order, @RequestParam String email) throws  IOException {
        return activityService.BuyStock(order,email);
    }

    @PostMapping("/sellStock")
    @Operation(
            summary = "Stock Sell",
            description = "Securely sell stocks with confidence through this API for investors.",
            tags = {"Stock Transactions"})
    private StockActivity SellStock(@RequestBody StockOrderRequest order,@RequestParam String email) throws IOException{
        return activityService.SellStock(order,email);
    }



}
