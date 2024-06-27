package com.example.foodlossapp.service;

import com.example.foodlossapp.model.Commodity;
import com.example.foodlossapp.repository.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;

    public List<Commodity> findAllCommodities() {
        return commodityRepository.findAll();
    }

    public Optional<Commodity> findCommodityById(Long id) {
        return commodityRepository.findById(id);
    }

    public Commodity saveCommodity(Commodity commodity) {
        return commodityRepository.save(commodity);
    }

    public void deleteCommodity(Long id) {
        commodityRepository.deleteById(id);
    }
}
