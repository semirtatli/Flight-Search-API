package com.flightsearch.flightsearchapi.repository;

import com.flightsearch.flightsearchapi.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    // Custom query methods can be defined here
}
