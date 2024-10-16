package com.ac.in.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ac.in.dao.TicketRepository;
import com.ac.in.entity.Ticket;

import jakarta.transaction.Transactional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    private final Random random = new Random();

    // Save ticket with generated ticketNumber and seatNumber
    public void save(Ticket ticket) {
        ticket.setTicketNumber(generateTicketNumber());
        ticket.setSeatNumber(generateSeatNumber());
        ticketRepository.save(ticket);
    }

    // Generate a 6-digit numeric ticket number
    private String generateTicketNumber() {
        return String.format("%06d", random.nextInt(999999));
    }

    // Generate a 3-character seat number: 1st character alphabet, next two digits numeric
    private String generateSeatNumber() {
        char letter = (char) (random.nextInt(26) + 'A'); // Generates random letter A-Z
        int number = random.nextInt(100); // Generates a number from 00 to 99
        return String.format("%c%02d", letter, number);
    }
    
    public List<Ticket> findAllByMobNo(String mobNo) {
        return ticketRepository.findByMobileNumber(mobNo);
    }
    
    @Transactional
    public void cancelTicket(String ticketNumber) {
        ticketRepository.deleteByTicketNumber(ticketNumber);
    }
}

