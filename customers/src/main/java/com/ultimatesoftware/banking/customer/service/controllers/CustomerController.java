package com.ultimatesoftware.banking.customer.service.controllers;

import com.ultimatesoftware.banking.customer.domain.models.Customer;
import com.ultimatesoftware.banking.customer.service.repositories.CustomerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("customers")
    public String createCustomer(@Valid @RequestBody Customer customer) {
        customerRepository.save(customer);
        return customer.getId();
    }

    @PutMapping("customers/{id}")
    public ResponseEntity updateCustomer(@PathVariable("id") String id, @Valid @RequestBody Customer customer) {
        Optional<Customer> customerFromDB = customerRepository.findById(id);
        if (customerFromDB.isPresent()) {
            customerRepository.save(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("customers/{id}")
    public ResponseEntity deleteCustomers(@PathVariable("id") String id) {
        Optional<Customer> customerFromDB = customerRepository.findById(id);
        if (customerFromDB.isPresent()) {
            customerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
