package com.example.foodlossapp.controller;

import com.example.foodlossapp.model.Commodity;
import com.example.foodlossapp.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commodities")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping
    public ResponseEntity<List<Commodity>> getAllCommodities() {
        List<Commodity> commodities = commodityService.findAllCommodities();
        return ResponseEntity.ok(commodities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commodity> getCommodityById(@PathVariable Long id) {
        return commodityService.findCommodityById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Commodity> createCommodity(@RequestBody Commodity commodity) {
        Commodity savedCommodity = commodityService.saveCommodity(commodity);
        return ResponseEntity.ok(savedCommodity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commodity> updateCommodity(@PathVariable Long id, @RequestBody Commodity commodity) {
        return commodityService.findCommodityById(id)
                .map(existingCommodity -> {
                    existingCommodity.setName(commodity.getName());
                    Commodity updatedCommodity = commodityService.saveCommodity(existingCommodity);
                    return ResponseEntity.ok(updatedCommodity);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommodity(@PathVariable Long id) {
        commodityService.deleteCommodity(id);
        return ResponseEntity.ok().build();
    }
}
