/**
 * Created by Syed
 */
package com.etrg.syed.assignment.movierental.controller;

import com.etrg.syed.assignment.movierental.model.Customer;
import com.etrg.syed.assignment.movierental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/statement")
    public ResponseEntity<String> generateStatement(@RequestBody Customer customer) {
        return ResponseEntity.ok(rentalService.statement(customer));
    }
}