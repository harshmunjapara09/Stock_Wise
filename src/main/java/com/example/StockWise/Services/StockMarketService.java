package com.example.StockWise.Services;

import com.example.StockWise.Model.StockOrder;
import com.example.StockWise.Model.User;
import com.example.StockWise.Model.dto.StockData;
import com.example.StockWise.Model.dto.StockDecision;
import com.example.StockWise.Repository.ActivityRepo;
import com.example.StockWise.Repository.UserRepo;
import com.example.StockWise.Services.Utility.ApiCallingMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockMarketService {
    @Autowired
    ActivityRepo activityRepo;
    @Autowired
    UserRepo userRepo;

    public StockData getStockData(String symbol) throws IOException {
        return ApiCallingMethod.apiCallingMethodByStock(symbol);
    }

    public List<StockData> getAllStockInfoData() throws IOException {
        return ApiCallingMethod.getAllStockData();
    }

//    public StockDecision getStockDecisionByStockName(String symbol) throws IOException {
//        return ApiCallingMethod.getStockDecisionByStockName(symbol);
//    }

    public List<StockOrder> getStatement(String email) {
        User user = userRepo.findByUserEmail(email);
        if (user.getStatus().equals("logged in")) {
            return activityRepo.findAllByEmail(email);
        } else {
            return new ArrayList<>();
        }
    }
}
