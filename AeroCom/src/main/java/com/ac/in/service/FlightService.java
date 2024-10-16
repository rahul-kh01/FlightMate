package com.ac.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ac.in.dao.FlightRepository;
import com.ac.in.entity.Flight;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> searchFlights(String origin, String destination, String departure) {
        return flightRepository.findByOriginAndDestinationAndDeparture(origin, destination, departure);
    }
    
   
}

