package com.example.StockWise.Controller;


import com.example.StockWise.Model.StockOrder;
import com.example.StockWise.Model.dto.StockData;
import com.example.StockWise.Model.dto.StockDecision;
import com.example.StockWise.Services.StockMarketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@Tag(name = "Stock Information", description = "Access real-time stock data, make stock decisions, and review your stock transaction statements with ease.")
public class StockController {

    @Autowired
    StockMarketService stockMarketService;

    @GetMapping("/getInfoStockByName")
    @Operation(
            summary = "Get Stock Data",
            description = "Retrieve real-time data for a specific stock symbol. Access key information about the stock market efficiently.",
            tags = {"Stock Information"}
    )
    public StockData getStockData(@RequestParam String symbol) throws IOException {
        return stockMarketService.getStockData(symbol);
    }

    @GetMapping("/getAllStockInfo")
    @Operation(
            summary = "Get All Stock Information",
            description = "Retrieve comprehensive information about all available stocks. Stay informed about the stock market with ease.",
            tags = {"Stock Information"}
    )
    public List<StockData> getAllStockInfoData() throws IOException {
        return stockMarketService.getAllStockInfoData();
    }

//    @GetMapping("/stockDecisionByStockName")
//    @Operation(
//            summary = "Get Stock Decision by Stock Name",
//            description = "Retrieve stock decision information for a specific stock symbol. Make informed investment choices.",
//            tags = {"Stock Information"}
//    )
//    public StockDecision getStockDecisionByStockName(String symbol) throws IOException {
//        return stockMarketService.getStockDecisionByStockName(symbol);
//    }

    @GetMapping("/statement")
    @Operation(
            summary = "Get Statement",
            description = "Retrieve your stock transaction statement. Review your investment portfolio and transaction history securely.",
            tags = {"Stock Information"}
    )
    public List<StockOrder> getStatement(String email)  {
        return stockMarketService.getStatement(email);
    }

}