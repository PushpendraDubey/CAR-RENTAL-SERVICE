package com.carrental.config;

import com.carrental.model.Car;
import com.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private CarRepository carRepository;

    @Override
    public void run(String... args) throws Exception {
        // Load sample cars
        carRepository.save(new Car("Toyota", "Camry", 2023, "White", 
            new BigDecimal("50.00"), "ABC-1234", 
            "https://images.unsplash.com/photo-1621007947382-bb3c3994e3fb?w=400"));
        
        carRepository.save(new Car("Honda", "Accord", 2023, "Black", 
            new BigDecimal("55.00"), "XYZ-5678", 
            "https://images.unsplash.com/photo-1590362891991-f776e747a588?w=400"));
        
        carRepository.save(new Car("Tesla", "Model 3", 2024, "Red", 
            new BigDecimal("120.00"), "TES-9999", 
            "https://images.unsplash.com/photo-1560958089-b8a1929cea89?w=400"));
        
        carRepository.save(new Car("BMW", "3 Series", 2023, "Blue", 
            new BigDecimal("90.00"), "BMW-4321", 
            "https://images.unsplash.com/photo-1555215695-3004980ad54e?w=400"));
        
        carRepository.save(new Car("Mercedes", "C-Class", 2023, "Silver", 
            new BigDecimal("95.00"), "MER-7890", 
            "https://images.unsplash.com/photo-1618843479313-40f8afb4b4d8?w=400"));
        
        System.out.println("Sample car data loaded successfully!");
    }
}
