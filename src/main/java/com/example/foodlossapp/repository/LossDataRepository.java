package com.example.foodlossapp.repository;

import com.example.foodlossapp.model.LossData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LossDataRepository extends JpaRepository<LossData, Long> {
    @Query("SELECT new com.example.foodlossapp.dto.LossDataDTO(ld.country, ld.year, ld.lossPercentage) " +
            "FROM LossData ld WHERE ld.country IN :countries AND ld.year IN (:startYear, :endYear)")
    List<LossData> findLossDataChangesByCountryAndYearRange(List<String> countries, int startYear, int endYear);

}
