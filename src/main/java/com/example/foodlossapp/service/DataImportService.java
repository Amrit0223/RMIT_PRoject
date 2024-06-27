package com.example.foodlossapp.service;

import com.example.foodlossapp.model.Commodity;
import com.example.foodlossapp.model.Country;
import com.example.foodlossapp.model.LossData;
import com.example.foodlossapp.repository.CommodityRepository;
import com.example.foodlossapp.repository.CountryRepository;
import com.example.foodlossapp.repository.LossDataRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class DataImportService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private LossDataRepository lossDataRepository;

    @Transactional
    public void importData(String csvPath) throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvPath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord record : csvParser) {
                String countryName = record.get("country");
                String commodityName = record.get("commodity");
                Integer year = Integer.parseInt(record.get("year"));
                Double lossPercentage = Double.parseDouble(record.get("loss_percentage"));

                Country country = countryRepository.findByName(countryName)
                        .orElseGet(() -> {
                            Country newCountry = new Country();
                            newCountry.setName(countryName);
                            return countryRepository.save(newCountry);
                        });

                Commodity commodity = commodityRepository.findByName(commodityName)
                        .orElseGet(() -> {
                            Commodity newCommodity = new Commodity();
                            newCommodity.setName(commodityName);
                            return commodityRepository.save(newCommodity);
                        });

                LossData lossData = new LossData();
                lossData.setCountry(country);
                lossData.setCommodity(commodity);
                lossData.setYear(year);
                lossData.setLossPercentage(lossPercentage);
                lossDataRepository.save(lossData);
            }
        }
    }
}

