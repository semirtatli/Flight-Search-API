package com.flightsearch.flightsearchapi.repository;

import com.flightsearch.flightsearchapi.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {



    @Query("SELECT f FROM Flight f WHERE f.departureAirportId = ?1 AND f.arrivalAirportId = ?2 AND f.departureDateTime = ?3")
    List<Flight> findFlights(Long departureAirportId, Long arrivalAirportId, LocalDateTime departureDateTime);
}