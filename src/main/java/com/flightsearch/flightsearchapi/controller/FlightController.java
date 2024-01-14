    package com.flightsearch.flightsearchapi.controller;


    import com.flightsearch.flightsearchapi.model.Flight;
    import com.flightsearch.flightsearchapi.service.FlightService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.format.annotation.DateTimeFormat;
    import org.springframework.web.bind.annotation.*;

    import java.time.LocalDateTime;
    import java.util.List;

    @RestController
    @RequestMapping("/flights")
    public class FlightController {
        private final FlightService flightService;

        @Autowired
        public FlightController(FlightService flightService) {
            this.flightService = flightService;
        }

        @PostMapping
        public Flight createFlight(@RequestBody Flight flight) {
            return flightService.createFlight(flight);
        }

        @GetMapping
        public List<Flight> getAllFlights() {
            return flightService.getAllFlights();
        }

        @GetMapping("/{id}")
        public Flight getFlightById(@PathVariable Long id) {
            return flightService.getFlightById(id);
        }

        @PutMapping("/{id}")
        public Flight updateFlight(@PathVariable Long id, @RequestBody Flight flightDetails) {
            return flightService.updateFlight(id, flightDetails);
        }

        @DeleteMapping("/{id}")
        public void deleteFlight(@PathVariable Long id) {
            flightService.deleteFlight(id);
        }

        @GetMapping("/search")
        public List<List<Flight>> searchFlights(@RequestParam Long departureAirportId,
                                                @RequestParam Long arrivalAirportId,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDateTime) {
            return flightService.searchFlights(departureAirportId, arrivalAirportId, departureDateTime, returnDateTime);
        }
    }

