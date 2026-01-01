package com.carrental.controller;

import com.carrental.model.Rental;
import com.carrental.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@CrossOrigin(origins = "*")
public class RentalController {
    
    @Autowired
    private RentalService rentalService;

    @GetMapping
    public ResponseEntity<List<Rental>> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createRental(@Valid @RequestBody Rental rental) {
        try {
            Rental newRental = rentalService.createRental(rental);
            return ResponseEntity.status(HttpStatus.CREATED).body(newRental);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<?> completeRental(@PathVariable Long id) {
        try {
            Rental rental = rentalService.completeRental(id);
            return ResponseEntity.ok(rental);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelRental(@PathVariable Long id) {
        try {
            Rental rental = rentalService.cancelRental(id);
            return ResponseEntity.ok(rental);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/customer/{email}")
    public ResponseEntity<List<Rental>> getRentalsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(rentalService.getRentalsByEmail(email));
    }
}
