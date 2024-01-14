package com.flightsearch.flightsearchapi.service;

import com.flightsearch.flightsearchapi.model.Flight;
import com.flightsearch.flightsearchapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
        // Update fields as necessary
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }


    public List<List<Flight>> searchFlights(Long departureAirportId, Long arrivalAirportId,
                                            LocalDateTime departureDateTime,
                                            LocalDateTime returnDateTime) {
        List<Flight> departureFlights = flightRepository.findFlights(departureAirportId, arrivalAirportId, departureDateTime);
        if (returnDateTime == null) {
            return Collections.singletonList(departureFlights);
        } else {
            List<Flight> returnFlights = flightRepository.findFlights(arrivalAirportId, departureAirportId, returnDateTime);
            List<List<Flight>> result = new ArrayList<>();
            for (Flight departureFlight : departureFlights) {
                for (Flight returnFlight : returnFlights) {
                    result.add(Arrays.asList(departureFlight, returnFlight));
                }
            }
            return result;
        }
    }

    @Scheduled(cron = "0 0 1 * * ?") // Her gün saat 01:00'de çalışır
    public void fetchAndStoreFlightData() {
        // Mock API URL
        String mockApiUrl = "https://example-mockapi.com/flights";

        // RestTemplate ile API'dan veri çekme
        RestTemplate restTemplate = new RestTemplate();
        Flight[] flights = restTemplate.getForObject(mockApiUrl, Flight[].class);

        // Veritabanına kaydetme
        for (Flight flight : flights) {
            flightRepository.save(flight);
        }
    }

}