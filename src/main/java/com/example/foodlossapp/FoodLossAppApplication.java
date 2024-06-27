package com.example.foodlossapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.foodlossapp")
public class FoodLossAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(FoodLossAppApplication.class, args);
	}
}
