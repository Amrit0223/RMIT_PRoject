package com.example.foodlossapp.controller;

import com.example.foodlossapp.model.LossData;
import com.example.foodlossapp.service.LossDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lossdata")
public class LossDataController {

    @Autowired
    private LossDataService lossDataService;

    @GetMapping
    public ResponseEntity<List<LossData>> getAllLossData() {
        List<LossData> lossDatas = lossDataService.findAllLossData();
        return ResponseEntity.ok(lossDatas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LossData> getLossDataById(@PathVariable Long id) {
        return lossDataService.findLossDataById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LossData> createLossData(@RequestBody LossData lossData) {
        LossData savedLossData = lossDataService.saveLossData(lossData);
        return ResponseEntity.ok(savedLossData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LossData> updateLossData(@PathVariable Long id, @RequestBody LossData lossData) {
        return lossDataService.findLossDataById(id)
                .map(existingLossData -> {
                    existingLossData.setCountry(lossData.getCountry());
                    existingLossData.setCommodity(lossData.getCommodity());
                    existingLossData.setYear(lossData.getYear());
                    existingLossData.setLossPercentage(lossData.getLossPercentage());
                    LossData updatedLossData = lossDataService.saveLossData(existingLossData);
                    return ResponseEntity.ok(updatedLossData);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLossData(@PathVariable Long id) {
        lossDataService.deleteLossData(id);
        return ResponseEntity.ok().build();
    }
}
