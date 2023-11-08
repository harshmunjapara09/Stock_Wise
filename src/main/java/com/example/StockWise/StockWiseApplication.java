package com.example.StockWise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StockWiseApplication {
	public static void main(String[] args) {
		SpringApplication.run(StockWiseApplication.class, args);
	}

}
