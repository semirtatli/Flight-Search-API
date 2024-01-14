package com.flightsearch.flightsearchapi.service;

import com.flightsearch.flightsearchapi.model.Airport;
import com.flightsearch.flightsearchapi.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).orElseThrow(() -> new RuntimeException("Airport not found"));
    }

    public Airport updateAirport(Long id, Airport airportDetails) {
        Airport airport = airportRepository.findById(id).orElseThrow(() -> new RuntimeException("Airport not found"));
        airport.setCity(airportDetails.getCity());
        return airportRepository.save(airport);
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }
}



