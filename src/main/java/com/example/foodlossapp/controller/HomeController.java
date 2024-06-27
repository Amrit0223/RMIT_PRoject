
package com.example.foodlossapp.controller;

import com.example.foodlossapp.model.LossData;
import com.example.foodlossapp.service.LossDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private LossDataService lossDataService;
    @RequestMapping("/")
    public String home(Model model) {
        List<LossData> lossDataList = lossDataService.findAllLossData();

        int minYear = lossDataList.stream().mapToInt(LossData::getYear).min().orElse(1966);
        int maxYear = lossDataList.stream().mapToInt(LossData::getYear).max().orElse(2022);
        String yearRange = minYear + " - " + maxYear;

        double maxLoss = lossDataList.stream().mapToDouble(LossData::getLossPercentage).max().orElse(0);
        LossData maxLossData = lossDataList.stream()
                .filter(data -> data.getLossPercentage() == maxLoss)
                .findFirst().orElse(null);

        String maxLossCommodity = maxLossData != null ? maxLossData.getCommodity().getName() : "N/A";

        model.addAttribute("yearRange", yearRange);
        model.addAttribute("maxLoss", maxLoss);
        model.addAttribute("maxLossCommodity", maxLossCommodity);

        return "index";
    }
    @GetMapping("/Mission")
    public String mission(Model model) {
        model.addAttribute("message", "Welcome to the Food Loss and Waste Analysis Platform!");
        return "Mission";
    }

    @GetMapping("/data")
    public String data(Model model) {
        model.addAttribute("message", "Welcome to the Food Loss and Waste Analysis Platform!");
        return "dataanalysis";
    }
    @RestController
    @RequestMapping("/api/loss-data")
    public class LossDataController {

        @Autowired
        private LossDataService lossDataService;

        @GetMapping("/changes")
        public ResponseEntity<List<LossData>> getLossDataChanges(@RequestParam List<String> countries, @RequestParam int startYear, @RequestParam int endYear) {
            return ResponseEntity.ok(lossDataService.getLossDataChanges(countries, startYear, endYear));
        }
    }
}