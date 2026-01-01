package com.carrental.repository;

import com.carrental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByCustomerEmail(String customerEmail);
    List<Rental> findByStatus(Rental.RentalStatus status);
}
