package com.example.StockWise.Controller;

import com.example.StockWise.Model.Portfolio;
import com.example.StockWise.Model.dto.PortfolioDto;
import com.example.StockWise.Services.PortfolioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
@Tag(name = "Portfolio", description = "Manage your investment portfolio efficiently with secure stock transactions, including buying and selling stocks, all in one place.")
public class PortfolioController {
    @Autowired
    PortfolioService portfolioService;

    @GetMapping("/get")
    @Operation(
            summary = "Portfolio",
            description = "Securely purchase stocks through this API, ensuring a reliable investment experience.",
            tags = {"Stock Transactions"})
    private PortfolioDto getportfolio(@RequestParam String email) {
        return portfolioService.getportfolio(email);
    }

}
