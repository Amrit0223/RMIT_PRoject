package com.example.foodlossapp.service;

import com.example.foodlossapp.model.LossData;
import com.example.foodlossapp.repository.LossDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LossDataService {

    @Autowired
    private  LossDataRepository lossDataRepository;

    public  List<LossData> findAllLossData() {
        return lossDataRepository.findAll();
    }

    public   Optional<LossData> findLossDataById(Long id) {
        return lossDataRepository.findById(id);
    }

    public  LossData saveLossData(LossData lossData) {
        return lossDataRepository.save(lossData);
    }

    public void deleteLossData(Long id) {
        lossDataRepository.deleteById(id);
    }
    public List<LossData> getLossDataChanges(List<String> countries, int startYear, int endYear) {
        return lossDataRepository.findLossDataChangesByCountryAndYearRange(countries, startYear, endYear);
    }
}
