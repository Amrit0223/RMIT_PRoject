package com.example.foodlossapp.repository;


import com.example.foodlossapp.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByName(String countryName);
    // You can define custom queries here if needed
}
