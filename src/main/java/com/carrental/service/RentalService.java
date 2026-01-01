package com.carrental.service;

import com.carrental.model.Car;
import com.carrental.model.Rental;
import com.carrental.repository.CarRepository;
import com.carrental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    
    @Autowired
    private RentalRepository rentalRepository;
    
    @Autowired
    private CarRepository carRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public Rental createRental(Rental rental) {
        // Check if car is available
        Car car = carRepository.findById(rental.getCar().getId())
            .orElseThrow(() -> new RuntimeException("Car not found"));
        
        if (!car.getAvailable()) {
            throw new RuntimeException("Car is not available for rental");
        }

        // Calculate total cost
        long days = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());
        if (days < 1) {
            throw new RuntimeException("Rental period must be at least 1 day");
        }
        
        rental.setTotalCost(car.getDailyRate().multiply(java.math.BigDecimal.valueOf(days)));
        
        // Mark car as unavailable
        car.setAvailable(false);
        carRepository.save(car);
        
        return rentalRepository.save(rental);
    }

    public Rental completeRental(Long id) {
        Rental rental = rentalRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rental not found"));
        
        rental.setStatus(Rental.RentalStatus.COMPLETED);
        
        // Mark car as available again
        Car car = rental.getCar();
        car.setAvailable(true);
        carRepository.save(car);
        
        return rentalRepository.save(rental);
    }

    public Rental cancelRental(Long id) {
        Rental rental = rentalRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rental not found"));
        
        rental.setStatus(Rental.RentalStatus.CANCELLED);
        
        // Mark car as available again
        Car car = rental.getCar();
        car.setAvailable(true);
        carRepository.save(car);
        
        return rentalRepository.save(rental);
    }

    public List<Rental> getRentalsByEmail(String email) {
        return rentalRepository.findByCustomerEmail(email);
    }
}
