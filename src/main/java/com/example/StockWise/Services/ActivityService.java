package com.example.StockWise.Services;

import com.example.StockWise.Model.Portfolio;
import com.example.StockWise.Model.StockOrder;
import com.example.StockWise.Model.User;
import com.example.StockWise.Model.dto.StockActivity;
import com.example.StockWise.Model.dto.StockData;
import com.example.StockWise.Model.dto.StockOrderRequest;
import com.example.StockWise.Repository.ActivityRepo;
import com.example.StockWise.Repository.PortfolioRepo;
import com.example.StockWise.Repository.UserRepo;
import com.example.StockWise.Services.Utility.ApiCallingMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class ActivityService {
    @Autowired
    ActivityRepo activityRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    PortfolioRepo portfolioRepo;

    public StockActivity BuyStock(StockOrderRequest order, String email) throws IOException {
        User user = userRepo.findByUserEmail(email);
        StockData stockData = ApiCallingMethod.apiCallingMethodByStock(order.getStockName());
        StockOrder stockOrder = new StockOrder();
        StockActivity stockActivity = new StockActivity();
        double totalamount = order.getQuantity() * Double.parseDouble(stockData.getCurrentPrice());


        //this code will save all the details in stockorder table
        if (user.getStatus().equals("logged in")) {
            if (user.getFund() > totalamount) {
                stockOrder.setEmail(email);
                stockOrder.setStockName(order.getStockName());
                stockOrder.setStockPrice(Double.parseDouble(stockData.getCurrentPrice()));
                stockOrder.setQuantity(order.getQuantity());
                stockOrder.setUserName(user.getUserName());
                stockOrder.setTotalAmount(totalamount);
                stockOrder.setStatus("BUY");
                activityRepo.save(stockOrder);
                user.setFund(user.getFund() - totalamount);
                userRepo.save(user);
// this code for response
                stockActivity.setTotalAmount(totalamount);
                stockActivity.setStockName(order.getStockName());
                stockActivity.setQuantity(order.getQuantity());
                stockActivity.setStatus("BUY");
                stockActivity.setMessege("Success");
                stockActivity.setStockPrice(Double.parseDouble(stockData.getCurrentPrice()));

                if (!portfolioRepo.existsByEmailAndSymbol(email, stockActivity.getStockName())) {
                    Portfolio portfolio = new Portfolio();


                    portfolio.setEmail(email);
                    portfolio.setBuyPrice(stockActivity.getStockPrice());
                    portfolio.setSymbol(stockActivity.getStockName());
                    portfolio.setQuantity(stockActivity.getQuantity());
                    portfolio.setInvestedAmount(totalamount);
                    portfolio.setCurrentPrice(Double.parseDouble(stockData.getCurrentPrice()));
                    portfolio.setCurrentAmount(Double.parseDouble(stockData.getCurrentPrice()) * stockActivity.getQuantity());
                    portfolioRepo.save(portfolio);
                } else {
                    Portfolio portfolio = portfolioRepo.findByEmailAndSymbol(email, stockActivity.getStockName());
                    double currentTotalCost = portfolio.getBuyPrice() * portfolio.getQuantity();

                    double newSharesTotalCost = stockActivity.getStockPrice() * stockActivity.getQuantity();
                    portfolio.setId(portfolio.getId());
                    portfolio.setBuyPrice((currentTotalCost + newSharesTotalCost) / (portfolio.getQuantity() + stockActivity.getQuantity()));
                    portfolio.setSymbol(stockActivity.getStockName());
                    portfolio.setQuantity(portfolio.getQuantity() + stockActivity.getQuantity());
                    portfolio.setInvestedAmount(portfolio.getInvestedAmount() + totalamount);
                    portfolio.setCurrentAmount(Double.parseDouble(stockData.getCurrentPrice()) * (portfolio.getQuantity() + stockActivity.getQuantity()));
                    portfolio.setCurrentPrice(Double.parseDouble(stockData.getCurrentPrice()));
                    portfolioRepo.save(portfolio);
                }


                return stockActivity;
            } else {
                stockActivity.setTotalAmount(0.0);
                stockActivity.setStockName(null);
                stockActivity.setQuantity(0);
                stockActivity.setStatus("Error");
                stockActivity.setMessege("Insufficient Balance");
                stockActivity.setStockPrice(0);
                return stockActivity;

            }
        }
        return stockActivity;
    }

    public StockActivity SellStock(StockOrderRequest order, String email) throws IOException {
        StockOrder stockOrder = new StockOrder();
        User user = userRepo.findByUserEmail(email);
        StockData stockData = ApiCallingMethod.apiCallingMethodByStock(order.getStockName());
        StockActivity stockActivity = new StockActivity();
        if (user.getStatus().equals("logged in")) {
            if (!portfolioRepo.existsByEmailAndSymbol(email, order.getStockName())) {
                stockActivity.setTotalAmount(0.0);
                stockActivity.setStockName(null);
                stockActivity.setQuantity(0);
                stockActivity.setStatus("Error");
                stockActivity.setMessege("Insufficient Stock");
                stockActivity.setStockPrice(0);
                return stockActivity;
            } else {
                Portfolio portfolio = portfolioRepo.findByEmailAndSymbol(email, order.getStockName());
                if (portfolio.getQuantity() == order.getQuantity()) {
                    stockActivity.setTotalAmount(Double.parseDouble(stockData.getCurrentPrice())*order.getQuantity());
                    stockActivity.setStockName(portfolio.getSymbol());
                    stockActivity.setQuantity(order.getQuantity());
                    stockActivity.setStatus("SELL");
                    stockActivity.setMessege("Sold Successfully");
                    stockActivity.setStockPrice(Double.parseDouble(stockData.getCurrentPrice()));

                    stockOrder.setEmail(email);
                    stockOrder.setStockName(order.getStockName());
                    stockOrder.setStockPrice(Double.parseDouble(stockData.getCurrentPrice()));
                    stockOrder.setQuantity(order.getQuantity());
                    stockOrder.setUserName(user.getUserName());
                    stockOrder.setTotalAmount(Double.parseDouble(stockData.getCurrentPrice()) * order.getQuantity());
                    stockOrder.setStatus("SELL");

                    activityRepo.save(stockOrder);
                    user.setFund(user.getFund() + stockOrder.getTotalAmount());
                    userRepo.save(user);

                    portfolioRepo.delete(portfolio);
                    return stockActivity;

                }
                portfolio.setEmail(email);
                portfolio.setSymbol(order.getStockName());
                portfolio.setCurrentPrice(Double.parseDouble(stockData.getCurrentPrice()) * order.getQuantity());
                portfolio.setInvestedAmount(portfolio.getInvestedAmount() - (portfolio.getBuyPrice() * order.getQuantity()));
                portfolio.setQuantity(portfolio.getQuantity() - order.getQuantity());
                portfolio.setBuyPrice(portfolio.getBuyPrice());
                portfolio.setCurrentAmount(portfolio.getCurrentPrice() - ((Double.parseDouble(stockData.getCurrentPrice())) * order.getQuantity()));
                portfolioRepo.save(portfolio);


                stockActivity.setTotalAmount(Double.parseDouble(stockData.getCurrentPrice()) * order.getQuantity());
                stockActivity.setStockName(portfolio.getSymbol());
                stockActivity.setQuantity(order.getQuantity());
                stockActivity.setStatus("SELL");
                stockActivity.setMessege("Sold Successfully");
                stockActivity.setStockPrice(Double.parseDouble(stockData.getCurrentPrice()));

                stockOrder.setEmail(email);
                stockOrder.setStockName(order.getStockName());
                stockOrder.setStockPrice(Double.parseDouble(stockData.getCurrentPrice()));
                stockOrder.setQuantity(order.getQuantity());
                stockOrder.setUserName(user.getUserName());
                stockOrder.setTotalAmount(Double.parseDouble(stockData.getCurrentPrice()) * order.getQuantity());
                stockOrder.setStatus("SELL");
                activityRepo.save(stockOrder);

                user.setFund(user.getFund() + stockOrder.getTotalAmount());
                userRepo.save(user);
                return stockActivity;
            }
        } else {
            stockActivity.setTotalAmount(0);
            stockActivity.setStockName(null);
            stockActivity.setQuantity(0);
            stockActivity.setStatus("NONE");
            stockActivity.setMessege("Please First Login");
            stockActivity.setStockPrice(0);
            return stockActivity;
        }
    }

}
