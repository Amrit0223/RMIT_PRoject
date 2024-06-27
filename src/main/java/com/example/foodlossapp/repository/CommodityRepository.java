package com.example.foodlossapp.repository;

import com.example.foodlossapp.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {
    Optional<Commodity> findByName(String commodityName);
    // Additional custom queries can be added here
}
