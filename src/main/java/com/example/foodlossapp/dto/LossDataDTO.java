package com.example.foodlossapp.dto;


public class LossDataDTO {
    private String country;
    private double startYearLoss;
    private double endYearLoss;

    // Constructor, getters and setters
    public LossDataDTO(String country, double startYearLoss, double endYearLoss) {
        this.country = country;
        this.startYearLoss = startYearLoss;
        this.endYearLoss = endYearLoss;
    }

    // Getters and setters
}