package com.lsis.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity find(@PathVariable Long id){
        return new ResponseEntity(customerRepository.findById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity list(){
        return new ResponseEntity(customerRepository.findAll(),HttpStatus.ACCEPTED);
    }

    @PostMapping
    public String save(@RequestBody Customer customer){
        customerRepository.save(customer);
        return  "Customer Added";
    }
}
