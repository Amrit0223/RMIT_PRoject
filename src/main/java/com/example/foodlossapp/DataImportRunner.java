package com.example.foodlossapp;

import com.example.foodlossapp.service.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DataImportRunner implements CommandLineRunner {

    @Autowired
    private DataImportService dataImportService;

    @Override
    public void run(String... args) throws Exception {
        String csvPath = "FoodLoss.csv";  // Update this path to the actual CSV file path

        dataImportService.importData(csvPath);
    }
}
