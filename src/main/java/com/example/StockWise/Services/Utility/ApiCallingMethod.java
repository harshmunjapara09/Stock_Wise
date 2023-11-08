package com.example.StockWise.Services.Utility;

import com.example.StockWise.Model.dto.StockData;
import com.example.StockWise.Model.dto.StockDecision;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApiCallingMethod {
    public static StockData apiCallingMethodByStock(String shareName) throws IOException {
        StockData stockData = new StockData();
        // Define the base URL
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://nse-market.p.rapidapi.com/stocks").newBuilder();
        urlBuilder.addQueryParameter("symbol", shareName);
        String urlString = urlBuilder.build().toString();

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlString)
                    .get()
                    .addHeader("X-RapidAPI-Host", "nse-market.p.rapidapi.com")
                    .addHeader("X-RapidAPI-Key", "c005d0823cmshb3dbcbcd155520ap1c188bjsn9e28a416e7f2")
                    .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                InputStream inputStream = response.body().byteStream();
                String jsonResponse = convertStreamToString(inputStream);

                JSONObject jsonAPIResponse = new JSONObject(jsonResponse);

                stockData.setSymbol(jsonAPIResponse.optString("Symbol"));
                stockData.setCurrentPrice(jsonAPIResponse.optString("LastPrice"));
                stockData.setDayHigh(jsonAPIResponse.optString("DayHigh"));
                stockData.setDayLow(jsonAPIResponse.optString("DayLow"));
                stockData.setPercentChange(jsonAPIResponse.optString("PercentChange"));
                stockData.setLastUpdatedTime(jsonAPIResponse.optString("LastUpdateTime"));

                System.out.println(jsonAPIResponse);
            } else {
                System.out.println("API call could not be made! Response code: " + response.code());
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return stockData;
    }

    public static List<StockData> getAllStockData( ) throws IOException {
        List<StockData> list = new ArrayList<>();

        // Define the base URL
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://latest-stock-price.p.rapidapi.com/price?Indices=NIFTY%20100").newBuilder();
//        urlBuilder.addQueryParameter("symbol", shareName);
        String urlString = urlBuilder.build().toString();

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlString)
                    .get()
                    .addHeader("X-RapidAPI-Host", "latest-stock-price.p.rapidapi.com")
                    .addHeader("X-RapidAPI-Key", "5f95ad1582msh38a6ff24ef7d815p1b9cfbjsn98ff443d65ad")
                    .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                InputStream inputStream = response.body().byteStream();
                String jsonResponse = convertStreamToString(inputStream);

                JSONArray jsonArray = new JSONArray(jsonResponse);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonAPIResponse = jsonArray.getJSONObject(i);
                    StockData stockData = new StockData();

                    stockData.setSymbol(jsonAPIResponse.optString("symbol"));
                    stockData.setCurrentPrice(jsonAPIResponse.optString("lastPrice"));
                    stockData.setDayHigh(jsonAPIResponse.optString("dayHigh"));
                    stockData.setDayLow(jsonAPIResponse.optString("dayLow"));
                    stockData.setPercentChange(jsonAPIResponse.optString("pChange"));
                    stockData.setLastUpdatedTime(jsonAPIResponse.optString("lastUpdateTime"));

                    list.add(stockData);

                }
            } else {
                System.out.println("API call could not be made! Response code: " + response.code());
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return list;
    }

//    public static StockDecision getStockDecisionByStockName(String shareName) throws IOException {
//        StockDecision stockDecision = new StockDecision();
//        // Define the base URL
//        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://nse-market.p.rapidapi.com/stock_metrics").newBuilder();
//        urlBuilder.addQueryParameter("symbol", shareName);
//        String urlString = urlBuilder.build().toString();
//
//        try {
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url(urlString)
//                    .get()
//                    .addHeader("X-RapidAPI-Host", "nse-market.p.rapidapi.com")
//                    .addHeader("X-RapidAPI-Key", "c005d0823cmshb3dbcbcd155520ap1c188bjsn9e28a416e7f2")
//                    .build();
//
//            Response response = client.newCall(request).execute();
//
//            if (response.isSuccessful()) {
//                InputStream inputStream = response.body().byteStream();
//                String jsonResponse = convertStreamToString(inputStream);
//
//                JSONObject jsonAPIResponse = new JSONObject(jsonResponse);
//
//                stockDecision.setStockName(jsonAPIResponse.optString("symbol"));
//                stockDecision.setCurrentPrice(jsonAPIResponse.optString("currentPrice"));
//                stockDecision.setRecommendation(jsonAPIResponse.optString("recommendationKey"));
//
//                System.out.println(jsonAPIResponse);
//            } else {
//                System.out.println("API call could not be made! Response code: " + response.code());
//            }
//        } catch (Exception e) {
//            System.out.println("An error occurred: " + e.getMessage());
//        }
//        return stockDecision;
//    }


    private static String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
