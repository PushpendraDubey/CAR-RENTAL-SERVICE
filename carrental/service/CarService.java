package com.carrental.service;

import com.carrental.model.Car;
import com.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> getAvailableCars() {
        return carRepository.findByAvailable(true);
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car carDetails) {
        Car car = carRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
        
        car.setBrand(carDetails.getBrand());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());
        car.setColor(carDetails.getColor());
        car.setDailyRate(carDetails.getDailyRate());
        car.setLicensePlate(carDetails.getLicensePlate());
        car.setAvailable(carDetails.getAvailable());
        car.setImageUrl(carDetails.getImageUrl());
        
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public List<Car> searchCarsByBrand(String brand) {
        return carRepository.findByBrandContainingIgnoreCase(brand);
    }
}
